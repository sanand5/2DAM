package com.existdb.modelos;

public class Modulo {
    private String id;
    private String nombre;

    
    public Modulo(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public String toXml() {
        return "<modulo id = \"" + this.getId() + "\">" +
                "<nombre>" + this.getNombre() + "</nombre>" +
                "</modulo>";
    }
}
