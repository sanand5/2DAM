/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

import MODEL.Villanos;
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
public class VillanosLeerFicherosXML {
    
    public VillanosLeerFicherosXML (){
    
    }
    
    public static ArrayList<Villanos>  LeerFicherosXML (ArrayList<Villanos>villanos) throws SAXException, IOException{
        
	 XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
	 GestionContenidoVillanos gestor= new GestionContenidoVillanos(villanos);
        
	 procesadorXML.setContentHandler(gestor);
 	 InputSource fileXML = new InputSource("Villanos.xml");	    
         procesadorXML.parse(fileXML);
        
         return villanos;
    }
	
}//fin PruebaSax1

class GestionContenidoVillanos extends DefaultHandler {
    String Fichero = "";
    Villanos nuevo;
    ArrayList<Villanos>villanosAUX;
    
             public  GestionContenidoVillanos(ArrayList<Villanos>villanos) {
	        super();
                this.villanosAUX = villanos;
                
                 //System.out.println("entra");
          
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
                
                if(Fichero.equals("Villano")){
                    
                   nuevo = new Villanos();
                }
	    } 	
	    public void endElement(String uri, String nombre, 
                          String nombreC) {
	        //System.out.printf("\tFin Elemento: %s %n", nombre);
                
                Fichero = nombre;
                
                if(Fichero.equals("Villano")){
                    
                    villanosAUX.add(nuevo);
                }
	    }	
	    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
                
		   String car=new String(ch, inicio, longitud);
               //quitar saltos de línea	
		   car = car.replaceAll("[\t\n]","");	   
		  // System.out.printf ("\t Caracteres: %s %n", car);
                   
                   if(Fichero.equals("Id_SuperHeroe")){
                       
                       nuevo.setId_SuperHeroe(Integer.parseInt(car));
                   }
                   
                    if(Fichero.equals("Id_Villano")){
                       
                       nuevo.setId_Villano(Integer.parseInt(car));
                   }
                   
                   if(Fichero.equals("Nombre")){
                       
                       nuevo.setNombreVillano(String.valueOf(car));
                   }
                   
                   if(Fichero.equals("Habilidad")){
                       
                       nuevo.setHabilidad(String.valueOf(car));
                   }
                   
                   if(Fichero.equals("Mascara")){
                       
                       nuevo.setMascara(Boolean.valueOf(car));
                   }
                   
                   if(Fichero.equals("Genero")){
                       
                       nuevo.setGenero((car).charAt(0));
                   }
                   
                   if(Fichero.equals("Nombre_SuperHeroe")){
                       
                       nuevo.setNombreSuperHeroe(String.valueOf(car));
                   }
	    }	

}//fin GestionContenido
