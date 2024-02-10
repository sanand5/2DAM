package com.existdb.gestor;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;
import org.xmldb.api.modules.XMLResource;

import com.existdb.modelos.*;
import com.existdb.utilidades.Colors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

import java.util.List;
import java.util.Optional;

public class AlumnosDB extends CrudManager<Alumno> {
    private static String documento = "alumnos.xml";
    private static String root = "alumnos";

    public void anadirAlumno(Alumno alumno) {
        List<Alumno> alumnosList = getAllAlumnos();
        boolean existe = alumnosList != null
                && alumnosList.stream().anyMatch(al -> al.getNia().equals(alumno.getNia()));
        if (!existe) {
            addAlumno(alumno);
        } else {
            Colors.warMsg("El nia ya existe");
        }
    }

    public void eliminarAlumno(int nia) {
        List<Alumno> alumnosList = getAllAlumnos();
        Optional<Alumno> alumnoOptional = alumnosList.stream()
                .filter(al -> al.getNia().equals(nia+""))
                .findFirst();
        if (alumnoOptional.isPresent()) {
            deleteAlumnoById(Integer.valueOf(alumnoOptional.get().getId()));
            List<Matricula> matriculas = new MatriculasDB().getMatriculasByExternalId(
                    Integer.valueOf(alumnoOptional.get().getId()), MatriculasDB.CAMPO_ALUMNO);

            for (Matricula matricula : matriculas) {
                new MatriculasDB().eliminarMatricula(Integer.valueOf(matricula.getId()));
            }
        } else {
            Colors.warMsg("El nia no existe");
        }
    }

    private void addAlumno(Alumno alumno) {
        try {
            alumno.setId(obtenerMaximoId() + 1 + "");
            super.createResource(documento, alumno.toXml(), root);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void deleteAlumnoById(int id) {
        try {
            XMLResource resource = readResource(documento);
            if (resource != null) {
                Alumno alumnoToDelete = findAlumnoById(resource.getContent().toString(), id);
                if (alumnoToDelete != null) {
                    deleteResource(documento, String.valueOf(id), "alumno");
                    System.out.println("Alumno eliminado con éxito.");
                } else {
                    System.out.println("No se encontró un alumno con el ID proporcionado.");
                }
            } else {
                System.out.println("No se encontró el recurso " + documento);
            }
        } catch (Exception e) {
            Colors.errMsg("No se ha podido eliminar el alumno.");
        }
    }

    private Alumno findAlumnoById(String xmlContent, int id) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlContent));
            Document doc = builder.parse(is);

            NodeList nodeList = doc.getElementsByTagName("alumno");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element alumnoElement = (Element) nodeList.item(i);
                String idString = alumnoElement.getAttribute("id");

                try {
                    int alumnoId = Integer.parseInt(idString);

                    if (alumnoId == id) {
                        return parseAlumnoFromElement(alumnoElement);
                    }
                } catch (NumberFormatException e) {
                    Colors.errMsg("Formato incorecto id = " + idString);
                }
            }

            return null;
        } catch (Exception e) {
            Colors.errMsg("No se ha podido encontar el alumno ");
            return null;
        }
    }

    private Alumno parseAlumnoFromElement(Element alumnoElement) {
        String id = alumnoElement.getAttribute("id");
        String nombre = getChildValue(alumnoElement, "nombre");
        String apellidos = getChildValue(alumnoElement, "apellido");
        String fecha = getChildValue(alumnoElement, "fecha");
        String nia = getChildValue(alumnoElement, "nia");
        Alumno a = new Alumno(nombre, apellidos, fecha, nia);
        a.setId(id);
        return a;
    }

    private String getChildValue(Element parent, String childTagName) {
        NodeList nodeList = parent.getElementsByTagName(childTagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    private int obtenerMaximoId() {
        try {
            XMLResource document = Conexion.getDocument(documento);
            String currentContent = (String) document.getContent();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(currentContent));
            Document doc = builder.parse(is);

            NodeList nodeList = doc.getElementsByTagName("alumno");
            int maxId = 0;

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element alumnoElement = (Element) nodeList.item(i);
                String idString = alumnoElement.getAttribute("id");

                try {
                    int id = Integer.parseInt(idString);
                    if (id > maxId) {
                        maxId = id;
                    }
                } catch (NumberFormatException e) {
                    Colors.errMsg("Formato incorecto id = " + idString);
                }
            }

            return maxId;
        } catch (Exception e) {
            Colors.errMsg("No se ha podido encontar el id ");
            return 0;
        }
    }

    public Alumno getAlumnoByNia(int nia) {
        List<Alumno> alumnosList = getAllAlumnos();
        Optional<Alumno> alumnoOptional = alumnosList.stream()
                .filter(al -> al.getNia().equals(nia+""))
                .findFirst();
        return alumnoOptional.orElse(null);
    }

    public List<Alumno> getAllAlumnos() {
        try {
            XMLResource resource = readResource(documento);
            if (resource != null) {
                String xmlContent = (String) resource.getContent();
                NodeList nodeList = getElementsByTagName(xmlContent, "alumno");

                List<Alumno> alumnosList = new ArrayList<>();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element alumnoElement = (Element) nodeList.item(i);
                    Alumno alumno = parseAlumnoFromElement(alumnoElement);
                    alumnosList.add(alumno);
                }

                return alumnosList;
            } else {
                System.out.println("El documento no existe.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener todos los alumnos.");
            return null;
        }
    }

    public void exportar(String filePath) {
        exportToXml(documento, filePath);
    }
    
    public void importar(String filePath) {
        importarDesdeXml(documento, filePath);
    }
}
