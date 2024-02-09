/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

import MODEL.Superpoderes;

import java.io.IOException;

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
public class SuperPoderesLeerFicherosXML {
    
  public SuperPoderesLeerFicherosXML (){
    
    }
    
    public static ArrayList<Superpoderes>  LeerFicherosXML ( ArrayList<Superpoderes>superpoderes) throws SAXException, IOException{
        
	 XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
	 GestionContenidoSuperPoderes gestor= new GestionContenidoSuperPoderes(superpoderes);
        
	 procesadorXML.setContentHandler(gestor);
 	 InputSource fileXML = new InputSource("SuperPoderes.xml");	    
         procesadorXML.parse(fileXML);
        
         return superpoderes;
    }
	
}//fin PruebaSax1

class GestionContenidoSuperPoderes extends DefaultHandler {
    
    String Fichero = "";
    Superpoderes nuevo;
    ArrayList<Superpoderes>superpoderesAUX;
    
             public  GestionContenidoSuperPoderes(ArrayList<Superpoderes>superpoderes) {
	        super();
                this.superpoderesAUX = superpoderes;
                
                 //System.out.println("entra");
                
             }
            
	    public void startDocument() {
	        System.out.println("Comienzo del Documento XML");
	    }	    
            
	    public void endDocument() {
	        System.out.println("Final del Documento XML");
	    }	 
            
            
	    public void startElement(String uri, String nombre,String nombreC, Attributes atts) {
                
	        System.out.printf("\t Principio Elemento: %s %n",nombre);
                Fichero = nombre;
                
                if(Fichero.equals("SuperPoderes")){
                    
                   nuevo = new Superpoderes();
                }
	    } 	
	    public void endElement(String uri, String nombre, 
                          String nombreC) {
	        System.out.printf("\tFin Elemento: %s %n", nombre);
                
                Fichero = nombre;
                
                if(Fichero.equals("SuperPoderes")){
                    
                    superpoderesAUX.add(nuevo);
                }
	    }	
	    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
                
		   String car=new String(ch, inicio, longitud);
               //quitar saltos de línea	
		   car = car.replaceAll("[\t\n]","");	   
		   System.out.printf ("\t Caracteres: %s %n", car);
                   
                   if(Fichero.equals("Id_SuperHeroe")){
                       
                       nuevo.setId_SuperHeroe(Integer.parseInt(car));
                   }
                   
                    if(Fichero.equals("Id_SuperPoder")){
                       
                       nuevo.setId_SuperHeroe(Integer.parseInt(car));
                   }
                   
                   if(Fichero.equals("Nombre")){
                       
                       nuevo.setNombrePoder((car));
                   }
                   
                   if(Fichero.equals("Daño")){
                       
                       nuevo.setDaño(Integer.parseInt(car));
                   }
                   
                   if(Fichero.equals("Potencia")){
                       
                       nuevo.setPotencia(Integer.parseInt(car));
                   }
                   
                   if(Fichero.equals("Super_Heroe")){
                       
                       nuevo.setNombreSuperHeroe((car));
                   }
	    }	

}//fin GestionContenido