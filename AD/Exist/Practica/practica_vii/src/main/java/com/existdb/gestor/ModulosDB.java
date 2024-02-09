package com.existdb.gestor;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmldb.api.modules.XMLResource;

import com.existdb.modelos.*;
import com.existdb.utilidades.Colors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;

public class ModulosDB extends CrudManager<Modulo> {
    private static String documento = "modulos.xml";

    public void anadirModulo(Modulo modulo) {
        List<Modulo> modulosList = getAllModulos();
        boolean existe = modulosList != null
                && modulosList.stream().anyMatch(mod -> mod.getNombre().equals(modulo.getNombre()));
        if (!existe) {
            addModulo(modulo);
        } else {
            Colors.warMsg("El modulo ya existe");
        }
    }

    public void eliminarModulo(int id) {
        List<Modulo> modulosList = getAllModulos();
        boolean existe = modulosList != null
                && modulosList.stream().anyMatch(mod -> mod.getId().equals(id + ""));
        if (existe) {
            deleteModuloById(id);
            new MatriculasDB().getMatriculasByExternalId(id, MatriculasDB.CAMPO_MODULO);
        } else {
            Colors.warMsg("El modulo no existe");
        }
    }

    public void mostrarModulos() {
        List<Modulo> modulosList = getAllModulos();
        System.out.println("### Lista de modulos ###");
        for(int i = 1; i <= modulosList.size(); i++) {
            System.out.println(i + ".- " + modulosList.get(i).getNombre());
        }
    }

    private void addModulo(Modulo modulo) {
        try {
            modulo.setId(obtenerMaximoId(documento) + 1 + "");
            createResource(documento, modulo.toXml());
            System.out.println("Módulo agregado con éxito.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void deleteModuloById(int id) {
        try {
            // Obtén el recurso por ID y elimínalo
            XMLResource resource = readResource(documento);
            if (resource != null) {
                Modulo moduloToDelete = findModuloById(resource.getContent().toString(), id);
                if (moduloToDelete != null) {
                    deleteResource(documento, String.valueOf(id), "modulo");
                    System.out.println("Modulo eliminado con éxito.");
                } else {
                    System.out.println("No se encontró un modulo con el ID proporcionado.");
                }
            } else {
                System.out.println("No se encontró el recurso " + documento);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el modulo.");
        }
    }

    private Modulo findModuloById(String xmlContent, int id) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlContent));
            Document doc = builder.parse(is);

            NodeList nodeList = doc.getElementsByTagName("modulo");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element moduloElement = (Element) nodeList.item(i);
                String idString = moduloElement.getAttribute("id");

                try {
                    int moduloId = Integer.parseInt(idString);

                    if (moduloId == id) {
                        // Encontramos el módulo con el ID proporcionado
                        return parseModuloFromElement(moduloElement);
                    }
                } catch (NumberFormatException e) {
                    // Manejar el caso en que el atributo "id" no sea un número válido
                    e.printStackTrace();
                }
            }

            return null; // No se encontró el módulo con el ID proporcionado
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejar la excepción según tus necesidades
        }
    }

    private Modulo parseModuloFromElement(Element moduloElement) {
        int id = Integer.parseInt(moduloElement.getAttribute("id"));
        String nombre = getChildValue(moduloElement, "nombre");
        Modulo modulo = new Modulo(nombre);
        modulo.setId(id + "");

        return modulo;
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
            XMLResource document = readResource(resourceName);
            String currentContent = (String) document.getContent();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(currentContent));
            Document doc = builder.parse(is);

            NodeList nodeList = doc.getElementsByTagName("modulo");
            int maxId = 0;

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element moduloElement = (Element) nodeList.item(i);
                String idString = moduloElement.getAttribute("id");

                try {
                    int id = Integer.parseInt(idString);
                    if (id > maxId) {
                        maxId = id;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            return maxId;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Modulo getModuloById(String id) {
    List<Modulo> modulosList = getAllModulos();

    Optional<Modulo> moduloOptional = modulosList.stream()
            .filter(modulo -> modulo.getId().equals(id))
            .findFirst();

    return moduloOptional.orElse(null);
}

    public List<Modulo> getAllModulos() {
        try {
            XMLResource resource = readResource(documento);
            if (resource != null) {
                String xmlContent = (String) resource.getContent();
                NodeList nodeList = getElementsByTagName(xmlContent, "modulo");

                List<Modulo> modulosList = new ArrayList<>();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element moduloElement = (Element) nodeList.item(i);
                    Modulo modulo = parseModuloFromElement(moduloElement);
                    modulosList.add(modulo);
                }

                return modulosList;
            } else {
                System.out.println("El documento no existe.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener todos los alumnos.");
            return null;
        }
    }
}
