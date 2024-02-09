package centro;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

import persistencia.Conexion;
import persistencia.Lector;

public class Modulos {

  public static void darAlta() {
    System.out.print("Dime el nombre del módulo (Dejar vacio para salir): ");
    String nombre = Lector.leerString();
    if (!nombre.isEmpty()) {
      System.out.print("Ahora dime el Código (Ejemplo: 01) del módulo (Escribe un número negativo para salir): ");
      int codigo = Lector.leerInt();
      if (codigo >= 0) {
        Modulo m = encontrarModulo(codigo);
        if (m == null) {
          try {
            MongoCollection<Document> collection = Conexion.getDatabase().getCollection("modulos");

            Document modulo = new Document()
                .append("codigo", codigo)
                .append("nombre", nombre);
            collection.insertOne(modulo);
            System.out.println("Módulo con codigo " + codigo + " añadido con éxito!");

          } catch (Exception e) {
            System.out.println("Error encontrado: " + e.getMessage());
          }
        } else {
          System.out
              .println("No se ha añadido el módulo con código " + codigo + " por que ya existia en la base de datos.");
        }

      } else {
        System.out.println("Has decidido no añadir un módulo");
      }
    }

  }

  public static void darBaja() {
    int eleccion = 0;

    while (eleccion != 1 && eleccion != 2) {
      System.out.print(
          "¿Quieres dar de baja un módulo?\n1) Sí 2) No (elige índice) (recuerda que se eliminarán todas las matrículas del módulo): ");
      eleccion = Lector.leerInt();

      if (eleccion == 1) {
        listar();
        System.out.print("Dime el código del módulo que quieres dar de baja " +
            "(recuerda que se eliminarán todas las matrículas del módulo): ");
        int codigo = Lector.leerInt();
        Modulo eliminar = encontrarModulo(codigo);

        if (eliminar == null) {
          System.out.println("No hay ningún módulo con este código. Por favor, ingresa un código válido.");
        } else {
          try {
            MongoCollection<Document> collection = Conexion.getDatabase().getCollection("modulos");
            ObjectId objectid = obtenerIdModuloPorCodigo(codigo);
            long borrado = collection.deleteOne(new Document("codigo", codigo)).getDeletedCount();
            if (borrado == 0) {
              System.out.println("No se ha encontrado un modulo con este codigo");
            } else {
              Matriculas.eliminarMatricula(objectid, "modulo");
              System.out.println("El módulo con código " + codigo + " ha sido eliminado");
            }
          } catch (Exception e) {
            System.out.println("Error encontrado " + e.getMessage());
          }
        }
      } else if (eleccion == 2) {
        System.out.println("Has elegido no eliminar el módulo");
      } else {
        System.out.println("Opción no válida. Por favor, elige 1 o 2.");
      }
    }
  }

  public static ObjectId obtenerIdModuloPorCodigo(int codigo) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("modulos");
      Document modulo = collection.find(new Document("codigo", codigo)).first();
      if (modulo != null) {
        return modulo.getObjectId("_id");
      } else {
        System.out.println("No se ha encontrado un modulo con este codigo");
        return null;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static Modulo encontrarModulo(int codigo) {
    List<Modulo> listaPersonas = getModulos();

    for (Modulo modulo : listaPersonas) {
      if (modulo.getCodigo() == codigo) {
        return modulo;
      }
    }

    listaPersonas = null;
    return null;
  }

  public static Modulo encontrarModuloID(ObjectId id) {
    List<Modulo> listaPersonas = getModulos();

    for (Modulo modulo : listaPersonas) {
      if (modulo.getId().equals(id)) {
        return modulo;
      }
    }
    listaPersonas = null;
    return null;
  }

  public static List<Modulo> getModulos() {
    List<Modulo> listaPersonas = new ArrayList<>();

    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("modulos");

      for (Document document : collection.find()) {
        Modulo m = new Modulo();
        m.setId(document.getObjectId("_id"));
        m.setNombre(document.getString("nombre"));
        m.setCodigo(document.getInteger("codigo"));
        listaPersonas.add(m);

      }

    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
    }

    return listaPersonas;
  }

  public static boolean listar() {
    List<Modulo> listaModulos = getModulos();

    if (listaModulos.isEmpty()) {
      System.out.println("No hay módulos.");
      return false;
    } else {
      for (Modulo modulo : listaModulos) {
        System.out.println(modulo.toString());
      }
      return true;
    }

  }

  public static Modulo preguntarModulo() {
    String nombre = "";
    System.out.println("Cuál es el código del módulo? ");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Código: ");
    int codigo = Lector.leerInt();
    System.out.println("Cuál es el nombre del módulo? ");
    while (nombre == "") {
      nombre = scanner.nextLine();
    }
    Modulo modulo = new Modulo(codigo, nombre);
    return modulo;
  }

  public static void exportar() {
    List<Modulo> modulos = Modulos.getModulosId();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (modulos.isEmpty()) {
      System.err.println("No hay modulos para exportar");
    } else {
      try (FileWriter writer = new FileWriter("modulos.json")) {
        gson.toJson(modulos, writer);
      } catch (IOException e) {
        System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
      }
    }
  }

