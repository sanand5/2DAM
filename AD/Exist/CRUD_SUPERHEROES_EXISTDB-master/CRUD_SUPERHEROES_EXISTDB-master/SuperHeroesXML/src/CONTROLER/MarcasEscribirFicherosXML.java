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

/**
 *
 * @author RASPUTIN
 */
public class MarcasEscribirFicherosXML {
    
       static ArrayList<Marcas> MarcasAUX;
    /**
     * @param args the command line arguments
     */
    public static void MarcasEscribirFicherosXML(ArrayList<Marcas> marcas) throws ParserConfigurationException {
        // TODO code application logic here
        //Doy memoria a el array List de donde voy a leer y crear mi XML
        
        MarcasAUX = new ArrayList();
        MarcasAUX = marcas;
        
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
            implementation.createDocument(null, "Marcas", null);
            document.setXmlVersion("1.0"); 
            
            
            for(int i=0; i<MarcasAUX.size();i++){
                
                Element raiz = 
                document.createElement("Marca"); //nodo super
                document.getDocumentElement().appendChild(raiz);

               // CrearElemento(superheroes.get(i).getGenero(), raiz, document);
               /*
              
               */
               CrearElemento("Id_Villano",Integer.toString(MarcasAUX.get(i).getId_Villano()),raiz,document);
               CrearElemento("Id_Marca",Integer.toString(MarcasAUX.get(i).getId_Marca()),raiz,document);
               CrearElemento("NombreMarca",MarcasAUX.get(i).getNombreMarca(),raiz, document);
               CrearElemento("Año_Creacion", Integer.toString(MarcasAUX.get(i).getAñoCreacion()), raiz, document);
               CrearElemento("Pelicula", Boolean.toString(MarcasAUX.get(i).isPelicula()), raiz, document);
               CrearElemento("NombreVillano",(MarcasAUX.get(i).getNombreVillano()), raiz, document);
              
            }//fin del for que recorre el vector
		
                Source source = new DOMSource(document);
                Result result = 
                new StreamResult(new java.io.File("Marcas.xml"));        
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