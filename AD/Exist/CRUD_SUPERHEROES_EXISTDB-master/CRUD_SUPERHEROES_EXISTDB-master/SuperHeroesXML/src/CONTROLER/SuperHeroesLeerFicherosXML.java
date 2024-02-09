/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLER;

import MODEL.Superheroes;
import java.io.*;
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
public class SuperHeroesLeerFicherosXML {
    
    public SuperHeroesLeerFicherosXML (){
    
    }
    
    public static void LeerFicherosXML (ArrayList<Superheroes>superheroes) throws SAXException, IOException{
        
	 XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
	 GestionContenidoSuperHeroes gestor= new GestionContenidoSuperHeroes(superheroes);
         
         //for(int j=0; j<superheroes.size(); j++)
        //System.out.println("Array superheroes nada inicar " + superheroes.get(j).getNombre() + " " + superheroes.get(j).getId_SuperHeroe());

         
	 procesadorXML.setContentHandler(gestor);
 	 InputSource fileXML = new InputSource("SuperHeroes.xml");	    
         procesadorXML.parse(fileXML);
        
         //for(int j=0; j<superheroes.size(); j++)
         //System.out.println("Array superheroes nada mas leer fichero " + superheroes.get(j).getNombre() + " " + superheroes.get(j).getId_SuperHeroe());

         
         
    }
	
}//fin PruebaSax1

class GestionContenidoSuperHeroes extends DefaultHandler {
    String Fichero = "";
    Superheroes nuevo;
    ArrayList<Superheroes>superheroesAUX;
    
    
        public  GestionContenidoSuperHeroes(ArrayList<Superheroes>superheroes) {


           super();
           this.superheroesAUX = superheroes;

           // System.out.println("entra");

           //for(int j=0; j<superheroesAUX.size(); j++)
           //System.out.println("Array superheroes nada mas leer fichero " + superheroes.get(j).getNombre() + " " + superheroes.get(j).getId_SuperHeroe());

       }
            
        @Override
        public void startDocument() {
            
            //System.out.println("Comienzo del Documento XML");
        }

        @Override
        public void endDocument() {
            //System.out.println("Final del Documento XML");
        }

        @Override
        public void startElement(String uri, String nombre,String nombreC, Attributes atts) {

            //System.out.printf("\t Principio Elemento: %s %n",nombre);
            Fichero = nombre;
           

            if(Fichero.equals("SuperHeroe")){

               nuevo = new Superheroes();
            }
        }

        @Override
        public void endElement(String uri, String nombre, 
                      String nombreC) {
            //System.out.printf("\tFin Elemento: %s %n", nombre);

            Fichero = nombre;

            if(Fichero.equals("SuperHeroe")){

                superheroesAUX.add(nuevo);
            }
        }	
        @Override
        public void characters(char[] ch, int inicio, int longitud) throws SAXException {

               String car=new String(ch, inicio, longitud);
           //quitar saltos de línea	
               car = car.replaceAll("[\t\n]","");	   
               //System.out.printf ("\t Caracteres: %s %n", car);

               if(Fichero.equals("id_SuperHeroe")){

                   nuevo.setId_SuperHeroe(Integer.parseInt(car));
               }

               if(Fichero.equals("Nombre")){

                   nuevo.setNombre(String.valueOf(car));
               }

               if(Fichero.equals("Habilidad")){

                   nuevo.setHabilidad(String.valueOf(car));
               }

               if(Fichero.equals("Capa")){

                   nuevo.setCapa(Boolean.valueOf(car));
               }

               if(Fichero.equals("Genero")){

                   nuevo.setGenero((car).charAt(0));
               }
        }	

}//fin GestionContenido


