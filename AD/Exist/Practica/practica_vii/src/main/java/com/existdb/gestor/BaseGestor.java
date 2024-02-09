package com.existdb.gestor;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmldb.api.modules.XMLResource;

import com.existdb.modelos.*;
import com.existdb.utilidades.Colors;

public abstract class BaseGestor<T extends BaseModel> extends CrudManager<T> {
    protected abstract String getDocumento();

    protected abstract T parseFromElement(Element element);

    protected abstract String getIdAttribute();

    protected abstract String getTagName();

    public void agregar(T item) {
        List<T> lista = obtenerTodos();
        boolean existe = lista != null && lista.stream().anyMatch(i -> getIdValue(i).equals(getIdValue(item)));
        if (!existe) {
            agregarItem(item);
        } else {
            Colors.warMsg("El " + getTagName() + " ya existe");
        }
    }

    public void eliminar(int id) {
        List<T> lista = obtenerTodos();
        boolean existe = lista != null && lista.stream().anyMatch(i -> getIdValue(i).equals(String.valueOf(id)));
        if (existe) {
            eliminarPorId(id);
            eliminarRelaciones(id);
        } else {
            Colors.warMsg("El " + getTagName() + " no existe");
        }
    }

    private void agregarItem(T item) {
        try {
            item.setId(obtenerMaximoId() + 1 + "");
            createResource(getDocumento(), item.toXml());
            System.out.println(getTagName() + " agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar " + getTagName());
            e.printStackTrace();
        }
    }

    private void eliminarPorId(int id) {
        try {
            XMLResource resource = readResource(getDocumento());
            if (resource != null) {
                T itemToDelete = findItemById(resource.getContent().toString(), id);
                if (itemToDelete != null) {
                    deleteResource(getDocumento(), String.valueOf(id), getTagName());
                    System.out.println(getTagName() + " eliminado con éxito.");
                } else {
                    System.out.println("No se encontró un " + getTagName() + " con el ID proporcionado.");
                }
            } else {
                System.out.println("No se encontró el recurso " + getDocumento());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar " + getTagName());
        }
    }

    protected abstract T findItemById(String xmlContent, int id);

    protected abstract void eliminarRelaciones(int id);

    protected abstract String getIdValue(T item);

    private int obtenerMaximoId() {
        try {
            XMLResource document = Conexion.getDocument(getDocumento());
            String currentContent = (String) document.getContent();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(currentContent));
            org.w3c.dom.Document doc = builder.parse(is);

            NodeList nodeList = doc.getElementsByTagName(getTagName());
            int maxId = 0;

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String idString = element.getAttribute(getIdAttribute());

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

    protected abstract List<T> obtenerTodos();

    protected abstract String getChildValue(Element parent, String childTagName);
}
