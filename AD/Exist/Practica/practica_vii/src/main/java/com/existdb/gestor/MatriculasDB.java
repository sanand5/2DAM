package com.existdb.gestor;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmldb.api.modules.XMLResource;

import com.existdb.modelos.Matricula;
import com.existdb.utilidades.Colors;

import java.util.List;

public class MatriculasDB extends CrudManager<Matricula> {
    private static String documento = "matriculas.xml";
    private static String root = "matriculas";
    public static final String CAMPO_ALUMNO = "idAlumno";
    public static final String CAMPO_MODULO = "idModulo";

    public void anadirMatricula(Matricula matricula) {
        List<Matricula> matriculasList = getAllMatriculas();
        boolean existe = matriculasList != null
                && matriculasList.stream().anyMatch(mat -> mat.getIdAlumno() == matricula.getIdAlumno() && mat
                        .getIdModulo() == matricula.getIdModulo());
        if (!existe) {
            addMatricula(matricula);
        } else {
            Colors.warMsg("La matricula no existe");
        }
    }

    public void eliminarMatricula(int id) {
        List<Matricula> matriculasList = getAllMatriculas();
        boolean existe = matriculasList != null
                && matriculasList.stream().anyMatch(mat -> mat.getId().equals(id + ""));
        if (existe) {
            deleteMatriculaById(id);
        } else {
            Colors.warMsg("La matricula no existe");
        }
    }

    public void actualizarMatricula(int id, String value) {
        updateMatricula(id, value);
    }

    private void addMatricula(Matricula matricula) {
        try {
            matricula.setId(obtenerMaximoId(documento) + 1 + "");
            String nuevaMatriculaXml = matricula.toXml();
            super.createResource(documento, nuevaMatriculaXml, root);
            System.out.println("Matrícula añadida con éxito.");
        } catch (Exception e) {
            Colors.errMsg("Error al añadir la matrícula.");
        }
    }

    private void deleteMatriculaById(int id) {
        try {
            String resourceName = documento;
            XMLResource resource = readResource(resourceName);
            if (resource != null) {
                Matricula matriculaToDelete = findMatriculaById(resource.getContent().toString(), id);
                if (matriculaToDelete != null) {
                    deleteResource(resourceName, String.valueOf(id), "matricula");
                    System.out.println("Matrícula eliminada con éxito.");
                } else {
                    System.out.println("No se encontró una matrícula con el ID proporcionado.");
                }
            } else {
                System.out.println("No se encontró el recurso " + resourceName);
            }
        } catch (Exception e) {
            Colors.errMsg("Error al eliminar la matrícula.");
        }
    }

    public void updateMatricula(int id, String value) {
        try {
            String resourceName = documento;
            XMLResource resource = readResource(resourceName);
            if (resource != null) {
                Matricula matriculaToDelete = findMatriculaById(resource.getContent().toString(), id);
                if (matriculaToDelete != null) {
                    super.updateFieldById(resourceName, id+"", "matricula", "notas",  value);
                    System.out.println("Matrícula eliminada con éxito.");
                } else {
                    System.out.println("No se encontró una matrícula con el ID proporcionado.");
                }
            } else {
                System.out.println("No se encontró el recurso " + resourceName);
            }
        } catch (Exception e) {
            Colors.errMsg("Error al eliminar la matrícula.");
        }
    }

    private Matricula findMatriculaById(String xmlContent, int id) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlContent));
            Document doc = builder.parse(is);

            NodeList nodeList = doc.getElementsByTagName("matricula");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element matriculaElement = (Element) nodeList.item(i);
                String idString = matriculaElement.getAttribute("id");

                try {
                    int matriculaId = Integer.parseInt(idString);

                    if (matriculaId == id) {
                        return parseMatriculaFromElement(matriculaElement);
                    }
                } catch (NumberFormatException e) {
                    Colors.errMsg("Formato incorecto id = " + idString);
                }
            }

            return null; 
        } catch (Exception e) {
            Colors.errMsg("No se ha podido encontar el id ");
            return null; 
        }
    }

    private Matricula parseMatriculaFromElement(Element matriculaElement) {
        int id = Integer.parseInt(matriculaElement.getAttribute("id"));
        int idAlumno = Integer.parseInt(getChildValue(matriculaElement, "idAlumno"));
        int idModulo = Integer.parseInt(getChildValue(matriculaElement, "idModulo"));
        String notas = getChildValue(matriculaElement, "notas");
        Matricula matricula = new Matricula(idAlumno, idModulo, notas);
        matricula.setId(id + "");

        return matricula;
    }

    private String getChildValue(Element parent, String childTagName) {
        NodeList nodeList = parent.getElementsByTagName(childTagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    private int obtenerMaximoId(String resourceName) {
        try {
            XMLResource document = Conexion.getDocument(resourceName);
            String currentContent = (String) document.getContent();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(currentContent));
            Document doc = builder.parse(is);

            NodeList nodeList = doc.getElementsByTagName("matricula");
            int maxId = 0;

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element matriculaElement = (Element) nodeList.item(i);
                String idString = matriculaElement.getAttribute("id");

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

    public List<Matricula> getMatriculasByExternalId(int id, String element) {
        try {
            List<Matricula> filteredMatriculas = new ArrayList<>();
            for (Matricula matricula : getAllMatriculas()) {
                int externalId = (element.equals("idAlumno")) ? matricula.getIdAlumno() : matricula.getIdModulo();

                if (externalId == id) {
                    filteredMatriculas.add(matricula);
                }
            }

            return filteredMatriculas;
        } catch (Exception e) {
            Colors.errMsg("Error al obtener matrículas por ID.");
            return null;
        }
    }

    public List<Matricula> getAllMatriculas() {
        try {
            XMLResource resource = readResource(documento);
            if (resource != null) {
                String xmlContent = (String) resource.getContent();
                NodeList nodeList = getElementsByTagName(xmlContent, "matricula");

                List<Matricula> matriculaList = new ArrayList<>();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element matriculaElement = (Element) nodeList.item(i);
                    Matricula matricula = parseMatriculaFromElement(matriculaElement);
                    matriculaList.add(matricula);
                }

                return matriculaList;
            } else {
                System.out.println("El documento no existe.");
                return null;
            }
        } catch (Exception e) {
            Colors.errMsg("Error al obtener todos los alumnos.");
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
