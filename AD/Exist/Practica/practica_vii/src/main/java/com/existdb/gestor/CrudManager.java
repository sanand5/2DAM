package com.existdb.gestor;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

public class CrudManager<T> {

    private Collection collection = Conexion.getDatabase();

    protected void createResource(String resourceName, String xmlContent) {
    collection = Conexion.getDatabase();
        try {
            XMLResource resource = (XMLResource) collection.getResource(resourceName);

            Document document = null;
            if (resource == null) {
                try{
                    
                    document = createNewDocument();
                }catch(Exception e){
                    e.printStackTrace();
                }
            } else {
                try {
                    document = parseXmlContent((String) resource.getContent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Element newElement = null;
            try {
                newElement = createElementFromXml(xmlContent, document);
            } catch (Exception e) {
                e.printStackTrace();
            }
            document.getDocumentElement().appendChild(newElement);
            String updatedContent = null;
            try {
                updatedContent = convertDocumentToString(document);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Actualiza el contenido del recurso en la base de datos
            if (resource == null) {
                resource = (XMLResource) collection.createResource(resourceName, "XMLResource");
            }
            resource.setContent(updatedContent);
            collection.storeResource(resource);

            System.out.println("Documento creado o actualizado con éxito.");
        } catch (XMLDBException e) {
            e.printStackTrace();
            System.out.println("Error al crear o actualizar el documento.");
        }
    }

    private Document createNewDocument() throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        Element rootElement = document.createElement("root");
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
        collection = Conexion.getDatabase();
        try {
            return (XMLResource) collection.getResource(resourceName);
        } catch (XMLDBException e) {
            e.printStackTrace();
            System.out.println("Error al leer el documento.");
            return null;
        }
    }

    protected void updateResource(String resourceName, String newContent) {
        collection = Conexion.getDatabase();
        try {
            XMLResource resource = (XMLResource) collection.getResource(resourceName);
            resource.setContent(newContent);
            collection.storeResource(resource);
            System.out.println("Documento actualizado con éxito.");
        } catch (XMLDBException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el documento.");
        }
    }

    protected void deleteResource(String resourceName, String targetId, String elementName) {
        collection = Conexion.getDatabase();
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
                        // Elimina el nodo con el ID deseado
                        doc.getDocumentElement().removeChild(element);
                        break;
                    }
                }

                // Convierte el documento modificado a cadena XML y actualiza el recurso
                String updatedContent = convertDocumentToString(doc);
                resource.setContent(updatedContent);
                collection.storeResource(resource);
                System.out.println(elementName + " con ID " + targetId + " eliminado con éxito.");
            } else {
                System.out.println("El documento no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el elemento.");
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
            e.printStackTrace();
            return null;
        }
    }

}
