public static void addPersona(Persona p) {
    if (!p.getDni().isBlank()) {
      try {
        XMLResource document = Conexion.getDocument(documento);
        String currentContent = (String) document.getContent();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(currentContent));
        Document doc = builder.parse(is);

        Element persona = doc.createElement("persona");
        p.setId(obtenerId());
        persona.setAttribute("id", String.valueOf(p.getId()));
        createElement(doc, persona, "dni", p.getDni());
        createElement(doc, persona, "nombre", p.getNombre());
        createElement(doc, persona, "nombre2", p.getNombre2());
        createElement(doc, persona, "apellido", p.getApellido1());
        createElement(doc, persona, "apellido2", p.getApellido2());

        Node personasNode = doc.getDocumentElement();
        personasNode.appendChild(persona);

        String updatedContent = convertDocumentToString(doc);

        document.setContent(updatedContent);
        Conexion.getDatabase().storeResource(document);
        System.out.println("Persona agregada al documento");

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static String convertDocumentToString(Document doc) throws Exception {
    javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
    javax.xml.transform.Transformer transformer = tf.newTransformer();
    javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
    java.io.StringWriter writer = new java.io.StringWriter();
    javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(writer);
    transformer.transform(source, result);
    return writer.toString();
  }

  private static void createElement(Document doc, Element parent, String tagName, String textContent) {
    Element element = doc.createElement(tagName);
    element.setTextContent(textContent);
    parent.appendChild(element);
  }