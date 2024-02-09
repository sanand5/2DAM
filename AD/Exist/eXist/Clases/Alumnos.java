package centro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

import java.lang.reflect.Type;

import persistencia.Conexion;
import persistencia.Lector;

public class Alumnos {

  public static boolean listar() {
    List<Alumno> listaAlumnos = getAlumnos();
    if (listaAlumnos.isEmpty()) {
      System.out.println("No hay alumnos");
      return false;
    } else {
      for (Alumno alumno : listaAlumnos) {
        System.out.println(alumno.toString());
      }
      return true;
    }
  }

  public static void darAlta() {
    int opcion = 0;
    int nia = 0;
    Persona p = null;
    boolean add = true;
    do {
      System.out.println(
          "Deseas crear un nuevo Alumno o convertir una persona ya existente a alumno?\n1)Nuevo alumno\n2)Convertir persona a alumno");
      opcion = Lector.leerInt();
      switch (opcion) {
        case 1:
          p = Personas.preguntarPersona();
          System.out.println(p.getDni());
          if (!p.getDni().isEmpty()) {
            Personas.addPersona(p);
            p = Personas.encontrarPersona(p.getDni());
            System.out.print("Dime el NIA del alumno: ");
            nia = Lector.leerInt();
            Alumno a = encontrarAlumno(nia);
            if (a == null) {
              add = true;
            } else {
              System.out.println(
                  "Este NIA ya está registrado como alumno en la base de datos, no se pueden registrar NIAs repetidos.");
              add = false;
            }
          } else {
            add = false;
          }
          break;
        case 2:
          if (Personas.listPersonas()) {
            System.out.print("Dime el DNI de la persona que quieras convertir en alumno: ");
            p = Personas.encontrarPersona(Lector.leerDNI());
            if (p == null) {
              System.out.println("No hay ninguna Persona con este DNI");
              add = false;
            } else {
              if (encontrarAlumnoPersonaID(p.getId()) == null) {
                System.out.print("Dame el NIA de este alumno: ");
                nia = Lector.leerInt();
                Alumno a = encontrarAlumno(nia);
                if (a == null) {
                  add = true;
                } else {
                  System.out.println("Este NIA ya está registrado como alumno en la base de datos.");
                  add = false;
                }
              } else {
                System.out
                    .println("Esta persona ya está como alumno en la base de datos, no la puedes volver a añadir.");
                add = false;
              }
            }
          } else {
            add = false;
            System.out.println("Sin personas, no puedes convertir.");
          }
          break;
        default:
          System.out.println("Tienes que elegir un número comprendido en el menú");
          break;
      }
    } while (opcion <= 0 && opcion >= 3);

    Alumno a;
    if (add) {
      a = new Alumno(nia, p);

      try {
        MongoCollection<Document> collection = Conexion.getDatabase().getCollection("alumnos");
        Document alumnoNuevo = new Document()
            .append("nia", nia)
            .append("persona", p.getId());

        collection.insertOne(alumnoNuevo);

        System.out.println("Alumno con NIA " + a.getNia() + " añadido con éxito!");

      } catch (Exception e) {
        System.out.println("Error encontrado: " + e.getMessage());
      }
    }
    a = null;

  }

  public static int preguntarEliminar() {
    int eleccion = 0;
    while (eleccion != 1 && eleccion != 2) {
      System.out.print(
          "¿Estás seguro de querer eliminar un Alumno? Se eliminará todo lo relacionado con él (Matriculas, Notas)\n1) Sí 2) No (elegir índice): ");
      eleccion = Lector.leerInt();
      if (eleccion == 1) {
        if (listar()) {
          System.out.println("Dime el NIA del alumno que quieres dar de baja");
          int nia = Lector.leerInt();
          if (nia < 0) {
            System.out.println("Debes introducir un NIA valido (no puede ser menor de 0)");
          }
          return nia;
        } else {
          return -1;
        }
      } else if (eleccion == 2) {
        System.out.println("Has elegido no eliminar el alumno");
        return -1;
      } else {
        System.out.println("Opción no válida. Por favor, elige 1 o 2.");
      }
    }
    return -1;
  }

