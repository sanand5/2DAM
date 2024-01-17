package mongo;

//
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//
//public class Evaluacion {
//
//    private long id;
//    private Matricula matricula;
//    private double nota;
//    private Date fechaEvaluacion;
//
//    public Evaluacion() {
//
//    }
//
//    public Evaluacion(long id, Matricula matricula, double nota, Date fechaEvaluacion) {
//        this.id = id;
//        this.matricula = matricula;
//        this.nota = nota;
//        this.fechaEvaluacion = fechaEvaluacion;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Matricula getMatricula() {
//        return matricula;
//    }
//
//    public void setMatricula(Matricula matricula) {
//        this.matricula = matricula;
//    }
//
//    public double getNota() {
//        return nota;
//    }
//
//    public void setNota(double nota) {
//        this.nota = nota;
//    }
//
//    public Date getFechaEvaluacion() {
//        return fechaEvaluacion;
//    }
//
//    public void setFechaEvaluacion(Date fechaEvaluacion) {
//        this.fechaEvaluacion = fechaEvaluacion;
//    }
//    
//    
//
//    // Otras funciones y métodos existentes
//    public static void calificarEvaluaciones() {
//        Scanner scanner = new Scanner(System.in);
//        MongoClient mongoClient = null;
//
//        try {
//            mongoClient = MongoClients.create("mongodb://localhost:27017");
//            MongoDatabase database = mongoClient.getDatabase("tuBaseDeDatos");
//            MongoCollection<Document> evaluacionesCollection = database.getCollection("evaluaciones");
//
//            System.out.println("Ingrese el DNI del alumno:");
//            String dni = scanner.nextLine();
//
//            List<Document> evaluaciones = new ArrayList<>();
//
//            List<Matricula> matriculas = Matricula.buscarMatriculasPorDNI(dni);
//
//            if (matriculas.isEmpty()) {
//                System.out.println("El alumno con DNI " + dni + " no está matriculado en ningún módulo.");
//                return;
//            }
//
//            System.out.println("Matrículas del alumno con DNI " + dni + ":");
//            for (int i = 0; i < matriculas.size(); i++) {
//                System.out.println((i + 1) + ". " + matriculas.get(i).getModulo().getNombreModulo());
//            }
//
//            System.out.println("Seleccione el número de la matrícula a calificar:");
//            int seleccion = scanner.nextInt();
//            scanner.nextLine();
//
//            if (seleccion < 1 || seleccion > matriculas.size()) {
//                System.out.println("Selección inválida.");
//                return;
//            }
//
//            Matricula matriculaSeleccionada = matriculas.get(seleccion - 1);
//
//            Date fechaEvaluacion = null;
//
//            do {
//                System.out.print("Ingrese la fecha de evaluación (en formato YYYY-MM-DD):");
//                String fechaEvaluacionStr = scanner.nextLine();
//
//                try {
//                    fechaEvaluacion = Date.valueOf(fechaEvaluacionStr);
//                } catch (IllegalArgumentException e) {
//                    System.out.println("Formato de fecha incorrecto. Inténtelo nuevamente.");
//                }
//
//            } while (fechaEvaluacion == null);
//
//            System.out.println("Ingrese la cantidad de evaluaciones para la matrícula en " + matriculaSeleccionada.getModulo().getNombreModulo() + ":");
//            int cantidadEvaluaciones = scanner.nextInt();
//            scanner.nextLine();
//
//            while (cantidadEvaluaciones < 3) {
//                System.out.println("Debe ingresar al menos tres evaluaciones. Ingrese la cantidad nuevamente:");
//                cantidadEvaluaciones = scanner.nextInt();
//                scanner.nextLine();
//            }
//
//            int i = 0;
//            do {
//                System.out.print("Ingrese la nota para la evaluación " + (i + 1) + ": ");
//                double nota;
//
//                if (scanner.hasNext()) {
//                    nota = scanner.nextDouble();
//                    scanner.nextLine();
//                } else {
//                    break;
//                }
//
//                long matriculaId = matriculaSeleccionada.getId();
//
//                Document evaluacionDocument = new Document()
//                        .append("matricula_id", matriculaId)
//                        .append("nota", nota)
//                        .append("fecha_evaluacion", fechaEvaluacion);
//
//                evaluaciones.add(evaluacionDocument);
//
//                System.out.println("Evaluación registrada correctamente.");
//
//                i++;
//
//            } while (i < cantidadEvaluaciones);
//
//            evaluacionesCollection.insertMany(evaluaciones);
//
//        } catch (Exception e) {
//            System.out.println("Error al calificar evaluaciones: " + e.getMessage());
//        } finally {
//            if (mongoClient != null) {
//                mongoClient.close();
//            }
//        }
//    }
//
//    // Métodos y funciones adicionales
//    public static void modificarEvaluaciones() {
//        Scanner scanner = new Scanner(System.in);
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = Conexion.obtenerConexion();
//
//            System.out.println("Ingrese el DNI del alumno:");
//            String dni = scanner.nextLine();
//
//            if (Alumno.buscarAlumnoPorDNI(dni) == null) {
//                System.out.println("El alumno con DNI " + dni + " no está dado de alta.");
//                return;
//            }
//
//            List<Matricula> matriculas = Matricula.buscarMatriculasPorDNI(dni);
//
//            if (matriculas.isEmpty()) {
//                System.out.println("El alumno con DNI " + dni + " no está matriculado en ningún módulo.");
//                return;
//            }
//
//            System.out.println("Matrículas del alumno con DNI " + dni + ":");
//            for (int i = 0; i < matriculas.size(); i++) {
//                System.out.println((i + 1) + ". " + matriculas.get(i).getModulo().getNombreModulo());
//            }
//
//            System.out.println("Seleccione el número de la matrícula:");
//            int seleccionMatricula = scanner.nextInt();
//
//            if (seleccionMatricula < 1 || seleccionMatricula > matriculas.size()) {
//                System.out.println("Selección inválida.");
//                return;
//            }
//
//            Matricula matriculaSeleccionada = matriculas.get(seleccionMatricula - 1);
//
//            List<Evaluacion> evaluaciones = Evaluacion.buscarEvaluacionesPorMatricula(matriculaSeleccionada.getId());
//
//            if (evaluaciones.isEmpty()) {
//                System.out.println("No hay evaluaciones registradas para el alumno en este módulo.");
//                return;
//            }
//
//            System.out.println("Evaluaciones del alumno en " + matriculaSeleccionada.getModulo().getNombreModulo() + ":");
//            for (int i = 0; i < evaluaciones.size(); i++) {
//                Evaluacion evaluacion = evaluaciones.get(i);
//                System.out.println((i + 1) + ". Fecha: " + evaluacion.getFechaEvaluacion() + ", Nota: " + evaluacion.getNota());
//            }
//
//            System.out.println("Seleccione el número de la evaluación a modificar:");
//            int seleccionEvaluacion = scanner.nextInt();
//
//            if (seleccionEvaluacion < 1 || seleccionEvaluacion > evaluaciones.size()) {
//                System.out.println("Selección inválida.");
//                return;
//            }
//
//            Evaluacion evaluacionSeleccionada = evaluaciones.get(seleccionEvaluacion - 1);
//
//            System.out.print("Ingrese la nueva nota: ");
//            double nuevaNota = scanner.nextDouble();
//            scanner.nextLine();
//
//            Date nuevaFecha = null;
//            do {
//                System.out.print("Ingrese la nueva fecha de evaluación (en formato YYYY-MM-DD): ");
//                String nuevaFechaStr = scanner.nextLine();
//
//                try {
//                    nuevaFecha = Date.valueOf(nuevaFechaStr);
//                } catch (IllegalArgumentException e) {
//                    System.out.println("Formato de fecha incorrecto. Inténtelo nuevamente.");
//                }
//
//            } while (nuevaFecha == null);
//
//            String updateQuery = "UPDATE evaluaciones SET nota = ?, fecha_evaluacion = ? WHERE id = ?";
//            preparedStatement = connection.prepareStatement(updateQuery);
//            preparedStatement.setDouble(1, nuevaNota);
//            preparedStatement.setDate(2, nuevaFecha);
//            preparedStatement.setLong(3, evaluacionSeleccionada.getId());
//            int filasActualizadas = preparedStatement.executeUpdate();
//
//            if (filasActualizadas > 0) {
//                System.out.println("Evaluación modificada correctamente.");
//            } else {
//                System.out.println("No se pudo modificar la evaluación.");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error al modificar evaluación: " + e.getMessage());
//        } finally {
//            closeResources(connection, preparedStatement, resultSet);
//        }
//    }
//
//    public static List<Evaluacion> buscarEvaluacionesPorMatricula(long matriculaId) {
//        List<Evaluacion> evaluaciones = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = Conexion.obtenerConexion();
//
//            String selectQuery = "SELECT * FROM evaluaciones WHERE matricula_id = ?";
//            preparedStatement = connection.prepareStatement(selectQuery);
//            preparedStatement.setLong(1, matriculaId);
//
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                long id = resultSet.getLong("id");
//                double nota = resultSet.getDouble("nota");
//                Date fechaEvaluacion = resultSet.getDate("fecha_evaluacion");
//
//                Evaluacion evaluacion = new Evaluacion(id, null, nota, fechaEvaluacion);
//                evaluaciones.add(evaluacion);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error al buscar evaluaciones por matrícula: " + e.getMessage());
//        } finally {
//            closeResources(connection, preparedStatement, resultSet);
//        }
//
//        return evaluaciones;
//    }
//
//    public static void listarEvaluacionesPorAlumno() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Ingrese el DNI del alumno:");
//        String dni = scanner.nextLine();
//
//        List<Matricula> matriculas = Matricula.buscarMatriculasPorDNI(dni);
//
//        if (matriculas.isEmpty()) {
//            System.out.println("El alumno con DNI " + dni + " no está matriculado en ningún módulo.");
//            return;
//        }
//
//        for (Matricula matricula : matriculas) {
//            System.out.println("\nMódulo: " + matricula.getModulo().getNombreModulo());
//
//            List<Evaluacion> evaluaciones = buscarEvaluacionesPorMatricula(matricula.getId());
//
//            if (evaluaciones.isEmpty()) {
//                System.out.println("No hay evaluaciones registradas para este módulo.");
//            } else {
//                System.out.println("Evaluaciones:");
//                for (Evaluacion evaluacion : evaluaciones) {
//                    System.out.println("Fecha: " + evaluacion.getFechaEvaluacion() + ", Nota: " + evaluacion.getNota());
//                }
//            }
//        }
//    }
//
//    private static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
//        try {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al cerrar recursos: " + e.getMessage());
//        }
//    }
//}
