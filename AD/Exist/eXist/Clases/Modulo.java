package centro;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Modulo {
  private ObjectId id;
  private String objectId;
  private int codigo;
  private String nombre;

  public String getObjectId() {
    return objectId;
  }

  public void setObjectId(String objectId) {
    this.objectId = objectId;
  }

  public Modulo() {

  }

  public Modulo(int codigo, String nombre) {
    this.codigo = codigo;
    this.nombre = nombre;
  }

  public Document toDocument() {
    return new Document()
        .append("_id", id)
        .append("codigo", codigo)
        .append("nombre", nombre);
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return "Modulo [codigo=" + codigo + ", nombre=" + nombre + "]";
  }

}