  public static void darBajaAlumno(int nia) {
    if (nia >= 0) {
      try {
        MongoCollection<Document> collection = Conexion.getDatabase().getCollection("alumnos");
        ObjectId id = obtenerIdAlumnoPorNIA(nia);
        Document alu = collection.find(new Document("nia", nia)).first();

        if (alu != null) {
          Matriculas.eliminarMatricula(alu.getObjectId("_id"), "alumno");

          long borrado = collection.deleteOne(new Document("nia", nia)).getDeletedCount();

          if (borrado == 0) {
            System.out.println("No se ha encontrado un alumno con este NIA");
          } else {
            Matriculas.eliminarMatricula(id, "alumno");
            System.out.println("Se ha borrado el alumno con el NIA: " + nia);
          }
        } else {
          System.out.println("No se ha encontrado un alumno con este NIA");
        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static void eliminarAlumnoPersona(ObjectId id) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("alumnos");
      Document alu = collection.find(new Document("persona", id)).first();

      if (alu != null) {
        Matriculas.eliminarMatricula(alu.getObjectId("_id"), "alumno");

        long borrado = collection.deleteOne(new Document("persona", id)).getDeletedCount();

        if (borrado == 0) {
          System.out.println("No se ha encontrado un alumno con este NIA");
        }
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static ObjectId obtenerIdAlumnoPorNIA(int nia) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("alumnos");
      Document alumno = collection.find(new Document("nia", nia)).first();
      if (alumno != null) {
        return alumno.getObjectId("_id");
      } else {
        System.out.println("No se ha encontrado un alumno con este NIA");
        return null;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static List<Alumno> getAlumnos() {
    List<Alumno> listaAlumno = new ArrayList<>();

    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("alumnos");

      for (Document document : collection.find()) {
        Alumno a = new Alumno();
        a.setId(document.getObjectId("_id"));
        a.setNia(document.getInteger("nia"));
        Persona p = Personas.encontrarPersonaID(document.getObjectId("persona"));
        a.setPersona(p);
        listaAlumno.add(a);
      }
    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
    }

    return listaAlumno;
  }

  public static Alumno encontrarAlumno(int NIA) {
    List<Alumno> alumnos = getAlumnos();

    for (Alumno alumno : alumnos) {
      if (alumno.getNia() == NIA) {
        return alumno;
      }
    }
    return null;
  }

  public static Alumno encontrarAlumnoID(ObjectId id) {
    List<Alumno> alumnos = getAlumnos();

    for (Alumno alumno : alumnos) {
      if (alumno.getId().equals(id)) {
        return alumno;
      }
    }
    return null;
  }

  public static Alumno encontrarAlumnoPersonaID(ObjectId id) {
    List<Alumno> alumnos = getAlumnos();

    for (Alumno alumno : alumnos) {
      if (alumno.getPersona().getId().equals(id)) {
        return alumno;
      }
    }
    return null;
  }

  public static void exportar() {
    List<Alumno> alumnos = Alumnos.getAlumnosId();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (alumnos.isEmpty()) {
      System.err.println("No hay alumnos para exportar");
    } else {
      try (FileWriter writer = new FileWriter("alumnos.json")) {
        gson.toJson(alumnos, writer);
      } catch (IOException e) {
        System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
      }
    }
  }

  public static List<Alumno> getAlumnosId() {
    List<Alumno> listaAlumno = new ArrayList<>();

    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("alumnos");
      for (Document document : collection.find()) {
        Alumno a = new Alumno();
        a.setObjectId(document.getObjectId("_id").toString());
        a.setNia(document.getInteger("nia"));
        a.setPersonaId(document.getObjectId("persona").toString());
        listaAlumno.add(a);
      }
    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
    }

    return listaAlumno;
  }

  public static void importar() {
    try {
      FileReader reader = new FileReader("alumnos.json");
      Gson gson = new Gson();
      Type alumnoListType = new TypeToken<ArrayList<Alumno>>() {
      }.getType();
      List<Alumno> alumnos = gson.fromJson(reader, alumnoListType);
      reader.close();

      for (Alumno alumno : alumnos) {
        alumno.setId(new ObjectId(alumno.getObjectId()));
        if (Personas.encontrarPersonaID(new ObjectId(alumno.getPersonaId())) != null
            && encontrarAlumnoID(new ObjectId(alumno.getObjectId())) == null) {
          addAlumnoId(alumno);
        }
      }
    } catch (IOException e) {
      System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
    }
  }

  public static void addAlumnoId(Alumno a) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("alumnos");
      Document alumnoNuevo = new Document()
          .append("_id", new ObjectId(a.getObjectId()))
          .append("nia", a.getNia())
          .append("persona", new ObjectId(a.getPersonaId()));

      collection.insertOne(alumnoNuevo);

      System.out.println("Alumno con NIA " + a.getNia() + " añadido con éxito!");

    } catch (Exception e) {
      System.out.println("Error encontrado: " + e.getMessage());
    }
  }

  public static void editarAlumno() {
    if (listar()) {
      Alumno a = null;
      int nia = -1;
      boolean repetir = false;
      do {
        System.out.print("Dime el NIA del alumno a modificar (escribir un número negativo para salir): ");
        nia = Lector.leerInt();
        if (nia < 0) {
          System.out.println("Has decidido no modificar ningun Alumno.");
          return;
        }
        a = encontrarAlumno(nia);
        if (a == null) {
          System.out.println("No se ha encontrado ningun alumno con ese NIA, vuelve a introducirlo.");
          repetir = true;
        } else {
          repetir = false;
        }
      } while (repetir);
      while (true) {
        System.out.println("Que quieres modificar del alumno con NIA: " + a.getNia() + "?");
        System.out
            .println("1) NIA: " + a.getNia()
                + "\n2) Guardar y salir\n3) Salir sin guardar");
        switch (Lector.leerInt()) {
          case 1:
            System.out.println("Dime el nuevo NIA: ");
            int niaNuevo = Lector.leerInt();
            if (niaNuevo == a.getNia()) {
              System.out.println("Le has intentado poner el mismo NIA, no se han hecho cambios.");
            } else if (encontrarAlumno(niaNuevo) != null) {
              System.out.println("Ya existe este NIA en la base de datos, no pueden haber NIA duplicado");
            } else {
              a.setNia(niaNuevo);
            }
            break;
          case 2:
            actualizarAlumno(a);
            return;
          case 3:
            System.out.println("No se han hecho cambios en los datos");
            a = null;
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

  private static void actualizarAlumno(Alumno a) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("alumnos");
      Document alumnoNuevo = a.toDocument().append("persona", a.getPersona().getId());
      UpdateResult result = collection.replaceOne(new Document("_id", a.getId()), alumnoNuevo);

      if (result.getModifiedCount() > 0) {
        System.out.println("La actualización se ha completado correctamente.");
      } else {
        System.out.println("La actualización no ha tenido lugar.");
      }

    } catch (Exception e) {
      System.out.println("Error encontrado: " + e.getMessage());
    }
  }

  public static void menuAlumnos() {
    int opcion = 0;
    Scanner reader = new Scanner(System.in);
    do {
      System.out.println("Alumnos:");
      System.out.println("\t1) Dar de alta");
      System.out.println("\t2) Dar de baja");
      System.out.println("\t3) Listar");
      System.out.println("\t4) Editar");
      System.out.println("\t5) Atrás");
      System.out.print("Elige la opción: (Elegir índice)");
      try {
        opcion = reader.nextInt();
        switch (opcion) {
          case 1:
            darAlta();
            break;
          case 2:
            darBajaAlumno(preguntarEliminar());
            break;
          case 3:
            listar();
            break;
          case 4:
            editarAlumno();
            break;
          case 5:
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
        e.printStackTrace();
      }
    } while (opcion != 5);
  }

}
