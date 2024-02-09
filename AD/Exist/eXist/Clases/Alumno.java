package centro;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Alumno {
  private ObjectId id;
  private String objectId;
  private int nia;
  private String personaId;
  private Persona persona;

  public Document toDocument() {
    return new Document()
        .append("_id", id)
        .append("nia", nia)
        .append("persona", personaId);
  }

  public String getObjectId() {
    return objectId;
  }

  public void setObjectId(String objectId) {
    this.objectId = objectId;
  }

  public String getPersonaId() {
    return personaId;
  }

  public void setPersonaId(String personaId) {
    this.personaId = personaId;
  }

  public Alumno(int nia, Persona persona) {
    this.nia = nia;
    this.persona = persona;
  }

  public Alumno(int nia, String personaId) {
    this.nia = nia;
    this.personaId = personaId;
  }

  public Alumno() {

  }

  @Override
  public String toString() {
    return "Alumno con nia " + nia + ", " + persona.toString();
  }

  public int getNia() {
    return nia;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setNia(int nia) {
    this.nia = nia;
  }

  public Persona getPersona() {
    return persona;
  }

  public void setPersona(Persona persona) {
    this.persona = persona;
  }

}