  public static List<Modulo> getModulosId() {
    List<Modulo> listaModulos = new ArrayList<Modulo>();

    MongoCollection<Document> collection = Conexion.getDatabase().getCollection("modulos");
    for (Document document : collection.find()) {
      Modulo m = new Modulo();
      m.setObjectId(document.getObjectId("_id").toString());
      m.setCodigo(document.getInteger("codigo"));
      m.setNombre(document.getString("nombre"));
      listaModulos.add(m);
    }
    return listaModulos;
  }

  public static void importar() {
    try {
      FileReader reader = new FileReader("modulos.json");
      Gson gson = new Gson();
      Type moduloListType = new TypeToken<ArrayList<Modulo>>() {
      }.getType();
      List<Modulo> modulos = gson.fromJson(reader, moduloListType);
      reader.close();

      for (Modulo modulo : modulos) {
        modulo.setId(new ObjectId(modulo.getObjectId()));
        if (encontrarModuloID(new ObjectId(modulo.getObjectId())) == null) {
          addModuloId(modulo);
        }
      }
    } catch (IOException e) {
      System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
    }
  }

  public static void addModuloId(Modulo modulo) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("modulos");
      Document moduloNuevo = new Document()
          .append("_id", new ObjectId(modulo.getObjectId()))
          .append("codigo", modulo.getCodigo())
          .append("nombre", modulo.getNombre());

      collection.insertOne(moduloNuevo);
      System.out.println("Módulo con codigo " + modulo.getCodigo() + " añadido con éxito!");

    } catch (Exception e) {
      System.out.println("Error encontrado: " + e.getMessage());
    }
  }

  public static void editarModulo() {
    if (listar()) {
      Modulo m = null;
      int codigo = -1;
      boolean repetir = false;
      do {
        System.out.print("Dime el código del módulo a modificar (escribir un número negativo para salir): ");
        codigo = Lector.leerInt();
        if (codigo < 0) {
          System.out.println("Has decidido no modificar ningun módulo.");
          return;
        }
        m = encontrarModulo(codigo);
        if (m == null) {
          System.out.println("No se ha encontrado ningun módulo con ese código, vuelve a introducirlo.");
          repetir = true;
        } else {
          repetir = false;
        }
      } while (repetir);
      while (true) {
        System.out.println("Que quieres modificar del módulo con codigo: " + m.getCodigo() + "?");
        System.out
            .println("1) Nombre: " + m.getNombre() + "\n2) Código: " + m.getCodigo()
                + "\n3) Guardar y salir\n4) Salir sin guardar");
        switch (Lector.leerInt()) {
          case 1:
            System.out.println("Dime el nuevo nombre: ");
            String nombre = Lector.leerString();
            m.setNombre(nombre);
            break;
          case 2:
            System.out.println("Dime el nuevo código: ");
            int codigoNuevo = Lector.leerInt();
            m.setCodigo(codigoNuevo);
            break;
          case 3:
            actualizarModulo(m);
            return;
          case 4:
            System.out.println("No se han hecho cambios en los datos");
            m = null;
            return;
          default:
            System.out.println("No has elegido una opcion correcta, vuelve a elegir una dentro del menú.");
            break;
        }
      }
    } else {
      System.out.println("No puedes editar datos si no hay modulos.");
    }
  }

  private static void actualizarModulo(Modulo m) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("modulos");
      Document moduloNuevo = m.toDocument();
      UpdateResult result = collection.replaceOne(new Document("_id", m.getId()), moduloNuevo);

      if (result.getModifiedCount() > 0) {
        System.out.println("La actualización se ha completado correctamente.");
      } else {
        System.out.println("La actualización no ha tenido lugar.");
      }

    } catch (Exception e) {
      System.out.println("Error encontrado: " + e.getMessage());
    }
  }

  public static void menuModulos() {
    int opcion = 0;
    Scanner reader = new Scanner(System.in);
    do {
      System.out.println("Módulos:");
      System.out.println("\t1) Dar de alta.");
      System.out.println("\t2) Dar de baja.");
      System.out.println("\t3) Listar.");
      System.out.println("\t4) Editar Módulos.");
      System.out.println("\t5) Matricular alumno a módulo.");
      System.out.println("\t6) Eliminar matricula.");
      System.out.println("\t7) Atrás.");
      System.out.print("Elige la opción: (Elegir índice)");

      try {
        opcion = reader.nextInt();
        switch (opcion) {
          case 1:
            darAlta();
            break;
          case 2:
            darBaja();
            break;
          case 3:
            listar();
            break;
          case 4:
            editarModulo();
            break;
          case 5:
            System.out.println("Vamos a matricular un alumno...");
            reader.nextLine();
            Matriculas.matricular();
            break;
          case 6:
            System.out.println("Vamos a eliminar una matrícula...");
            Matriculas.desmatricular();
            break;
          case 7:
            System.out.println("Volviendo atrás...");
            break;
          default:
            System.out.println("Tienes que elegir un número comprendido en el menú");
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Debes de introducir un número.");
        reader.nextLine();
      } catch (Exception e) {
        System.out.println("Excepcion encontrada " + e.getMessage());
      }
    } while (opcion != 7);
  }

}
