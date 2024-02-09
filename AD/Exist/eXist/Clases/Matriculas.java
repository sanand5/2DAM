package centro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import java.lang.reflect.Type;

import persistencia.Conexion;
import persistencia.Lector;

public class Matriculas {

  public static void matricular() {
    if (Alumnos.getAlumnos().isEmpty() || Modulos.getModulos().isEmpty()) {
      System.out.println("Sin alumnos o modulos no se puede matricular. \n");
    } else {
      Alumno alumno = seleccionarAlumno();
      if (alumno != null) {
        Modulo modulo = seleccionarModulo();
        if (modulo != null) {
          if (encontrarMatricula(alumno, modulo) == null) {
            realizarMatricula(alumno, modulo);
          } else {
            System.out.println("Ya existe una matricula con este alumno y módulo.");
          }

        }
      }
    }
  }

  public static void realizarMatricula(Alumno alumno, Modulo modulo) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("matriculas");
      Document matriculaNueva = new Document()
          .append("idMat", obtenerId())
          .append("alumno", alumno.getId())
          .append("modulo", modulo.getId());
      collection.insertOne(matriculaNueva);
      System.out.println("Matrícula creada con éxito!");
    } catch (Exception e) {
      System.out.println("Error encontrado: " + e.getMessage());
    }
  }

  public static int obtenerId() {
    List<Matricula> listaMatriculas = getMatriculas();
    int id = 0;
    for (Matricula matricula : listaMatriculas) {
      if (matricula.getId() > id) {
        id = matricula.getId();
      }
    }
    return id + 1;
  }

  public static Alumno seleccionarAlumno() {
    Alumno alumno = null;
    if (Alumnos.listar()) {
      while (alumno == null) {
        System.out.println("NIA del alumno que quieras matricular");
        int niaAlu = Lector.leerInt();
        alumno = Alumnos.encontrarAlumno(niaAlu);
        if (alumno == null) {
          System.out.println("Has escogido un NIA erróneo, vuelve a intentarlo");
        }
      }
    } else {
      System.out.println("Sin alumnos no se puede matricular");
    }
    return alumno;

  }

  public static Modulo seleccionarModulo() {
    Modulo modulo = null;
    if (Modulos.listar()) {
      while (modulo == null) {
        System.out.println("Código del modulo que quieras matricular a este alumno.");
        int codMod = Lector.leerInt();
        modulo = Modulos.encontrarModulo(codMod);
        if (modulo == null) {
          System.out.println("Has escogido un código erróneo, vuelve a intentarlo");
        }
      }
    } else {
      Lector.limpiarBuffer();
      System.out.println("Sin modulos no se puede matricular");
    }
    return modulo;
  }


  public static List<Matricula> getMatriculas() {
    List<Matricula> listaMatriculas = new ArrayList<>();

    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("matriculas");

      for (Document document : collection.find()) {
        Matricula m = new Matricula();
        m.setObjectId(document.getObjectId("_id"));
        m.setId(document.getInteger("idMat"));
        m.setAlumno(Alumnos.encontrarAlumnoID(document.getObjectId("alumno")));
        m.setModulo(Modulos.encontrarModuloID(document.getObjectId("modulo")));
        listaMatriculas.add(m);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return listaMatriculas;
  }

  public static List<Matricula> getMatriculasId() {
    List<Matricula> listaMatriculas = new ArrayList<>();

    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("matriculas");

      for (Document document : collection.find()) {
        Matricula m = new Matricula();
        m.setObjectIdString(document.getObjectId("_id").toString());
        m.setId(document.getInteger("idMat"));
        m.setAlumnoId(document.getObjectId("alumno").toString());
        m.setModuloId(document.getObjectId("modulo").toString());
        listaMatriculas.add(m);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return listaMatriculas;
  }

  public static Matricula encontrarMatricula(Alumno alumno, Modulo modulo) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("matriculas");
      for (Document document : collection.find()) {
        if (document.getObjectId("alumno").equals(alumno.getId())
            && document.getObjectId("modulo").equals(modulo.getId())) {
          return new Matricula(alumno, modulo);
        }
      }
    } catch (Exception e) {
      System.out.println("Excepcion encontrada: " + e.getMessage());
    }
    return null;
  }

  public static void desmatricular() {
    if (listar()) {
      System.out.println("Dime el ID de la matricula que quieres eliminar.");
      int matId = Lector.leerInt();
      Matricula m = encontrarMatricula(matId);
      if (m == null) {
        System.out.println("No se ha encontrado ninguna matricula con esta ID");
      } else {
        try {
          MongoCollection<Document> collection = Conexion.getDatabase().getCollection("matriculas");
          Notas.eliminarNotaMat(matId);
          long borrado = collection.deleteOne(new Document("_id", m.getObjectId())).getDeletedCount();
          if (borrado == 0) {
            System.out.println("No se ha podido eliminar la matricula");
          } else {
            System.out.println("La matricula con ID " + matId + " ha sido eliminada.");
          }
        } catch (Exception e) {
          System.out.println("Error encontrado " + e.getMessage());
        }
      }
    } else {
      System.out.println("No hay matriculas, sin matriculas, no se pueden eliminar matriculas");
    }
  }

  public static void eliminarMatricula(ObjectId id, String campo) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("matriculas");
      Bson filter = null;

      if (campo.equals("alumno")) {
        filter = Filters.eq("alumno", id);
      } else {
        filter = Filters.eq("modulo", id);
      }

      FindIterable<Document> documents = collection.find(filter);

      for (Document document : documents) {
        System.out.println("Te elimino la nota");
        Notas.eliminarNotaMat(document.getInteger("idMat"));
      }

      collection.deleteMany(filter);

    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static boolean listar() {
    List<Matricula> listaMatriculas = getMatriculas();

    if (listaMatriculas.isEmpty()) {
      System.out.println("No hay matrículas.");
    } else {
      for (Matricula matricula : listaMatriculas) {
        System.out.println(matricula.toString());
      }
      return true;
    }
    return false;
  }

  public static void exportar() {
    List<Matricula> matriculas = Matriculas.getMatriculasId();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (matriculas.isEmpty()) {
      System.err.println("No hay matriculas para exportar");
    } else {
      try (FileWriter writer = new FileWriter("matriculas.json")) {
        gson.toJson(matriculas, writer);
      } catch (IOException e) {
        System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
      }
    }
  }

  public static void importar() {
    try {
      FileReader reader = new FileReader("matriculas.json");
      Gson gson = new Gson();
      Type matriculaListType = new TypeToken<ArrayList<Matricula>>() {
      }.getType();
      List<Matricula> matriculas = gson.fromJson(reader, matriculaListType);
      reader.close();

      for (Matricula matricula : matriculas) {
        ObjectId objectId = new ObjectId(matricula.getObjectIdString());
        ObjectId alumnoId = new ObjectId(matricula.getAlumnoId());
        ObjectId moduloId = new ObjectId(matricula.getModuloId());

        Alumno alumno = Alumnos.encontrarAlumnoID(alumnoId);
        Modulo modulo = Modulos.encontrarModuloID(moduloId);
        if (alumno != null && modulo != null) {
          realizarMatricula(objectId, matricula.getId(), alumno, modulo);
        }
      }
    } catch (IOException e) {
      System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
    }
  }

  public static void realizarMatricula(ObjectId objectId, int id, Alumno alumno, Modulo modulo) {
    try {
      MongoCollection<Document> collection = Conexion.getDatabase().getCollection("matriculas");
      Matricula m = encontrarMatricula(alumno, modulo);
      if (m == null) {
        Document matriculaNueva = new Document()
            .append("_id", objectId)
            .append("idMat", id)
            .append("alumno", alumno.getId())
            .append("modulo", modulo.getId());
        collection.insertOne(matriculaNueva);

        System.out.println("Matrícula creada con éxito!");
      }
    } catch (Exception e) {
      System.out.println("Error encontrado: " + e.getMessage());
    }
  }

  public static Matricula encontrarMatricula(int id) {
    List<Matricula> listaMatriculas = getMatriculas();

    if (listaMatriculas.isEmpty()) {
      System.out.println("No hay matrículas.");
    } else {
      for (Matricula matricula : listaMatriculas) {
        if (matricula.getId() == id) {
          return matricula;
        }
      }
    }
    return null;
  }

}