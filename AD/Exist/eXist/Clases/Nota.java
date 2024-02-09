package centro;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Nota {
  private ObjectId objectId;
  private int id;
  private Double nota1;
  private Double nota2;
  private Double nota3;
  private Double notaFinal;
  private Matricula Matricula;

  public Nota(int id, Double nota1, Double nota2, Double nota3, Double notaFinal) {
    this.id = id;
    this.nota1 = nota1;
    this.nota2 = nota2;
    this.nota3 = nota3;
    this.notaFinal = notaFinal;
  }

  public Nota() {

  }

  public Document toDocument() {
    return new Document()
        .append("id", id)
        .append("nota1", nota1)
        .append("nota2", nota2)
        .append("nota3", nota3)
        .append("notaFinal", notaFinal);
  }

  public Double getNotaFinal() {
    return notaFinal;
  }

  public void setNotaFinal(Double notaFinal) {
    this.notaFinal = notaFinal;
  }

  public ObjectId getObjectId() {
    return objectId;
  }

  public void setObjectId(ObjectId objectId) {
    this.objectId = objectId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Double getNota1() {
    return nota1;
  }

  public void setNota1(Double nota1) {
    this.nota1 = nota1;
  }

  public Double getNota2() {
    return nota2;
  }

  public void setNota2(Double nota2) {
    this.nota2 = nota2;
  }

  public Double getNota3() {
    return nota3;
  }

  public void setNota3(Double nota3) {
    this.nota3 = nota3;
  }

  public Matricula getMatricula() {
    return Matricula;
  }

  public void setMatricula(Matricula matricula) {
    Matricula = matricula;
  }

  @Override
  public String toString() {
    return "Alumno con NIA " + Matricula.getAlumno().getNia() + " matriculado en módulo con codigo "
        + Matricula.getModulo().getCodigo()
        + " tiene: "
        + "nota1=" + String.format("%.2f", nota1) + ", nota2=" + String.format("%.2f", nota2) + ", nota3="
        + String.format("%.2f", nota3) + ", nota final=" + String.format("%.2f", notaFinal);
  }

}
