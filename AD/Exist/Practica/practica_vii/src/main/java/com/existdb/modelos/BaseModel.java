package com.existdb.modelos;

public class BaseModel {
    protected String id;

    public BaseModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toXml() {
        return "";
    }
    
    public String toXml(String tagName, String[] propertyNames) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<").append(tagName).append(" id=\"").append(getId()).append("\">");

        for (String propertyName : propertyNames) {
            xmlBuilder.append("<").append(propertyName).append(">")
                    .append(getPropertyValue(propertyName))
                    .append("</").append(propertyName).append(">");
        }

        xmlBuilder.append("</").append(tagName).append(">");
        return xmlBuilder.toString();
    }

    protected String getPropertyValue(String propertyName) {
        return ""; 
    }
    
    
}
