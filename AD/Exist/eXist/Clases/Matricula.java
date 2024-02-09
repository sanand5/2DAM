package centro;

import org.bson.types.ObjectId;

public class Matricula {
  private ObjectId objectId;
  private String objectIdString;
  private int id;
  private Alumno alumno;
  private Modulo modulo;
  private String alumnoId;
  private String moduloId;

  public String getObjectIdString() {
    return objectIdString;
  }

  public void setObjectIdString(String objectIdString) {
    this.objectIdString = objectIdString;
  }

  public String getAlumnoId() {
    return alumnoId;
  }

  public void setAlumnoId(String alumnoId) {
    this.alumnoId = alumnoId;
  }

  public String getModuloId() {
    return moduloId;
  }

  public void setModuloId(String moduloId) {
    this.moduloId = moduloId;
  }

  public ObjectId getObjectId() {
    return objectId;
  }

  public void setObjectId(ObjectId objectId) {
    this.objectId = objectId;
  }

  public Matricula() {

  }

  public Matricula(Alumno alumno, Modulo modulo) {
    this.alumno = alumno;
    this.modulo = modulo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Alumno getAlumno() {
    return alumno;
  }

  public void setAlumno(Alumno alumno) {
    this.alumno = alumno;
  }

  public Modulo getModulo() {
    return modulo;
  }

  public void setModulo(Modulo modulo) {
    this.modulo = modulo;
  }

  @Override
  public String toString() {
    return "Matricula [id=" + id + ", alumno con NIA: " + alumno.getNia() + ", y módulo con código: "
        + modulo.getCodigo()
        + "]";
  }

}