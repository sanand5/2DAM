package centro;

public class Persona {
  private int id;
  private String dni;
  private String nombre;
  private String nombre2;
  private String apellido1;
  private String apellido2;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Persona() {
  }

  /*
   * public Document toDocument() {
   * if (nombre2 == null) {
   * nombre2 = "";
   * }
   * if (apellido2 == null) {
   * apellido2 = "";
   * }
   * return new Document("_id", id)
   * .append("dni", dni)
   * .append("nombre", nombre)
   * .append("nombre2", nombre2)
   * .append("apellido1", apellido1)
   * .append("apellido2", apellido2);
   * }
   */

  /*
   * public static Persona fromDocument(Document document) {
   * Persona persona = new Persona();
   * persona.setDni(document.getString("dni"));
   * persona.setNombre(document.getString("nombre"));
   * persona.setNombre2(document.getString("nombre2"));
   * persona.setApellido1(document.getString("apellido1"));
   * persona.setApellido2(document.getString("apellido2"));
   * persona.setId(document.getObjectId("_id"));
   * return persona;
   * }
   */

  public Persona(String nombre, String nombre2, String dni, String apellido1, String apellido2) {
    this.nombre = nombre;
    this.nombre2 = nombre2;
    this.dni = dni;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNombre() {
    if (nombre == null) {
      return "Modifica el nombre";
    }
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre2() {
    if (nombre2 == null) {
      return "";
    }
    return nombre2;
  }

  public void setNombre2(String nombre2) {
    this.nombre2 = nombre2;
  }

  public String getApellido1() {
    if (apellido1 == null) {
      return "Modifica el apellido";
    }
    return apellido1;
  }

  public void setApellido1(String apellido1) {
    this.apellido1 = apellido1;
  }

  public String getApellido2() {
    if (apellido2 == null) {
      return "";
    }
    return apellido2;
  }

  public void setApellido2(String apellido2) {
    this.apellido2 = apellido2;
  }

  @Override
  public String toString() {
    return "Persona [dni=" + dni + ", nombre=" + nombre + ", nombre2=" + nombre2 + ", apellido1="
        + apellido1 + ", apellido2=" + apellido2 + "]";
  }
}
