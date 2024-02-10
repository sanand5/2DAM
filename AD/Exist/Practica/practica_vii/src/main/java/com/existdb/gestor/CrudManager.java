package com.existdb.gestor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.existdb.utilidades.Colors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

public class CrudManager<T> {

    private Collection collection = Conexion.getCollection();

    protected void createResource(String resourceName, String xmlContent, String root) {
        collection = Conexion.getCollection();
        try {
            XMLResource resource = (XMLResource) collection.getResource(resourceName);
            Document document = null;
            if (resource == null) {
                document = createNewDocument(root);
            } else {
                document = parseXmlContent((String) resource.getContent());
            }
            Element newElement = null;
            newElement = createElementFromXml(xmlContent, document);
            document.getDocumentElement().appendChild(newElement);
            String updatedContent = null;
            updatedContent = convertDocumentToString(document);
            if (resource == null) {
                resource = (XMLResource) collection.createResource(resourceName, "XMLResource");
            }
            resource.setContent(updatedContent);
            collection.storeResource(resource);

            System.out.println("Documento creado o actualizado con éxito.");
        } catch (Exception e) {
            Colors.errMsg("Error al crear o actualizar el documento.");
        }
    }

    private Document createNewDocument(String root) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        Element rootElement = document.createElement(root);
        document.appendChild(rootElement);
        return document;
    }

    private Document parseXmlContent(String xmlContent) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlContent));
        return builder.parse(is);
    }

    private Element createElementFromXml(String xmlContent, Document document) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document newDocument = builder.parse(new InputSource(new StringReader(xmlContent)));
        return (Element) document.importNode(newDocument.getDocumentElement(), true);
    }

    private String convertDocumentToString(Document document) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        return writer.toString();
    }

    protected XMLResource readResource(String resourceName) {
        collection = Conexion.getCollection();
        try {
            return (XMLResource) collection.getResource(resourceName);
        } catch (XMLDBException e) {
            Colors.errMsg("Error al leer el documento.");
            return null;
        }
    }

    protected void updateFieldById(String resourceName, String targetId, String elementName, String fieldName,
            String newValue) {
        collection = Conexion.getCollection();
        try {
            XMLResource resource = (XMLResource) collection.getResource(resourceName);
            if (resource != null) {
                String currentContent = (String) resource.getContent();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                InputSource is = new InputSource(new StringReader(currentContent));
                Document doc = builder.parse(is);

                NodeList nodeList = doc.getElementsByTagName(elementName);

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    String idString = element.getAttribute("id");

                    if (idString.equals(targetId)) {
                        element.getElementsByTagName(fieldName).item(0).setTextContent(newValue);
                        break;
                    }
                }
                String updatedContent = convertDocumentToString(doc);
                resource.setContent(updatedContent);
                collection.storeResource(resource);
                System.out
                        .println(fieldName + " del " + elementName + " con ID " + targetId + " actualizado con éxito.");
            } else {
                System.out.println("El documento no existe.");
            }
        } catch (Exception e) {
            Colors.errMsg("Error al actualizar el campo.");
        }
    }

    protected void deleteResource(String resourceName, String targetId, String elementName) {
        collection = Conexion.getCollection();
        try {
            XMLResource resource = (XMLResource) collection.getResource(resourceName);
            if (resource != null) {
                String currentContent = (String) resource.getContent();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                InputSource is = new InputSource(new StringReader(currentContent));
                Document doc = builder.parse(is);

                NodeList nodeList = doc.getElementsByTagName(elementName);

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    String idString = element.getAttribute("id");

                    if (idString.equals(targetId)) {
                        doc.getDocumentElement().removeChild(element);
                        break;
                    }
                }

                String updatedContent = convertDocumentToString(doc);
                resource.setContent(updatedContent);
                collection.storeResource(resource);
                System.out.println(elementName + " con ID " + targetId + " eliminado con éxito.");
            } else {
                System.out.println("El documento no existe.");
            }
        } catch (Exception e) {
            Colors.errMsg("Error al eliminar el elemento.");
        }
    }

    protected NodeList getElementsByTagName(String xmlContent, String elementName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlContent));
            Document doc = builder.parse(is);

            return doc.getElementsByTagName(elementName);
        } catch (Exception e) {
            Colors.errMsg("No se ha obnetener el elemento");
            return null;
        }
    }

    public void exportToXml(String resourceName, String filePath) {
        collection = Conexion.getCollection();
        try {
            XMLResource resource = (XMLResource) collection.getResource(resourceName);

            if (resource != null) {
                String xmlContent = (String) resource.getContent();

                File file = new File(filePath);

                if (file.exists()) {
                    try (FileWriter fileWriter = new FileWriter(file, false)) {
                        fileWriter.write("");
                    } catch (IOException e) {
                        Colors.errMsg("Error al borrar el contenido del archivo.");
                        return;
                    }
                }

                try (FileWriter fileWriter = new FileWriter(file, true)) {
                    fileWriter.write(xmlContent);
                    System.out.println("Datos exportados a: " + filePath);
                } catch (IOException e) {
                    Colors.errMsg("Error al escribir en el archivo.");
                }
            } else {
                System.out.println("El recurso no existe.");
            }
        } catch (XMLDBException e) {
            Colors.errMsg("Error al leer el recurso.");
        }
    }

    public void importarDesdeXml(String resourceName, String filePath) {
        collection = Conexion.getCollection();
        try {
            File file = new File(filePath);
            String xmlContent = "";

            if (file.exists()) {
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        xmlContent += scanner.nextLine();
                    }
                } catch (IOException e) {
                    Colors.errMsg("Error al leer el archivo.");
                    return;
                }
            } else {
                System.out.println("El archivo no existe.");
                return;
            }

            XMLResource resource = (XMLResource) collection.getResource(resourceName);

            if (resource != null) {
                resource.setContent(xmlContent);
                collection.storeResource(resource);
                System.out.println("Datos importados desde: " + filePath);
            } else {
                System.out.println("El recurso no existe.");
            }
        } catch (XMLDBException e) {
            Colors.errMsg("Error al leer o actualizar el recurso.");
        }
    }

}
