/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

/**
 *
 * @author Ras
 */
import MODEL.Marcas;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author RASPUTIN
 */
public class MarcasLeerFicherosXML {
    
     public MarcasLeerFicherosXML (){
    
    }
    
    public static ArrayList<Marcas>  LeerFicherosXML (ArrayList<Marcas>marcas) throws SAXException, IOException{
        
	 XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
	 GestionContenidoMarcas gestor= new GestionContenidoMarcas(marcas);
        
	 procesadorXML.setContentHandler(gestor);
 	 InputSource fileXML = new InputSource("Marcas.xml");	    
         procesadorXML.parse(fileXML);
        
         return marcas;
    }
	
}//fin PruebaSax1

class GestionContenidoMarcas extends DefaultHandler {
    String Fichero = "";
    Marcas nuevo;
    ArrayList<Marcas>marcasAUX;
    
             public  GestionContenidoMarcas(ArrayList<Marcas>marcas) {
	        super();
                this.marcasAUX = marcas;
       
             }
            
	    public void startDocument() {
	        //System.out.println("Comienzo del Documento XML");
	    }	    
	    public void endDocument() {
	        //System.out.println("Final del Documento XML");
	    }	 	    
	    public void startElement(String uri, String nombre,String nombreC, Attributes atts) {
                
	        //System.out.printf("\t Principio Elemento: %s %n",nombre);
                Fichero = nombre;
                
                if(Fichero.equals("Marca")){
                    
                   nuevo = new Marcas();
                }
	    } 	
	    public void endElement(String uri, String nombre, 
                          String nombreC) {
	        //System.out.printf("\tFin Elemento: %s %n", nombre);
                
                Fichero = nombre;
                
                if(Fichero.equals("Marca")){
                    
                    marcasAUX.add(nuevo);
                }
	    }	
	    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
                
		   String car=new String(ch, inicio, longitud);
               //quitar saltos de línea	
		   car = car.replaceAll("[\t\n]","");	   
		  // System.out.printf ("\t Caracteres: %s %n", car);
                   
                   if(Fichero.equals("Id_Villano")){
                       
                       nuevo.setId_Villano(Integer.parseInt(car));
                   }
                   
                     if(Fichero.equals("Id_Marca")){
                       
                       nuevo.setId_Marca(Integer.parseInt(car));
                   }
                   
                   if(Fichero.equals("NombreMarca")){
                       
                       nuevo.setNombreMarca(String.valueOf(car));
                   }
                   
                   if(Fichero.equals("Año_Creacion")){
                       
                       nuevo.setAñoCreacion(Integer.parseInt(car));
                   }
                   
                   if(Fichero.equals("Pelicula")){
                       
                       nuevo.setPelicula(Boolean.valueOf(car));
                   }
                   
                   if(Fichero.equals("NombreVillano")){
                       
                       nuevo.setNombreVillano((car));
                   }
	    }	

}//fin GestionContenido
