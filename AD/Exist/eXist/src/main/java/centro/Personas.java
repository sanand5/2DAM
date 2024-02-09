package centro;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.base.Resource;

import persistencia.Conexion;
import persistencia.Lector;

public class Personas {

  private static String documento = "personas.xml";

  public Personas() {
  }

  public static void addPersona(Persona p) {
    if (!p.getDni().isBlank()) {
      try {
        XMLResource document = Conexion.getDocument(documento);
        String currentContent = (String) document.getContent();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(currentContent));
        Document doc = builder.parse(is);

        Element persona = doc.createElement("persona");
        p.setId(obtenerId());
        persona.setAttribute("id", String.valueOf(p.getId()));
        createElement(doc, persona, "dni", p.getDni());
        createElement(doc, persona, "nombre", p.getNombre());
        createElement(doc, persona, "nombre2", p.getNombre2());
        createElement(doc, persona, "apellido", p.getApellido1());
        createElement(doc, persona, "apellido2", p.getApellido2());

        Node personasNode = doc.getDocumentElement();
        personasNode.appendChild(persona);

        String updatedContent = convertDocumentToString(doc);

        document.setContent(updatedContent);
        Conexion.getDatabase().storeResource(document);
        System.out.println("Persona agregada al documento");

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static String convertDocumentToString(Document doc) throws Exception {
    javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
    javax.xml.transform.Transformer transformer = tf.newTransformer();
    javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
    java.io.StringWriter writer = new java.io.StringWriter();
    javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(writer);
    transformer.transform(source, result);
    return writer.toString();
  }

  private static void createElement(Document doc, Element parent, String tagName, String textContent) {
    Element element = doc.createElement(tagName);
    element.setTextContent(textContent);
    parent.appendChild(element);
  }

  public static int obtenerId() {
    List<Persona> listaPersonas = getPersonas();
    int id = 0;
    for (Persona persona : listaPersonas) {
      if (persona.getId() > id) {
        id = persona.getId();
      }
    }
    return id + 1;
  }

  public static void eliminarPersona(String dni) {
    if (dni.equals("")) {
      return;
    }
  }

  public static List<Persona> getPersonas() {
    List<Persona> listaPersonas = new ArrayList<>();

    try {
      Collection collection = Conexion.getDatabase();
      XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
      ResourceSet result = service.query("//persona");

      ResourceIterator iterator = result.getIterator();
      while (iterator.hasMoreResources()) {
        Resource res = iterator.nextResource();
        String xmlContent = (String) res.getContent();
        Persona p = parsePersonaFromXML(xmlContent);
        listaPersonas.add(p);
      }
    } catch (Exception e) {
      System.out.println("Error encontrado " + e.getMessage());
    }

    return listaPersonas;
  }

  private static Persona parsePersonaFromXML(String xmlContent) {
    Persona p = new Persona();
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      InputSource inputSource = new InputSource(new StringReader(xmlContent));
      Document doc = builder.parse(inputSource);

      Node personaNode = doc.getElementsByTagName("persona").item(0);
      Element personaElement = (Element) personaNode;

      p.setId(Integer.parseInt(personaElement.getAttribute("id")));
      p.setDni(doc.getElementsByTagName("dni").item(0).getTextContent());
      p.setNombre(doc.getElementsByTagName("nombre").item(0).getTextContent());
      p.setNombre2(doc.getElementsByTagName("nombre2").item(0).getTextContent());
      p.setApellido1(doc.getElementsByTagName("apellido").item(0).getTextContent());
      p.setApellido2(doc.getElementsByTagName("apellido2").item(0).getTextContent());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return p;
  }

  public static boolean listPersonas() {
    List<Persona> listaPersonas = getPersonas();

    if (listaPersonas.isEmpty()) {
      System.out.println("No hay personas en la base de datos.");
      return false;
    } else {
      for (Persona persona : listaPersonas) {
        System.out.println(persona.toString());
      }
      listaPersonas = null;
      return true;
    }
  }

  public static Persona preguntarPersona() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Introduce los datos de la persona:");

    System.out.print("Nombre (dejar vacio para salir): ");
    String nombre = scanner.nextLine();
    if (nombre.isEmpty()) {
      return new Persona("", "", "", "", "");
    }

    System.out.print("Segundo nombre: ");
    String nombre2 = scanner.nextLine();

    System.out.print("Primer apellido (dejar vacio para salir): ");
    String apellido1 = scanner.nextLine();
    if (apellido1.isEmpty()) {
      return new Persona("", "", "", "", "");
    }

    System.out.print("Segundo apellido: ");
    String apellido2 = scanner.nextLine();

    String dni;
    boolean hacer = false;
    do {
      if (hacer) {
        System.out.println("Has introducido un DNI ya existente, escribe otro DNI o deja el espacio vacio para salir.");
      }
      System.out.print("DNI (dejar vacio para salir): ");
      dni = scanner.nextLine();
      if (dni.isEmpty()) {
        return new Persona(nombre, nombre2, "", apellido1, apellido2);
      }
      if (!verificarFormatoDNI(dni)) {
        System.out.println("Introduce un DNI con formato correcto.");
      } else {
        Persona p = encontrarPersona(dni);
        if (p != null) {
          hacer = true;
        } else {
          hacer = false;
        }
      }
    } while (!verificarFormatoDNI(dni) || hacer);

    return new Persona(nombre, nombre2, dni, apellido1, apellido2);
  }

  public static String preguntarDNI() {
    Scanner reader = new Scanner(System.in);
    String dni = "";
    int eleccion;
    do {
      System.out.println(
          "¿Estás seguro de querer eliminar una persona? Se eliminará todo lo relacionado con ella (Alumnos, Matriculas, Notas)\n1) Sí 2) No (elegir índice): ");
      eleccion = Lector.leerInt();
      if (eleccion != 1 && eleccion != 2) {
        System.out.println("Por favor, elige 1 o 2.");
      } else if (eleccion == 1) {
        listPersonas();
        do {
          System.out.println("Introduce el DNI de la persona a eliminar:");
          dni = reader.next();
          if (!verificarFormatoDNI(dni)) {
            System.out.println("Introduce un DNI con formato correcto.");
          }
        } while (!verificarFormatoDNI(dni));
      } else {
        System.out.println("Has elegido no eliminar persona.");
      }

    } while (eleccion != 1 && eleccion != 2);

    return dni.toUpperCase();
  }

  public static boolean verificarFormatoDNI(String dni) {
    if (dni.isEmpty()) {
      return true;
    }
    String regex = "\\d{8}[a-zA-Z]";
    return Pattern.matches(regex, dni);
  }

  public static Persona encontrarPersona(String dni) {
    List<Persona> listaPersonas = getPersonas();

    for (Persona persona : listaPersonas) {
      if (persona.getDni().equalsIgnoreCase(dni)) {
        return persona;
      }
    }

    return null;
  }

  // public static Persona encontrarPersonaID(ObjectId id) {
  //   List<Persona> listaPersonas = getPersonas();

  //   for (Persona persona : listaPersonas) {
  //     if (persona.getId().equals(id)) {
  //       return persona;
  //     }
  //   }
  //   return null;
  // }

  // public static void exportar() {
  //   List<Persona> personas = Personas.getPersonasId();
  //   Gson gson = new GsonBuilder().setPrettyPrinting().create();
  //   if (personas.isEmpty()) {
  //     System.err.println("No hay personas para exportar");
  //   } else {
  //     try (FileWriter writer = new FileWriter("personas.json")) {
  //       gson.toJson(personas, writer);
  //     } catch (IOException e) {
  //       System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
  //     }
  //   }
  // }

  // public static List<Persona> getPersonasId() {
  //   List<Persona> listaPersonas = new ArrayList<>();

  //   try {
  //     MongoCollection<Document> collection = Conexion.getDatabase().getCollection("personas");

  //     for (Document document : collection.find()) {
  //       Persona p = new Persona();
  //       p.setObjectIdString(document.getObjectId("_id").toString());
  //       p.setDni(document.getString("dni"));
  //       p.setNombre(document.getString("nombre"));
  //       p.setNombre2(document.getString("nombre2"));
  //       p.setApellido1(document.getString("apellido"));
  //       p.setApellido2(document.getString("apellido2"));
  //       listaPersonas.add(p);
  //     }
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
  //   return listaPersonas;
  // }

  // public static void addPersonaId(Persona p) {
  //   if (!p.getDni().isEmpty()) {
  //     Document doc = new Document()
  //         .append("_id", p.getId())
  //         .append("dni", p.getDni())
  //         .append("nombre", p.getNombre())
  //         .append("nombre2", p.getNombre2())
  //         .append("apellido", p.getApellido1())
  //         .append("apellido2", p.getApellido2());
  //     try {
  //       MongoDatabase db = Conexion.getDatabase();
  //       MongoCollection<Document> collection = db.getCollection("personas");
  //       collection.insertOne(doc);

  //       System.out.println("Persona con DNI " + p.getDni() + " añadida con éxito!");
  //     } catch (Exception e) {
  //       System.out.println("No se puede meter un DNI duplicado (" + p.getDni() + ")");
  //     }
  //   } else {
  //     System.out.println("Has decidido no añadir Persona");
  //   }
  // }

  // public static void importar() {
  //   try {
  //     FileReader reader = new FileReader("personas.json");
  //     Gson gson = new Gson();
  //     Type personaListType = new TypeToken<ArrayList<Persona>>() {
  //     }.getType();
  //     List<Persona> personas = gson.fromJson(reader, personaListType);
  //     reader.close();

  //     for (Persona persona : personas) {
  //       persona.setId(new ObjectId(persona.getObjectIdString()));
  //       if (encontrarPersona(persona.getDni()) == null && encontrarPersonaID(persona.getId()) == null) {
  //         addPersonaId(persona);
  //       }
  //     }
  //   } catch (IOException e) {
  //     System.out.println("Error de lectura o escritura del archivo, comprueba los permisos.");
  //   }
  // }

  /*public static void editarPersona() {
    if (listPersonas()) {
      Persona p = null;
      String dni = "";
      boolean repetir = false;
      do {
        System.out.print("Dime el DNI de la persona a modificar (dejar vacio para salir): ");
        dni = Lector.leerString();
        if (dni.isEmpty()) {
          System.out.println("Has decidido no modificar ninguna Persona.");
          return;
        }
        if (!verificarFormatoDNI(dni)) {
          System.out.println("Introduce un DNI con formato correcto.");
        } else {
          p = encontrarPersona(dni);
          if (p == null) {
            System.out.println("No se ha encontrado ninguna persona con ese DNI, vuelve a introducirlo.");
            repetir = true;
          } else {
            repetir = false;
          }
        }
      } while (!verificarFormatoDNI(dni) || repetir);
      while (true) {
        System.out.println("Que quieres modificar de la persona con DNI: " + p.getDni() + "?");
        System.out.println("1) Nombre: " + p.getNombre() + "\n2) Segundo nombre: " + p.getNombre2() + "\n3) Apellido: "
            + p.getApellido1() + "\n4) Segundo apellido: " + p.getApellido2() + "\n5) DNI: " + p.getDni()
            + "\n6) Guardar y salir\n7) Salir sin guardar");
        switch (Lector.leerInt()) {
          case 1:
            System.out.println("Dime el nuevo nombre: ");
            String nombre = Lector.leerString();
            p.setNombre(nombre);
            break;
          case 2:
            System.out.println("Dime el nuevo segundo nombre: ");
            String nombre2 = Lector.leerString();
            p.setNombre2(nombre2);
            break;
          case 3:
            System.out.println("Dime el nuevo apellido: ");
            String apellido = Lector.leerString();
            p.setApellido1(apellido);
            break;
          case 4:
            System.out.println("Dime el nuevo segundo apellido: ");
            String apellido2 = Lector.leerString();
            p.setApellido2(apellido2);
            break;
          case 5:
            System.out.println("Dime el nuevo DNI:");
            String nuevoDni = Lector.leerDNI();
            if (nuevoDni.equals(p.getDni())) {
              System.out.println("Has intentado poner el mismo DNI, no se han hecho cambios.");
            } else if (encontrarPersona(nuevoDni) != null) {
              System.out.println("No se puede poner un DNI que ya existe en la base de datos");
            } else {
              p.setDni(nuevoDni);
            }
            break;
          case 6:
            actualizarPersona(p);
            return;
          case 7:
            System.out.println("No se han hecho cambios en los datos");
            p = null;
            return;
          default:
            System.out.println("No has elegido una opcion correcta, vuelve a elegir una dentro del menú.");
            break;
        }
      }
    } else {
      System.out.println("No puedes editar datos si no hay personas.");
    }
  }*/

  public static void actualizarPersona(Persona p) {
    // TODO
  }

  public static void menuPersonas() {
    int opcion = 0;
    Scanner reader = new Scanner(System.in);
    do {
      System.out.println("Personas: ");
      System.out.println("\t1) Dar de alta");
      System.out.println("\t2) Dar de baja");
      System.out.println("\t3) Listar");
      System.out.println("\t4) Editar");
      System.out.println("\t5) Atrás");
      System.out.print("Elige la opción (Elegir índice): ");
      try {
        opcion = reader.nextInt();
        switch (opcion) {
          case 1:
            addPersona(preguntarPersona());
            break;
          case 2:
            eliminarPersona(preguntarDNI());
            break;
          case 3:
            listPersonas();
            break;
          case 4:
            // editarPersona();
            break;
          case 5:
            System.out.println("Volviendo atrás...");
            break;
          default:
            System.out.println("Tienes que elegir un número comprendido en el menú");
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Por favor, introduce un número decimal válido.");
      } finally {
        reader.nextLine();
      }
    } while (opcion != 5);
  }

}
