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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
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
public class VillanosEscribirFicherosXML {
    
    static ArrayList<Villanos> villanosAUX;
    /**
     * @param args the command line arguments
     */
    public static void VillanosEscribirFicherosXML(ArrayList<Villanos> villanos) throws ParserConfigurationException {
        // TODO code application logic here
        //Doy memoria a el array List de donde voy a leer y crear mi XML
        
        villanosAUX = new ArrayList();
        villanosAUX = villanos;
        
        /*
        for(int i=0; i<superheroes.size(); i++){
            
            System.out.println("Nombre SuperHeroe " + superheroes.get(i).getNombre());
        }
        */
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  
        try{
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = 
            implementation.createDocument(null, "Villanos", null);
            document.setXmlVersion("1.0"); 
            
            
            for(int i=0; i<villanosAUX.size();i++){
                
                Element raiz = 
                document.createElement("Villano"); //nodo super
                document.getDocumentElement().appendChild(raiz);

               // CrearElemento(superheroes.get(i).getGenero(), raiz, document);
               /*
              
               */
               CrearElemento("Id_SuperHeroe",Integer.toString(villanosAUX.get(i).getId_SuperHeroe()),raiz,document);
               CrearElemento("Id_Villano",Integer.toString(villanosAUX.get(i).getId_Villano()),raiz,document);
               CrearElemento("Nombre",villanosAUX.get(i).getNombreVillano(),raiz, document);
               CrearElemento("Habilidad", villanosAUX.get(i).getHabilidad(), raiz, document);
               CrearElemento("Mascara", Boolean.toString(villanosAUX.get(i).isMascara()), raiz, document);
               CrearElemento("Genero", Character.toString(villanosAUX.get(i).getGenero()), raiz, document);
               CrearElemento("Nombre_SuperHeroe",(villanosAUX.get(i).getNombreSuperHeroe()), raiz, document);
              
            }//fin del for que recorre el vector
		
                Source source = new DOMSource(document);
                Result result = 
                new StreamResult(new java.io.File("Villanos.xml"));        
                Transformer transformer =
                TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, result);
            
        }catch(Exception e){ System.err.println("Error: "+e); }
        
        
    }
    
    public static void CrearElemento(String AtributoSuperHeroe, String contenido,Element raiz, Document document) {
        
       
        Element elem = document.createElement(AtributoSuperHeroe); 
        Text text = document.createTextNode(contenido); //damos valor
       
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor	
    }
    
   
        
   
}
