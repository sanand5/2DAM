package centro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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

import persistencia.Conexion;
import persistencia.Lector;

public class Notas {

  public static void calificar() {
    Matricula matricula = null;
    int idmat = 0;
    while (matricula == null) {
      if (!Matriculas.listar()) {
        System.out.println("Sin matriculas no puedes dar notas.");
        return;
      }
      System.out.print("ID de la matricula que quieras añadir notas: ");
      idmat = Lector.leerInt();
      matricula = Matriculas.encontrarMatricula(idmat);
      if (matricula == null) {
        System.out.println("Has escogido un ID erróneo, vuelve a intentarlo");
      }
    }
    Nota notaExistente = null;
    notaExistente = encontrarNota(idmat);

    if (notaExistente != null) {
      System.out.println("Ya existen notas para esta matrícula. No se pueden añadir más notas.");
      return;
    }

    Nota nota = new Nota();
    nota = preguntarNotas();
    nota.setMatricula(matricula);

    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("notas");
      Document nuevaNota = new Document()
          .append("id", matricula.getId())
          .append("nota1", nota.getNota1())
          .append("nota2", nota.getNota2())
          .append("nota3", nota.getNota3())
          .append("notaFinal", nota.getNotaFinal());
      collection.insertOne(nuevaNota);

      System.out.println("Notas añadidas con exito (" + nota.getNota1() + ", " + nota.getNota2() + ", "
          + nota.getNota3() + ") creada con éxito!");
    } catch (Exception e) {
      System.out.println("Excepcion encontrada " + e.getMessage());
    }
  }

  public static void eliminarNota(Matricula matricula) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("notas");
      long borrado = collection.deleteOne(new Document("_id", matricula.getObjectId())).getDeletedCount();
      if (borrado == 0) {
        System.out.println("No se han podido borrar las notas,.");
      } else {
        System.out.println("Nota eliminada con éxito!");
      }
    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
    }
  }

  public static void modificar() {
    Matricula matricula = null;
    int idmat = 0;
    while (matricula == null) {
      Matriculas.listar();
      System.out.println("ID de la matricula que quieras modificar notas");
      idmat = Lector.leerInt();
      matricula = Matriculas.encontrarMatricula(idmat);
      if (matricula == null) {
        System.out.println("Has escogido un ID erróneo, vuelve a intentarlo");
      }
    }
    Nota notaExistente = encontrarNota(idmat);
    if (notaExistente == null) {
      System.out.println(
          "No existen notas para esta matrícula. No se pueden modificar las notas. Primero deberás calificar.");
      return;
    } else {
      while (true) {
        System.out.print("\t1) Nota 1: " + notaExistente.getNota1() + "\n\t2) Nota 2: " + notaExistente.getNota2()
            + "\n\t3) Nota 3: " + notaExistente.getNota3()
            + "\n\t4) Todas las notas\n\t5) Guardar y salir\n\t6) Salir sin guardar"
            + "\n\t¿Qué nota quieres modificar? (Elegir índice): ");
        switch (Lector.leerInt()) {
          case 1:
            System.out.println("Dime la nueva nota:");
            notaExistente.setNota1(Lector.leerNota());
            break;
          case 2:
            System.out.println("Dime la nueva nota:");
            notaExistente.setNota2(Lector.leerNota());
            break;
          case 3:
            System.out.println("Dime la nueva nota:");
            notaExistente.setNota3(Lector.leerNota());
            break;
          case 4:
            Nota nota = preguntarNotas();
            notaExistente.setNota1(nota.getNota1());
            notaExistente.setNota2(nota.getNota2());
            notaExistente.setNota3(nota.getNota3());
            notaExistente.setNotaFinal(getMedia(notaExistente));
            break;
          case 5:
            notaExistente.setNotaFinal(getMedia(notaExistente));
            replaceNotas(notaExistente);
            return;
          case 6:
            System.out.println("Has decidido no modificar notas");
            return;
        }
      }
    }
  }

  public static void eliminarNotaMat(int idMat) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("notas");
      collection.deleteMany(new Document("id", idMat));
    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
    }
  }

  public static void replaceNotas(Nota nota) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("notas");
      collection.replaceOne(new Document("id", nota.getId()), nota.toDocument());
      System.out.println("Se han actualizado las notas.");
    } catch (Exception e) {
      System.out.println("Error encontrado notas: " + e.getMessage());
    }
  }

  private static Nota preguntarNotas() {
    Nota notas = new Nota();
    System.out.print("Dime la nota 1:");
    Double nota1 = Lector.leerNota();
    System.out.print("Dime la nota 2:");
    Double nota2 = Lector.leerNota();
    System.out.print("Dime la nota 3:");
    Double nota3 = Lector.leerNota();
    notas.setNota1(nota1);
    notas.setNota2(nota2);
    notas.setNota3(nota3);
    notas.setNotaFinal(getMedia(notas));
    return notas;
  }

  public static double getMedia(Nota nota) {
    return (nota.getNota1() + nota.getNota2() + nota.getNota3()) / 3;
  }

  public static List<Nota> getNotas() {
    List<Nota> listaNotas = new ArrayList<>();
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("notas");

      for (Document document : collection.find()) {
        Nota n = new Nota();
        n.setObjectId(document.getObjectId("_id"));
        n.setId(document.getInteger("id"));
        n.setNota1(document.getDouble("nota1"));
        n.setNota2(document.getDouble("nota2"));
        n.setNota3(document.getDouble("nota3"));
        n.setNotaFinal(document.getDouble("notaFinal"));
        n.setMatricula(Matriculas.encontrarMatricula(document.getInteger("id")));
        listaNotas.add(n);
      }
    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
      e.printStackTrace();
    }
    return listaNotas;
  }

  public static void notas() {
    List<Nota> boletinNotas = getNotas();

    if (boletinNotas.isEmpty()) {
      System.out.println("No hay notas");
    } else {
      for (Nota nota : boletinNotas) {
        System.out.println(nota);
      }
    }
  }

  public static Nota encontrarNota(int id) {
    List<Nota> listaNotas = getNotas();
    for (Nota nota : listaNotas) {
      if (nota.getId() == id) {
        return nota;
      }
    }
    return null;
  }

  public static List<Nota> getNotasExport() {
    List<Nota> listaNotas = new ArrayList<>();
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("notas");

      for (Document document : collection.find()) {
        Nota n = new Nota();
        n.setId(document.getInteger("id"));
        n.setNota1(document.getDouble("nota1"));
        n.setNota2(document.getDouble("nota2"));
        n.setNota3(document.getDouble("nota3"));
        n.setNotaFinal(document.getDouble("notaFinal"));
        listaNotas.add(n);
      }
    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
      e.printStackTrace();
    }
    return listaNotas;
  }

  public static void exportar() {
    List<Nota> notas = Notas.getNotasExport();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (notas.isEmpty()) {
      System.err.println("No hay notas para exportar");
    } else {
      try (FileWriter writer = new FileWriter("notas.json")) {
        gson.toJson(notas, writer);
      } catch (IOException e) {
        System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
      }
    }
  }

  public static void importar() {
    try {
      FileReader reader = new FileReader("notas.json");
      Gson gson = new Gson();
      Type notaListType = new TypeToken<ArrayList<Nota>>() {
      }.getType();
      List<Nota> notas = gson.fromJson(reader, notaListType);
      reader.close();

      for (Nota nota : notas) {
        if (encontrarNota(nota.getId()) == null) {
          addNotaId(nota);
        }
      }
    } catch (IOException e) {
      System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
    }
  }

  public static void addNotaId(Nota nota) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("notas");
      Document notaNueva = new Document()
          .append("id", nota.getId())
          .append("nota1", nota.getNota1())
          .append("nota2", nota.getNota2())
          .append("nota3", nota.getNota3())
          .append("notaFinal", nota.getNotaFinal());

      collection.insertOne(notaNueva);
      System.out.println("Nota con id " + nota.getId() + " añadida con éxito!");

    } catch (Exception e) {
      System.out.println("Error encontrado: " + e.getMessage());
    }
  }

  public static void menuEvaluar() {
    int opcion = 0;
    Scanner reader = new Scanner(System.in);
    do {
      System.out.println("Evaluar");
      System.out.println("\t1) Calificar");
      System.out.println("\t2) Modificar");
      System.out.println("\t3) Traer boletín de notas");
      System.out.println("\t4) Atrás");
      try {
        System.out.print("Elige la opción: ");
        opcion = reader.nextInt();
        switch (opcion) {
          case 1:
            calificar();
            break;
          case 2:
            modificar();
            break;
          case 3:
            notas();
            break;
          case 4:
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
        System.out.println("Error encontrado en notas: " + e.getMessage());
        e.printStackTrace();
      }
    } while (opcion != 4);
  }
}
