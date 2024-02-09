/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;


import CONTROLER.MarcasEscribirFicherosXML;
import CONTROLER.MarcasLeerFicherosXML;
import CONTROLER.SuperHeroesEscribirFicherosXML;
import CONTROLER.SuperHeroesLeerFicherosXML;
import CONTROLER.SuperPoderesEscribirFicherosXML;
import CONTROLER.SuperPoderesLeerFicherosXML;
import CONTROLER.VillanosEscribirFicherosXML;
import CONTROLER.VillanosLeerFicherosXML;
import MODEL.Superheroes;
import MODEL.Superpoderes;
import MODEL.Villanos;
import MODEL.Marcas;
import java.awt.Graphics;
import java.awt.Image;


import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 *
 * @author RASPUTIN
 */
public class VISTA extends javax.swing.JFrame {
    
    //FONDOJPANELS
    FondoPanel fondo1 = new FondoPanel();
    FondoPanel fondo2 = new FondoPanel();
    
    //ATRIBUTOS OBJETOS
    
    int seleccion;
    int codIniciador=0;
    int opcion = 5;
    
    //ATRIBUTOS SUPERHEROES
    private int id_SuperHeroe ;
        private String nombre;
        private String Habilidad;
        private boolean capa;
        private char genero;
        
    //ATRIBUTOS SUPERPODERES
        int id_SuperHeroeSuperPoder;
        int id_SuperPoder;
        String NombreSuperHeroePoder;
        String NombrePoder;
        int Daño;
        int Potencia;
    
    //ATRIBUTOS VILLANO
        private int Id_SuperHeroeVillano ;
        private int Id_Villano;
        private String NombreVillano;
        private String HabilidadVillano;
        private boolean mascara;
        private char generoVillano;
        private String NombreSuperHeroeVillano;
        
     //ATRIBUTOS MARCA
        private int Id_VillanoMarca;
        private int Id_Marca;
        private String NombreMarca;
        private int AñoCreacion;
        private boolean Pelicula;
        private String NombreVillanoMarca;
        
    
    //DECLARO CONEXION
   
    
    
    //DECLARO LOS MODELOS PARA ENGANCHAR CON EL LAYOUT DESPUES
     DefaultTableModel modelo1; 
     DefaultTableModel modelo2;
     DefaultTableModel modelo3;
     DefaultTableModel modelo4;
     
     //DECLARO MIS ARRAYS DE OBJETOS
     private ArrayList<Superheroes> superheroes;
     private ArrayList<Superpoderes> superpoderes;
     private ArrayList<Villanos> villanos;
     private ArrayList<Marcas> marcas;
     
     
     
    /**
     * Creates new form NUEVAInterfazUtilizable
     */
    public VISTA() throws IOException, SAXException {
        
         initComponents();
         
         
        
         this.setLocationRelativeTo(null);
        
        //Pongo los comandos a false para que no pueda escribir a noser que sea insertar o modificar
        PrimeraTablaBloquearoDesbloquearEscrituraTextField(1);
        SegundaTablaBloquearoDesbloquearEscrituraTextField(1);
        TerceraTablaBloquearoDesbloquearEscrituraTextField(1);
        CuartaTablaBloquearoDesbloquearEscrituraTextField(1);
        
        
        //Bloqueo los combobox
        jComboBoxSuperPoderes.setEnabled(false);
        jComboBoxVillanos.setEnabled(false);
        jComboBoxMarcas.setEnabled(false);
        
        
         //Forma de no dejar escribir en la tabla
        
        modelo1 = new DefaultTableModel(){
            
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==4){
                    
                    return true;
                }else{
                    
                    return false;
                }
            }
            
        };
        modelo2 = new DefaultTableModel(){
            
             @Override
            public boolean isCellEditable(int row, int column) {
                if(column==4){
                    
                    return true;
                }else{
                    
                    return false;
                }
            }
            
        };
        modelo3 = new DefaultTableModel(){
            
             @Override
            public boolean isCellEditable(int row, int column) {
                if(column==5){
                    
                    return true;
                }else{
                    
                    return false;
                }
            }
            
        };
        
         modelo4 = new DefaultTableModel(){
            
             @Override
            public boolean isCellEditable(int row, int column) {
                if(column==4){
                    
                    return true;
                }else{
                    
                    return false;
                }
            }
            
        };
       
       
        //INTRODUZC NOMBRES COLUMNAS , ENLAZO TABLAS Y DOY MEMORIA AL ARRAY CON SUS VALORES
        
        CrearTablaSuperHeroes();
        
        CrearTablaSuperPoderes();
        
        CrearTablaVillanos();
        
        CrearTablaMarcas();
        
        //ESCRIBIR Y LEER FICHEROS
        //EscribirFicherosSuperHeroes(); Al finalizar programa
        //LeerFicheroSuperHeroes();
    }
    
   
    
    //CREAR TABLAS
    
    public void CrearTablaSuperHeroes() throws SAXException, IOException{
        
        modelo1.addColumn("NAME");
        modelo1.addColumn("HABILIDAD");
        modelo1.addColumn("GENERO");
        modelo1.addColumn("CAPA");
        this.TablaSuperHeroes.setModel(modelo1);

        this.superheroes = new ArrayList();
        
        SuperHeroesLeerFicherosXML.LeerFicherosXML(superheroes);
   
        //Comprobacion arrays ids
        
        //for(int j=0; j<superheroes.size(); j++)
        //System.out.println("Array superheroes nada mas leer fichero " + superheroes.get(j).getNombre() + " " + superheroes.get(j).getId_SuperHeroe());
        
        
        this.ActualizarTablaSuperHeroes(superheroes);
   
       
        
    }
    
    public void CrearTablaSuperPoderes() throws SAXException, IOException{
    
        modelo2.addColumn("NAME");
        modelo2.addColumn("DAÑO");
        modelo2.addColumn("POTENCIA");
        modelo2.addColumn("SUPERHEROE");
        this.TablaSuperPoderes.setModel(modelo2);
        
        this.superpoderes = new ArrayList();
       
       //System.out.println("dimension superpoderes = " + superpoderes.size());
        SuperPoderesLeerFicherosXML.LeerFicherosXML(superpoderes);
        
         //Comprobacion arrays ids
         //Comprobacion arrays ids
        // System.out.println("dimension superpoderes = " + superpoderes.size());
        
         for(int j=0; j<superpoderes.size(); j++)
        System.out.println("Array superpoderes nada mas leer la base de datos " + superpoderes.get(j).getNombrePoder()+ " " + superpoderes.get(j).getId_SuperPoder());
       
      
        
        this.ActualizarTablaSuperPoderes(superpoderes);
        
        //this.DatosSuperPoderes();
       
    }
    
     public void CrearTablaVillanos() throws SAXException, IOException{
    
        modelo3.addColumn("NAME");
        modelo3.addColumn("HABILIDAD");
        modelo3.addColumn("GENERO");
        modelo3.addColumn("MASCARA");
        modelo3.addColumn("SUPERHEROE");
        
        this.Tablavillanos.setModel(modelo3);
        
         //System.out.println("ENTRA");
        this.villanos = new ArrayList();
           
        villanos = VillanosLeerFicherosXML.LeerFicherosXML(villanos);
    
        // System.out.println("Tamaño array villanos " + " = " + villanos.size());
         //Comprobacion arrays ids
         /*
         for(int j=0; j<villanos.size(); j++)
        System.out.println("Array Villanos nada mas leer fichero " + villanos.get(j).getNombreVillano()+ " " + villanos.get(j).getIdVillano());
         */
        
       
        this.ActualizarTablaVillanos(villanos);
        
    }

    public void CrearTablaMarcas() throws SAXException, IOException{
    
        modelo4.addColumn("NAME");
        modelo4.addColumn("AÑO");
        modelo4.addColumn("PELICULA");
        modelo4.addColumn("VILLANOS");
       
        this.TablaMarcas.setModel(modelo4);
        
        this.marcas = new ArrayList();
        
        marcas = MarcasLeerFicherosXML.LeerFicherosXML(marcas);
       
        //RELLENO LOS COMBOX
        this.ActualizarTablaMarcas(marcas);
        
    }
    
    
    
    //LLENAR COMBOXSSS SUPERPODERES Y VILLANOS
     public void LlenarComboBox(){
         seleccion = TablaSuperHeroes.getSelectedRow();
         
         jComboBoxSuperPoderes.removeAllItems();//BORRAR LOS DATOS PRIMERAMENTE
         jComboBoxVillanos.removeAllItems();
        
         for(int i=0; i<superheroes.size();i++){
             
              jComboBoxSuperPoderes.addItem(superheroes.get(i).getNombre());
              jComboBoxVillanos.addItem(superheroes.get(i).getNombre());
         }
     }
     
     //LLENAR COMBOBOXXX MARCAS
     public void LlenarComboBoxMarcas(){
         
         jComboBoxMarcas.removeAllItems();//BORRAR LOS DATOS PRIMERAMENTE
         
         
         for(int i=0; i<villanos.size();i++){
             
             
               jComboBoxMarcas.addItem(villanos.get(i).getNombreVillano());
         }
     }

     
        
    //METODOS  
  
      public int TamañoSuperheroes(){
       
        return superheroes.size();
    }

    public boolean BuscarEnVector(){
        boolean encontrado = false;
         
            for(int i=0; i<superheroes.size() && encontrado == false; i++){

                  if(!this.TextFieldName.getText().equals(superheroes.get(i).getNombre())){

                      encontrado = true;
                  }
            }
         
        return encontrado;
    }
   
    
    //ACTUALIZAR TABLAS
    
    
    public void ActualizarTablaSuperHeroes(ArrayList<Superheroes> superheroes){
        
    
         //System.out.println("filas: "+ modelo1.getRowCount());
        
       
        for (int i = modelo1.getRowCount() -1; i >= 0; i--){
           
             modelo1.removeRow(i);
        }
        
       if(superheroes.size()>0){
        
            for (int i=0;i<superheroes.size(); i++){ //recorro los arrays  
               
               //Inserto en la tabla los valores del objeto
               Object[] row = {superheroes.get(i).getNombre(), superheroes.get(i).getHabilidad(), superheroes.get(i).getGenero(), superheroes.get(i).isCapa()};  

               modelo1.addRow(row);


           }
       }
        
          //System.out.println("filas: "+ modelo1.getRowCount());
    }
    
     public void ActualizarTablaSuperPoderes(ArrayList<Superpoderes> superpoderes){
         
        for (int i = modelo2.getRowCount() -1; i >= 0; i--){
           
             modelo2.removeRow(i);
        }
         
        
         
            for (int i=0;i<superpoderes.size(); i++){ //recorro los arrays  

                //System.out.println(superpoderes.get(i).getNomPoder() + " " + superpoderes.get(i).getDanio()+ " " + superpoderes.get(i).getPotencia()+ " " + superpoderes.get(i).getSuperheroes().getNomSuperheroe());

                //ACTUALIZO MODO FICHEROSS AÑADO LAS NUEVAS FILAS
                Object[] row = {superpoderes.get(i).getNombrePoder(), superpoderes.get(i).getDaño(), superpoderes.get(i).getPotencia(), superpoderes.get(i).getNombreSuperHeroe()};
                modelo2.addRow(row);



            }  
            
    }
    
     public void ActualizarTablaVillanos(ArrayList<Villanos> villanos){
         
         for (int i = modelo3.getRowCount() -1; i >= 0; i--){
           
             modelo3.removeRow(i);
        }
         
        
         
        for (int i=0;i<villanos.size(); i++){ //recorro los arrays  
            
  
            //ACTUALIZO MODO FICHEROSS AÑADO LAS NUEVAS FILAS
            Object[] row = {villanos.get(i).getNombreVillano(), villanos.get(i).getHabilidad(), villanos.get(i).getGenero(), villanos.get(i).isMascara(),villanos.get(i).getNombreSuperHeroe()};
            modelo3.addRow(row);
          
        }  
            
    } 
    
     public void ActualizarTablaMarcas(ArrayList<Marcas> marcas){
         
         for (int i = modelo4.getRowCount() -1; i >= 0; i--){
           
             modelo4.removeRow(i);
        }
         
        for (int i=0;i<marcas.size(); i++){ //recorro los arrays  
            
          
            //ACTUALIZO MODO FICHEROSS AÑADO LAS NUEVAS FILAS
            Object[] row = {marcas.get(i).getNombreMarca(), marcas.get(i).getAñoCreacion(), marcas.get(i).isPelicula(), marcas.get(i).getNombreVillano()};
            modelo4.addRow(row);
            //Inserto en la tabla los valores del objeto
             /*
            modelo3.setValueAt(getVillano.getNombreVillano(),i,0);
            modelo3.setValueAt(getVillano.getHabilidad(),i,1);
            modelo3.setValueAt(getVillano.getGenero(),i,2);
            modelo3.setValueAt(getVillano.isMascara(),i,3);
            modelo3.setValueAt( jComboBoxVillanos.getItemAt(getVillano.getId_SuperHeroe()),i,4);
             */
            
             
        }  
            
            
    }  
    
    //ACTUALIZAR IDS
     
    public void SuperHeroesActualizarIDS(ArrayList<Superheroes> superheroes){
        
        for(int i=0; i<superheroes.size(); i++){
            
            superheroes.get(i).setId_SuperHeroe(i);
        }
    } 
     
    public void SuperPoderesActualizarIDS(ArrayList<Superpoderes> superpoderes){
        
        for(int i=0; i<superpoderes.size(); i++){
            
            superpoderes.get(i).setId_SuperPoder(i);
        }
    } 
    
    public void VillosActualizarIDS(ArrayList<Villanos> villanos){
        
        for(int i=0; i<villanos.size(); i++){
            
           villanos.get(i).setId_Villano(i);
        }
    } 
    
    public void MarcasActualizarIDS(ArrayList<Marcas> marcas){
        
        for(int i=0; i<marcas.size(); i++){
            
           marcas.get(i).setId_Marca(i);
        }
    } 
     
    //LIMPIAR TEXT FIELD
    public void SuperHeroesLimpiarTextField(){
        
        this.TextFieldName.setText("");
        this.TextFieldHabilidad.setText("");
        this.TextFieldGenero.setText("");
        this.TextFieldCapa.setText("");
    }
    
    public void SuperPoderesLimpiarTextField(){
        
        this.TextFieldName1.setText("");
        this.TextFieldDaño.setText("");
         this.TextFieldPotencia.setText("");
    }
    
     public void VillanosLimpiarTextField(){
        
        this.TextFieldVillanoName.setText("");
        this.TextFieldVillanoHabilidad.setText("");
        this.TextFieldVillanoGenero.setText("");
        this.TextFieldVillanoMascara.setText("");
    }
    
    public void MarcasLimpiarTextField(){
        
        this.TextFieldMarcaName.setText("");
        this.TextFieldMarcaAño.setText("");
        this.TextFieldMarcaPelicula.setText("");
       
    }
    
    //BLOQUEADORES DE ESCRITURA DE TODAS LAS TABLAS
    
    public void PrimeraTablaBloquearoDesbloquearEscrituraTextField(int i){
            //DESBLOQUEO i = 0
            //BLOQUEADO  i!= 0
        
            if(i == 0){
                
                this.TextFieldName.setEditable(true);
                this.TextFieldHabilidad.setEditable(true);
                this.TextFieldGenero.setEditable(true);
                this.TextFieldCapa.setEditable(true);
                
            }else{
                
                this.TextFieldName.setEditable(false);
                this.TextFieldHabilidad.setEditable(false);
                this.TextFieldGenero.setEditable(false);
                this.TextFieldCapa.setEditable(false);
            }
    }
    
     public void SegundaTablaBloquearoDesbloquearEscrituraTextField(int i){
            //DESBLOQUEO i = 0
            //BLOQUEADO  i!= 0
        
            if(i == 0){
                
                this.TextFieldName1.setEditable(true);
                this.TextFieldDaño.setEditable(true);
                this.TextFieldPotencia.setEditable(true);
               
                
            }else{
                
                 this.TextFieldName1.setEditable(false);
                this.TextFieldDaño.setEditable(false);
                this.TextFieldPotencia.setEditable(false);
            }
    }
    
     public void TerceraTablaBloquearoDesbloquearEscrituraTextField(int i){
            //DESBLOQUEO i = 0
            //BLOQUEADO  i!= 0
        
            if(i == 0){
                
                this.TextFieldVillanoName.setEditable(true);
                this.TextFieldVillanoHabilidad.setEditable(true);
                this.TextFieldVillanoGenero.setEditable(true);
                this.TextFieldVillanoMascara.setEditable(true);
              
               
                
            }else{
                
                this.TextFieldVillanoName.setEditable(false);
                this.TextFieldVillanoHabilidad.setEditable(false);
                this.TextFieldVillanoGenero.setEditable(false);
                this.TextFieldVillanoMascara.setEditable(false);
            }
    
     }
    
     
     
     public void CuartaTablaBloquearoDesbloquearEscrituraTextField(int i){
            //DESBLOQUEO i = 0
            //BLOQUEADO  i!= 0
        
            if(i == 0){
                
                this.TextFieldMarcaName.setEditable(true);
                this.TextFieldMarcaAño.setEditable(true);
                this.TextFieldMarcaPelicula.setEditable(true);
                
            }else{
                
                this.TextFieldMarcaName.setEditable(false);
                this.TextFieldMarcaAño.setEditable(false);
                this.TextFieldMarcaPelicula.setEditable(false);
            }
    
     }
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelSuperHeroes = new FondoPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSuperHeroes = new javax.swing.JTable();
        jButtonSuperHeroesInsertar = new javax.swing.JButton();
        jButtonSuperHeroesEliminar = new javax.swing.JButton();
        jButtonSuperHeroesModificar = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        TextFieldName = new javax.swing.JTextField();
        jLabelGenero = new javax.swing.JLabel();
        TextFieldGenero = new javax.swing.JTextField();
        jLabelSuperPoder = new javax.swing.JLabel();
        TextFieldHabilidad = new javax.swing.JTextField();
        jLabelCapa = new javax.swing.JLabel();
        TextFieldCapa = new javax.swing.JTextField();
        jButtonSuperHeroesGuardar = new javax.swing.JButton();
        jButtonSuperHeroesCancelar = new javax.swing.JButton();
        jButtonCerrarVentana = new javax.swing.JButton();
        jPanelSuperPoderes = new FondoPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaSuperPoderes = new javax.swing.JTable();
        jButtonSuperPoderesInsertar = new javax.swing.JButton();
        jButtonSuperPoderesEliminar = new javax.swing.JButton();
        jButtonSuperPoderesModificar = new javax.swing.JButton();
        jLabelName1 = new javax.swing.JLabel();
        TextFieldName1 = new javax.swing.JTextField();
        jLabelDaño = new javax.swing.JLabel();
        TextFieldDaño = new javax.swing.JTextField();
        TextFieldPotencia = new javax.swing.JTextField();
        jLabelPotencia = new javax.swing.JLabel();
        jButtonSuperPoderesGuardar = new javax.swing.JButton();
        jButtonSuperPoderesCancelar = new javax.swing.JButton();
        jComboBoxSuperPoderes = new javax.swing.JComboBox<>();
        jButtonCerrarVentana1 = new javax.swing.JButton();
        jPanelVillanos = new FondoPanel();
        jButtonVillanoInsertar = new javax.swing.JButton();
        jButtonVillanosEliminar = new javax.swing.JButton();
        jButtonVillanosModificar = new javax.swing.JButton();
        TextFieldVillanoName = new javax.swing.JTextField();
        TextFieldVillanoHabilidad = new javax.swing.JTextField();
        TextFieldVillanoGenero = new javax.swing.JTextField();
        TextFieldVillanoMascara = new javax.swing.JTextField();
        jLabelNameVillano = new javax.swing.JLabel();
        jLabelGenero1 = new javax.swing.JLabel();
        jLabelSuperPoder1 = new javax.swing.JLabel();
        jLabelCapa1 = new javax.swing.JLabel();
        jButtonVillanosGuardar = new javax.swing.JButton();
        jButtonVillanosCancelar = new javax.swing.JButton();
        jComboBoxVillanos = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        Tablavillanos = new javax.swing.JTable();
        jButtonCerrarVentana2 = new javax.swing.JButton();
        jPanelMarca = new FondoPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaMarcas = new javax.swing.JTable();
        jButtonMarcaInsertar = new javax.swing.JButton();
        jButtonMarcaEliminar = new javax.swing.JButton();
        jButtonMarcaModificar = new javax.swing.JButton();
        TextFieldMarcaName = new javax.swing.JTextField();
        jLabelNameMarca = new javax.swing.JLabel();
        TextFieldMarcaAño = new javax.swing.JTextField();
        TextFieldMarcaPelicula = new javax.swing.JTextField();
        jLabelAño = new javax.swing.JLabel();
        jLabelPelicula = new javax.swing.JLabel();
        jButtonMarcaGuardar = new javax.swing.JButton();
        jButtonMarcaCancelar = new javax.swing.JButton();
        jComboBoxMarcas = new javax.swing.JComboBox<>();
        jButtonCerrarVentana3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 55));

        jPanelSuperHeroes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSuperHeroesMouseClicked(evt);
            }
        });

        TablaSuperHeroes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NAME", "HABILIDAD", "GENERO", "CAPA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaSuperHeroes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaSuperHeroesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaSuperHeroes);
        if (TablaSuperHeroes.getColumnModel().getColumnCount() > 0) {
            TablaSuperHeroes.getColumnModel().getColumn(0).setResizable(false);
            TablaSuperHeroes.getColumnModel().getColumn(1).setResizable(false);
            TablaSuperHeroes.getColumnModel().getColumn(2).setResizable(false);
            TablaSuperHeroes.getColumnModel().getColumn(3).setResizable(false);
        }

        jButtonSuperHeroesInsertar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperHeroesInsertar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonSuperHeroesInsertar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperHeroesInsertar.setText("INSERT");
        jButtonSuperHeroesInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperHeroesInsertarActionPerformed(evt);
            }
        });

        jButtonSuperHeroesEliminar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperHeroesEliminar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonSuperHeroesEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperHeroesEliminar.setText("DELETE");
        jButtonSuperHeroesEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperHeroesEliminarActionPerformed(evt);
            }
        });

        jButtonSuperHeroesModificar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperHeroesModificar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonSuperHeroesModificar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperHeroesModificar.setText("UPDATE");
        jButtonSuperHeroesModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperHeroesModificarActionPerformed(evt);
            }
        });

        jLabelName.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName.setText("NAME");

        TextFieldName.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldName.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldName.setForeground(new java.awt.Color(255, 255, 255));

        jLabelGenero.setFont(new java.awt.Font("Dosis", 0, 24)); // NOI18N
        jLabelGenero.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGenero.setText("GENERO");

        TextFieldGenero.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldGenero.setFont(new java.awt.Font("Dosis", 0, 18)); // NOI18N
        TextFieldGenero.setForeground(new java.awt.Color(255, 255, 255));
        TextFieldGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldGeneroActionPerformed(evt);
            }
        });

        jLabelSuperPoder.setFont(new java.awt.Font("Dosis", 0, 24)); // NOI18N
        jLabelSuperPoder.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSuperPoder.setText("HABILIDAD");

        TextFieldHabilidad.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldHabilidad.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldHabilidad.setForeground(new java.awt.Color(255, 255, 255));

        jLabelCapa.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelCapa.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCapa.setText("CAPA");

        TextFieldCapa.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldCapa.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldCapa.setForeground(new java.awt.Color(255, 255, 255));

        jButtonSuperHeroesGuardar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperHeroesGuardar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonSuperHeroesGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperHeroesGuardar.setText("SAVE");
        jButtonSuperHeroesGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperHeroesGuardarActionPerformed(evt);
            }
        });

        jButtonSuperHeroesCancelar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperHeroesCancelar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonSuperHeroesCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperHeroesCancelar.setText("CANCEL");
        jButtonSuperHeroesCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperHeroesCancelarActionPerformed(evt);
            }
        });

        jButtonCerrarVentana.setBackground(new java.awt.Color(0, 0, 153));
        jButtonCerrarVentana.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonCerrarVentana.setText("EXIT");
        jButtonCerrarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarVentanaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSuperHeroesLayout = new javax.swing.GroupLayout(jPanelSuperHeroes);
        jPanelSuperHeroes.setLayout(jPanelSuperHeroesLayout);
        jPanelSuperHeroesLayout.setHorizontalGroup(
            jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperHeroesLayout.createSequentialGroup()
                .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSuperHeroesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanelSuperHeroesLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelName)
                            .addComponent(jLabelGenero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSuperHeroesGuardar)
                            .addGroup(jPanelSuperHeroesLayout.createSequentialGroup()
                                .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextFieldGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelSuperHeroesLayout.createSequentialGroup()
                                        .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelSuperPoder)
                                            .addComponent(jLabelCapa))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TextFieldHabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TextFieldCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelSuperHeroesLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jButtonSuperHeroesCancelar)))
                                .addGap(279, 279, 279)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCerrarVentana)
                    .addComponent(jButtonSuperHeroesModificar)
                    .addComponent(jButtonSuperHeroesEliminar)
                    .addComponent(jButtonSuperHeroesInsertar))
                .addGap(131, 131, 131))
        );
        jPanelSuperHeroesLayout.setVerticalGroup(
            jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperHeroesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelSuperHeroesLayout.createSequentialGroup()
                        .addComponent(jButtonSuperHeroesInsertar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSuperHeroesEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSuperHeroesModificar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelName))
                    .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextFieldHabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelSuperPoder)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextFieldGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelGenero))
                    .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextFieldCapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelCapa)
                        .addComponent(jButtonCerrarVentana)))
                .addGap(19, 19, 19)
                .addGroup(jPanelSuperHeroesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSuperHeroesGuardar)
                    .addComponent(jButtonSuperHeroesCancelar))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jButtonCerrarVentana.getAccessibleContext().setAccessibleName("SAVE DATA");
        jButtonCerrarVentana.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane1.addTab("SUPERHEROES", jPanelSuperHeroes);

        jPanelSuperPoderes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSuperPoderesMouseClicked(evt);
            }
        });

        TablaSuperPoderes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NAME", "DAÑO", "POTENCIA", "SUPERHEROE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaSuperPoderes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaSuperPoderesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaSuperPoderes);

        jButtonSuperPoderesInsertar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperPoderesInsertar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonSuperPoderesInsertar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperPoderesInsertar.setText("INSERT");
        jButtonSuperPoderesInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperPoderesInsertarActionPerformed(evt);
            }
        });

        jButtonSuperPoderesEliminar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperPoderesEliminar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonSuperPoderesEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperPoderesEliminar.setText("DELETE");
        jButtonSuperPoderesEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperPoderesEliminarActionPerformed(evt);
            }
        });

        jButtonSuperPoderesModificar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperPoderesModificar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonSuperPoderesModificar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperPoderesModificar.setText("UPDATE");
        jButtonSuperPoderesModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperPoderesModificarActionPerformed(evt);
            }
        });

        jLabelName1.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelName1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName1.setText("NAME");

        TextFieldName1.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldName1.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldName1.setForeground(new java.awt.Color(255, 255, 255));

        jLabelDaño.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelDaño.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDaño.setText("DAÑO");

        TextFieldDaño.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldDaño.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldDaño.setForeground(new java.awt.Color(255, 255, 255));

        TextFieldPotencia.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldPotencia.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldPotencia.setForeground(new java.awt.Color(255, 255, 255));
        TextFieldPotencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldPotenciaActionPerformed(evt);
            }
        });

        jLabelPotencia.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelPotencia.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPotencia.setText("POTENCIA");

        jButtonSuperPoderesGuardar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperPoderesGuardar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonSuperPoderesGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperPoderesGuardar.setText("SAVE");
        jButtonSuperPoderesGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperPoderesGuardarActionPerformed(evt);
            }
        });

        jButtonSuperPoderesCancelar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonSuperPoderesCancelar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonSuperPoderesCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSuperPoderesCancelar.setText("CANCEL");
        jButtonSuperPoderesCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuperPoderesCancelarActionPerformed(evt);
            }
        });

        jComboBoxSuperPoderes.setBackground(new java.awt.Color(153, 0, 153));
        jComboBoxSuperPoderes.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        jComboBoxSuperPoderes.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxSuperPoderes.setToolTipText("");
        jComboBoxSuperPoderes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxSuperPoderesMouseClicked(evt);
            }
        });
        jComboBoxSuperPoderes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSuperPoderesActionPerformed(evt);
            }
        });

        jButtonCerrarVentana1.setBackground(new java.awt.Color(0, 0, 153));
        jButtonCerrarVentana1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonCerrarVentana1.setText("EXIT");
        jButtonCerrarVentana1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarVentana1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSuperPoderesLayout = new javax.swing.GroupLayout(jPanelSuperPoderes);
        jPanelSuperPoderes.setLayout(jPanelSuperPoderesLayout);
        jPanelSuperPoderesLayout.setHorizontalGroup(
            jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperPoderesLayout.createSequentialGroup()
                .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSuperPoderesLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanelSuperPoderesLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelName1)
                            .addComponent(jLabelPotencia))
                        .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSuperPoderesLayout.createSequentialGroup()
                                .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelSuperPoderesLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(TextFieldName1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabelDaño)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperPoderesLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonSuperPoderesGuardar)
                                        .addGap(61, 61, 61)))
                                .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelSuperPoderesLayout.createSequentialGroup()
                                        .addComponent(TextFieldDaño, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(88, 88, 88)
                                        .addComponent(jComboBoxSuperPoderes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonSuperPoderesCancelar)))
                            .addGroup(jPanelSuperPoderesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCerrarVentana1)
                    .addComponent(jButtonSuperPoderesModificar)
                    .addComponent(jButtonSuperPoderesEliminar)
                    .addComponent(jButtonSuperPoderesInsertar))
                .addGap(129, 129, 129))
        );
        jPanelSuperPoderesLayout.setVerticalGroup(
            jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperPoderesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelSuperPoderesLayout.createSequentialGroup()
                        .addComponent(jButtonSuperPoderesInsertar)
                        .addGap(28, 28, 28)
                        .addComponent(jButtonSuperPoderesEliminar)
                        .addGap(32, 32, 32)
                        .addComponent(jButtonSuperPoderesModificar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName1)
                    .addComponent(TextFieldName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDaño)
                    .addComponent(TextFieldDaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSuperPoderes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCerrarVentana1))
                .addGap(30, 30, 30)
                .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPotencia))
                .addGap(18, 18, 18)
                .addGroup(jPanelSuperPoderesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSuperPoderesGuardar)
                    .addComponent(jButtonSuperPoderesCancelar))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SUPERPODERES", jPanelSuperPoderes);

        jPanelVillanos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelVillanosMouseClicked(evt);
            }
        });

        jButtonVillanoInsertar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonVillanoInsertar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonVillanoInsertar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVillanoInsertar.setText("INSERT");
        jButtonVillanoInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVillanoInsertarActionPerformed(evt);
            }
        });

        jButtonVillanosEliminar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonVillanosEliminar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonVillanosEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVillanosEliminar.setText("DELETE");
        jButtonVillanosEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVillanosEliminarActionPerformed(evt);
            }
        });

        jButtonVillanosModificar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonVillanosModificar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonVillanosModificar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVillanosModificar.setText("UPDATE");
        jButtonVillanosModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVillanosModificarActionPerformed(evt);
            }
        });

        TextFieldVillanoName.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldVillanoName.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldVillanoName.setForeground(new java.awt.Color(255, 255, 255));
        TextFieldVillanoName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldVillanoNameActionPerformed(evt);
            }
        });

        TextFieldVillanoHabilidad.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldVillanoHabilidad.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldVillanoHabilidad.setForeground(new java.awt.Color(255, 255, 255));

        TextFieldVillanoGenero.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldVillanoGenero.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldVillanoGenero.setForeground(new java.awt.Color(255, 255, 255));
        TextFieldVillanoGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldVillanoGeneroActionPerformed(evt);
            }
        });

        TextFieldVillanoMascara.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldVillanoMascara.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        TextFieldVillanoMascara.setForeground(new java.awt.Color(255, 255, 255));
        TextFieldVillanoMascara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldVillanoMascaraActionPerformed(evt);
            }
        });

        jLabelNameVillano.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelNameVillano.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNameVillano.setText("NAME");

        jLabelGenero1.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelGenero1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGenero1.setText("GENERO");

        jLabelSuperPoder1.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelSuperPoder1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSuperPoder1.setText("HABILIDAD");

        jLabelCapa1.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelCapa1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCapa1.setText("MASCARA");

        jButtonVillanosGuardar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonVillanosGuardar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonVillanosGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVillanosGuardar.setText("SAVE");
        jButtonVillanosGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVillanosGuardarActionPerformed(evt);
            }
        });

        jButtonVillanosCancelar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonVillanosCancelar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonVillanosCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVillanosCancelar.setText("CANCEL");
        jButtonVillanosCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVillanosCancelarActionPerformed(evt);
            }
        });

        jComboBoxVillanos.setBackground(new java.awt.Color(153, 0, 153));
        jComboBoxVillanos.setFont(new java.awt.Font("Dosis", 1, 18)); // NOI18N
        jComboBoxVillanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVillanosActionPerformed(evt);
            }
        });

        Tablavillanos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NAME", "HABILIDAD", "GENERO", "MASCARA", "SUPERHEROES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tablavillanos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablavillanosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(Tablavillanos);
        if (Tablavillanos.getColumnModel().getColumnCount() > 0) {
            Tablavillanos.getColumnModel().getColumn(0).setResizable(false);
            Tablavillanos.getColumnModel().getColumn(1).setResizable(false);
            Tablavillanos.getColumnModel().getColumn(2).setResizable(false);
            Tablavillanos.getColumnModel().getColumn(3).setResizable(false);
            Tablavillanos.getColumnModel().getColumn(4).setResizable(false);
        }

        jButtonCerrarVentana2.setBackground(new java.awt.Color(0, 0, 153));
        jButtonCerrarVentana2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonCerrarVentana2.setText("EXIT");
        jButtonCerrarVentana2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarVentana2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelVillanosLayout = new javax.swing.GroupLayout(jPanelVillanos);
        jPanelVillanos.setLayout(jPanelVillanosLayout);
        jPanelVillanosLayout.setHorizontalGroup(
            jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVillanosLayout.createSequentialGroup()
                .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVillanosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5))
                    .addGroup(jPanelVillanosLayout.createSequentialGroup()
                        .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                        .addComponent(jLabelNameVillano)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TextFieldVillanoName, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                        .addComponent(jLabelGenero1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TextFieldVillanoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)))
                                .addGap(57, 57, 57)
                                .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                        .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelSuperPoder1)
                                            .addComponent(jLabelCapa1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TextFieldVillanoMascara, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                                .addComponent(TextFieldVillanoHabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(89, 89, 89)
                                                .addComponent(jComboBoxVillanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jButtonVillanosCancelar)))
                                .addGap(281, 281, 281))
                            .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(jButtonVillanosGuardar)))
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCerrarVentana2)
                    .addComponent(jButtonVillanosEliminar)
                    .addComponent(jButtonVillanosModificar)
                    .addComponent(jButtonVillanoInsertar))
                .addGap(29, 29, 29))
        );
        jPanelVillanosLayout.setVerticalGroup(
            jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVillanosLayout.createSequentialGroup()
                .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVillanosLayout.createSequentialGroup()
                        .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jButtonVillanoInsertar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonVillanosEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonVillanosModificar))
                            .addGroup(jPanelVillanosLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldVillanoName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldVillanoHabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNameVillano)
                            .addComponent(jLabelSuperPoder1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxVillanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVillanosLayout.createSequentialGroup()
                        .addComponent(jButtonCerrarVentana2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldVillanoMascara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCapa1)
                    .addComponent(TextFieldVillanoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGenero1))
                .addGap(90, 90, 90)
                .addGroup(jPanelVillanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVillanosGuardar)
                    .addComponent(jButtonVillanosCancelar))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("VILLANOS", jPanelVillanos);

        jPanelMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelMarcaMouseClicked(evt);
            }
        });

        TablaMarcas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NAME", "AÑO ", "PELICULA", "VILLANOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaMarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMarcasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaMarcas);

        jButtonMarcaInsertar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonMarcaInsertar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonMarcaInsertar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMarcaInsertar.setText("INSERT");
        jButtonMarcaInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMarcaInsertarActionPerformed(evt);
            }
        });

        jButtonMarcaEliminar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonMarcaEliminar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonMarcaEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMarcaEliminar.setText("DELETE");
        jButtonMarcaEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMarcaEliminarActionPerformed(evt);
            }
        });

        jButtonMarcaModificar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonMarcaModificar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonMarcaModificar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMarcaModificar.setText("UPDATE");
        jButtonMarcaModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMarcaModificarActionPerformed(evt);
            }
        });

        TextFieldMarcaName.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldMarcaName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextFieldMarcaName.setForeground(new java.awt.Color(255, 255, 255));
        TextFieldMarcaName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldMarcaNameActionPerformed(evt);
            }
        });

        jLabelNameMarca.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelNameMarca.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNameMarca.setText("NAME");

        TextFieldMarcaAño.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldMarcaAño.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextFieldMarcaAño.setForeground(new java.awt.Color(255, 255, 255));

        TextFieldMarcaPelicula.setBackground(new java.awt.Color(0, 0, 0));
        TextFieldMarcaPelicula.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextFieldMarcaPelicula.setForeground(new java.awt.Color(255, 255, 255));
        TextFieldMarcaPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldMarcaPeliculaActionPerformed(evt);
            }
        });

        jLabelAño.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelAño.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAño.setText("AÑO");

        jLabelPelicula.setFont(new java.awt.Font("Dosis", 1, 24)); // NOI18N
        jLabelPelicula.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPelicula.setText("PELICULA");

        jButtonMarcaGuardar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonMarcaGuardar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonMarcaGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMarcaGuardar.setText("SAVE");
        jButtonMarcaGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMarcaGuardarActionPerformed(evt);
            }
        });

        jButtonMarcaCancelar.setBackground(new java.awt.Color(51, 0, 51));
        jButtonMarcaCancelar.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jButtonMarcaCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMarcaCancelar.setText("CANCEL");
        jButtonMarcaCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMarcaCancelarActionPerformed(evt);
            }
        });

        jComboBoxMarcas.setBackground(new java.awt.Color(153, 0, 153));
        jComboBoxMarcas.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jComboBoxMarcas.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMarcasActionPerformed(evt);
            }
        });

        jButtonCerrarVentana3.setBackground(new java.awt.Color(0, 0, 153));
        jButtonCerrarVentana3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonCerrarVentana3.setText("EXIT");
        jButtonCerrarVentana3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarVentana3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMarcaLayout = new javax.swing.GroupLayout(jPanelMarca);
        jPanelMarca.setLayout(jPanelMarcaLayout);
        jPanelMarcaLayout.setHorizontalGroup(
            jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMarcaLayout.createSequentialGroup()
                .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMarcaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                        .addGap(62, 62, 62))
                    .addGroup(jPanelMarcaLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelNameMarca)
                            .addComponent(jLabelPelicula))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMarcaLayout.createSequentialGroup()
                                .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextFieldMarcaName, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(jButtonMarcaGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelMarcaLayout.createSequentialGroup()
                                        .addComponent(jLabelAño)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TextFieldMarcaAño, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(114, 114, 114)
                                        .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonMarcaCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(TextFieldMarcaPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCerrarVentana3)
                    .addComponent(jButtonMarcaInsertar)
                    .addComponent(jButtonMarcaEliminar)
                    .addComponent(jButtonMarcaModificar))
                .addGap(67, 67, 67))
        );
        jPanelMarcaLayout.setVerticalGroup(
            jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMarcaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMarcaLayout.createSequentialGroup()
                        .addComponent(jButtonMarcaInsertar)
                        .addGap(26, 26, 26)
                        .addComponent(jButtonMarcaEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jButtonMarcaModificar))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldMarcaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNameMarca)
                    .addComponent(TextFieldMarcaAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAño)
                    .addComponent(jComboBoxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCerrarVentana3))
                .addGap(18, 18, 18)
                .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldMarcaPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPelicula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(jPanelMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMarcaGuardar)
                    .addComponent(jButtonMarcaCancelar))
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("MARCA", jPanelMarca);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSuperHeroesInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperHeroesInsertarActionPerformed
        // TODO add your handling code here:
        
        opcion = 0;
        
        //Bloqeo los botones Modificar y Eliminar
    jButtonSuperHeroesModificar.setEnabled(false);
    jButtonSuperHeroesEliminar.setEnabled(false);
    
   
    
    //Pongo los cmapos en vacio 
    this.SuperHeroesLimpiarTextField();
        
    //Desbloqueo los "TextField" para poder editarlos
     this.PrimeraTablaBloquearoDesbloquearEscrituraTextField(0);
    //Traigo los valores de cada uno de los textfield y me creo las variables para poder mandarselas al constructor
                            //e introducirlo en el vector para evitar repetidos
        
    }//GEN-LAST:event_jButtonSuperHeroesInsertarActionPerformed

    private void jButtonSuperHeroesEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperHeroesEliminarActionPerformed
       
        int contador = 0;
        
        //Primero realizamos un filtro por si no se ha realizado
        //ninguna tabla no te deje eliminar ninguna fila
        
        if(TablaSuperHeroes.getSelectedRow() != -1){
            
          
            
           //ELIMINACION DE LAS OTRAS TABLAS   
          
            //SUPERPODERES
            
            for(int i=0; i<superpoderes.size();i++){
                
              
            
                //System.out.println("COMPARACION : " );
                //System.out.println("SELECCIONADO : " + "Nombre SuperHeroe : " + superheroes.get(TablaSuperHeroes.getSelectedRow()).getNombre() + " " + "Id_SuperHeroe : " + superheroes.get(TablaSuperHeroes.getSelectedRow()).getId_SuperHeroe());
                //System.out.println("Nombre SuperPoder : " + superpoderes.get(i).getNombrePoder() + " " + "Id_SuperPoder : " + superpoderes.get(i).getId_SuperHeroe()+ "FK : " + superpoderes.get(i).getId_SuperHeroe());
              
                
                //Comprobar si tiene alguna entidad con el mismo id en esa tabla
                
               if(superheroes.get(TablaSuperHeroes.getSelectedRow()).getId_SuperHeroe() == superpoderes.get(i).getId_SuperHeroe()){
                
                    if(contador < 1)
                        JOptionPane.showMessageDialog(null, "CUIDADO Ha Eliminado todos los datos relacionados con esta ENTIDAD", "alert", JOptionPane.ERROR_MESSAGE);
                         contador++;
                    System.out.println("Eliminado!!");
                //ELIMINACION SEGUNDA TABLA
    
                superpoderes.remove(i);
                //this.ActualizarTablaSuperHeroes(superheroes);
               
                
               }
            }
            
            contador = 0;
            //VILLANOS
            for(int i=0; i<villanos.size();i++){
                
              
            
                //System.out.println("COMPARACION : " );
                //System.out.println("SELECCIONADO : " + "Nombre SuperHeroe : " + superheroes.get(TablaSuperHeroes.getSelectedRow()).getNombre() + " " + "Id_SuperHeroe : " + superheroes.get(TablaSuperHeroes.getSelectedRow()).getId_SuperHeroe());
                //System.out.println("Nombre villanos : " + villanos.get(i).getNombreVillano()+ " " + "Id_Villano : " + villanos.get(i).getId_Villano()+ "FK : " + villanos.get(i).getId_SuperHeroe());
              
                
                //Comprobar si tiene alguna entidad con el mismo id en esa tabla
                
               if(superheroes.get(TablaSuperHeroes.getSelectedRow()).getId_SuperHeroe() == villanos.get(i).getId_SuperHeroe()){
                
                    if(contador < 1)
                        JOptionPane.showMessageDialog(null, "CUIDADO Ha Eliminado todos los datos relacionados con esta ENTIDAD", "alert", JOptionPane.ERROR_MESSAGE);
                         contador++;
                         
                    System.out.println("Eliminado!!");
                //ELIMINACION SEGUNDA TABLA
    
                villanos.remove(i);
                //this.ActualizarTablaSuperHeroes(superheroes);
               
                
               }
            }
            
            this.superheroes.remove(superheroes.get(TablaSuperHeroes.getSelectedRow()));
           
          
            this.ActualizarTablaSuperHeroes(superheroes);
            this.ActualizarTablaSuperPoderes(superpoderes);
            this.ActualizarTablaVillanos(villanos);
            
            //Actualizo el combobox de los objetos relacionados
            LlenarComboBox();
          
            
            //Pongo los huecos en blanco despues de Eliminar en la tabla y en el vector
            this.SuperHeroesLimpiarTextField();
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila", "alert", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonSuperHeroesEliminarActionPerformed

    private void jButtonSuperHeroesModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperHeroesModificarActionPerformed
        // TODO add your handling code here:
         //Filtro con mensaje si no ha seleccionado ninguna fila
        if(TablaSuperHeroes.getSelectedRow() != -1){
            
            //Bloqueo los demas botones para que el usuario no tenga acceso en ese momento hasta que le de a guardar
            jButtonSuperHeroesInsertar.setEnabled(false);
            jButtonSuperHeroesEliminar.setEnabled(false);
           
            
            //Desbloqueo los textfield para poder modificarlos
            this.PrimeraTablaBloquearoDesbloquearEscrituraTextField(0);

                //this.superheroes.get(Tabla.getSelectedRow()).setNombre(TextFieldName.getText());
                //this.superheroes.get(Tabla.getSelectedRow()).setHabilidad(TextFieldHabilidad.getText());
                //this.superheroes.get(Tabla.getSelectedRow()).setGenero(Character.toString(TextFieldGenero.getText()));
                //this.superheroes.get(Tabla.getSelectedRow()).setCapa(Boolean.toStringTextFieldCapa.getText());
        }else{
            
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila", "alert", JOptionPane.ERROR_MESSAGE);
        }        
            
    }//GEN-LAST:event_jButtonSuperHeroesModificarActionPerformed

    private void jButtonSuperHeroesGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperHeroesGuardarActionPerformed
     
        //Creo nuevas variables guardando lo introducido por el usuario 
       //para utilizarlas mas comodamente en el modulo
        boolean encontrado = false;
       
        String nombre = this.TextFieldName.getText();
        String Habilidad = this.TextFieldHabilidad.getText();
        boolean capa = Boolean.valueOf(this.TextFieldCapa.getText());
        //Esta sentencia no funciona. ?
        char genero = this.TextFieldGenero.getText().charAt(0);
        
        //System.out.println(genero);
        
        
        if(jButtonSuperHeroesInsertar.isEnabled() == true){
            
        //Aqui contemplo el caso para si estan las celdas vacias no me deje insertar nada
        
            if (!"".equals(this.TextFieldName.getText().trim()) && !"".equals(this.TextFieldHabilidad.getText().trim())
                 && !"".equals(this.TextFieldGenero.getText().trim()) && !"".equals(this.TextFieldCapa.getText().trim()) ) {
        
            
                 if(this.TextFieldGenero.getText().length() == 1 && this.TextFieldGenero.getText().charAt(0) == 'H' || this.TextFieldGenero.getText().charAt(0) == 'M'){

                    
                              
                               //BUSQUEDA NOMBRE Recorro EL ARRAY SUPERHEROES 
                                   for(int i=0; i<superheroes.size() && encontrado == false; i++){


                                   //Compruebo si el supeheroe existe en el sistema
                                       if(!this.TextFieldName.getText().equals(superheroes.get(i).getNombre())){

                                           //DEBUG COMO RECORRE EL BUCKLE


                                       }else{

                                           //for de debug para saber los superheroes que hay cuando EL NOMBRE YA EXISTE
                                           for(int j=0; j<superheroes.size(); j++)
                                           System.out.println( "superheroes que hay cuando EL NOMBRE YA EXISTE" + superheroes.get(j).getNombre() + superheroes.get(j).getId_SuperHeroe());

                                           JOptionPane.showMessageDialog(null, "Lo sentimos este nombre ya existe", "alert", JOptionPane.ERROR_MESSAGE);
                                           //System.out.println("Lo sentimos este nombreVillano ya existe");
                                            encontrado = true;

                                       }   
                     }

                     if(encontrado == false){

                            //Llamo a la clase object para dar espacio de memoria a la fila que voy a insertar
                            Object[] row = { this.TextFieldName.getText(), this.TextFieldHabilidad.getText(), this.TextFieldGenero.getText(), this.TextFieldCapa.getText()};  

                         
                            // Añado la nueva fila con los datos del NUEVO objeto superheroe a la tabla
                             modelo1.addRow(row);
                            
                             //AñadirSuperHeroesBaseDeDATOSYARRAY
                            
                             
                            //Actualizo el combobox de los objetos relacionados
                             LlenarComboBox();
                             
                             //Construyo un objeto de tipo usuario con los valores del usuario para introducirlo en el array
                             Superheroes nuevo = new Superheroes(nombre, Habilidad, capa, this.TextFieldGenero.getText().charAt(0)); 
                            
                            //Inserto el objeto en el array
                            this.superheroes.add(nuevo);
                             
                            nuevo.setId_SuperHeroe(superheroes.size()-1);

                            ActualizarTablaSuperHeroes(superheroes);
                                
                            //Una vez introducido el nuevo superheroe bloqueo y pongo en blanco de nuevo los TextField
                            this.SuperHeroesLimpiarTextField();

                            this.PrimeraTablaBloquearoDesbloquearEscrituraTextField(1);
                            
                            

                     }
                 }else{
                        JOptionPane.showMessageDialog(null, "Datos en los campos Incorrectos!", "Alert", JOptionPane.ERROR_MESSAGE); 
                 }
                   }else{
                        //BLOQUEO EL BOTON EN GUARDAR PARA PREVENIR ERRORES EN TIEMPO DE EJECUCION
                        jButtonSuperHeroesModificar.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "No puedes insertar campos vacios", "Alert", JOptionPane.ERROR_MESSAGE);  
                 }
            
        }
        
        if(jButtonSuperHeroesModificar.isEnabled() == true){
           
          
            //Modifico el superheroe seleccionado en el array
            this.superheroes.get(TablaSuperHeroes.getSelectedRow()).setNombre(this.TextFieldName.getText());
            this.superheroes.get(TablaSuperHeroes.getSelectedRow()).setHabilidad(this.TextFieldHabilidad.getText());
            this.superheroes.get(TablaSuperHeroes.getSelectedRow()).setGenero(this.TextFieldGenero.getText().charAt(0));
            this.superheroes.get(TablaSuperHeroes.getSelectedRow()).setCapa(Boolean.valueOf(this.TextFieldName.getText()));
         
            //Refresco el Array en el Layout
            this.ActualizarTablaSuperHeroes(superheroes);
            
            // System.out.println("Nombre : " + superheroes.get(posicion).getNomSuperheroe() + "ID = " + superheroes.get(posicion).getIdSuperheroe() );
            
            //Modifico en la base de datos
            
        }
        
        
        //Desbloqueo los botones
            jButtonSuperHeroesInsertar.setEnabled(true);
            jButtonSuperHeroesModificar.setEnabled(true);
            jButtonSuperHeroesEliminar.setEnabled(true);
            
        //Bloqueo la escritura
        //BLOQUEO LA ESCRITURA
       PrimeraTablaBloquearoDesbloquearEscrituraTextField(1);
    }//GEN-LAST:event_jButtonSuperHeroesGuardarActionPerformed

    private void jButtonSuperHeroesCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperHeroesCancelarActionPerformed
        // TODO add your handling code here:
          
        this.SuperHeroesLimpiarTextField();
        this.PrimeraTablaBloquearoDesbloquearEscrituraTextField(1);
        //Desbloqueo los botones
            jButtonSuperHeroesInsertar.setEnabled(true);
            jButtonSuperHeroesModificar.setEnabled(true);
            jButtonSuperHeroesEliminar.setEnabled(true);
    }//GEN-LAST:event_jButtonSuperHeroesCancelarActionPerformed

    private void TablaSuperHeroesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaSuperHeroesMouseClicked
        // TODO add your handling code here:
        seleccion = TablaSuperHeroes.getSelectedRow();
       
        this.TextFieldName.setText(superheroes.get(TablaSuperHeroes.getSelectedRow()).getNombre());
        this.TextFieldHabilidad.setText(superheroes.get(TablaSuperHeroes.getSelectedRow()).getHabilidad());
        this.TextFieldCapa.setText(Boolean.toString(superheroes.get(TablaSuperHeroes.getSelectedRow()).isCapa()));
        this.TextFieldGenero.setText(Character.toString(superheroes.get(TablaSuperHeroes.getSelectedRow()).getGenero()));
        
       
        //this.ActualizarTablaSuperHeroes(superheroes);
    }//GEN-LAST:event_TablaSuperHeroesMouseClicked

    private void TablaSuperPoderesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaSuperPoderesMouseClicked
        // Inserto el superpoderseleccionado en los text FIELD con los datos respectivos en cada uno
       
        seleccion = TablaSuperPoderes.getSelectedRow();
        
        System.out.println("seleccion = " + seleccion);
        
        jComboBoxSuperPoderes.removeAllItems();
        this.TextFieldName1.setText(superpoderes.get(TablaSuperPoderes.getSelectedRow()).getNombrePoder());
        this.TextFieldDaño.setText(Integer.toString(superpoderes.get(TablaSuperPoderes.getSelectedRow()).getDaño()));
        this.TextFieldPotencia.setText(Integer.toString(superpoderes.get(TablaSuperPoderes.getSelectedRow()).getPotencia()));
        jComboBoxSuperPoderes.addItem(superpoderes.get(seleccion).getNombreSuperHeroe());
        //this.superheroes.get(TablaSuperHeroes.getSelectedRow()).setNombre(this.TextFieldName.getText());
       
    }//GEN-LAST:event_TablaSuperPoderesMouseClicked

    private void jButtonSuperPoderesInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperPoderesInsertarActionPerformed
     
        //Bloqeo los botones Modificar y Eliminar
    jButtonSuperPoderesModificar.setEnabled(false);
    jButtonSuperPoderesEliminar.setEnabled(false); 
    
    //DESBLOQUEO EL COMBOBOX AL INSERTAR Y LO RELLENO CON LOS VALORES DEL PRIMERO
    jComboBoxSuperPoderes.setEnabled(true);
    jComboBoxSuperPoderes.removeAllItems();
    this.LlenarComboBox();
    
    //Pongo los cmapos en vacio 
    this.SuperPoderesLimpiarTextField();
        
    //Desbloqueo los "TextField" para poder editarlos
    this.SegundaTablaBloquearoDesbloquearEscrituraTextField(0);
    //Traigo los valores de cada uno de los textfield y me creo las variables para poder mandarselas al constructor
                            //e introducirlo en el vector para evitar repetidos
        
    }//GEN-LAST:event_jButtonSuperPoderesInsertarActionPerformed

    private void jButtonSuperPoderesEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperPoderesEliminarActionPerformed
        // TODO add your handling code here:
         
        //Primero realizamos un filtro por si no se ha realizado
        //ninguna tabla no te deje eliminar ninguna fila
        
        
        if(TablaSuperPoderes.getSelectedRow() != -1){
            
            
           
           superpoderes.remove(seleccion);
         
           
           //Actualizo la tabla
           this.ActualizarTablaSuperPoderes(superpoderes);
            
            //BLoqueo el combo box y lo dejo limpio
            jComboBoxSuperPoderes.setEnabled(false);
            jComboBoxSuperPoderes.removeAllItems();
            
            /*
            System.out.println("posicion = " + posicion);
            System.out.println("nombre poder antes de eliminar  "+ this.superpoderes.get(posicion).getNomPoder());
            */
            
           
            
            //Pongo los huecos en blanco despues de Eliminar en la tabla y en el vector
            this.SuperPoderesLimpiarTextField();
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila", "alert", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonSuperPoderesEliminarActionPerformed

    private void jButtonSuperPoderesModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperPoderesModificarActionPerformed
        // TODO add your handling code here:
           //Filtro con mensaje si no ha seleccionado ninguna fila
        if(TablaSuperPoderes.getSelectedRow() != -1){
            
            //Bloqueo los demas botones para que el usuario no tenga acceso en ese momento hasta que le de a guardar
            jButtonSuperPoderesInsertar.setEnabled(false);
            jButtonSuperPoderesEliminar.setEnabled(false);
           
            //DESBLOQUEO EL COMBOBOX AL INSERTAR Y LO RELLENO CON LOS VALORES DEL PRIMERO
            jComboBoxSuperPoderes.setEnabled(true);
            jComboBoxSuperPoderes.removeAllItems();
            this.LlenarComboBox();
            
            //Desbloqueo los textfield para poder modificarlos
            this.SegundaTablaBloquearoDesbloquearEscrituraTextField(0);

               
        }else{
            
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila", "alert", JOptionPane.ERROR_MESSAGE);
        }        

    }//GEN-LAST:event_jButtonSuperPoderesModificarActionPerformed

    private void jButtonSuperPoderesGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperPoderesGuardarActionPerformed
         // TODO add your handling code here:
        
         seleccion = jComboBoxSuperPoderes.getSelectedIndex();
        //System.out.println("Posicion del jcombobox =" + posicion);
        //System.out.println("SUPERHEROE NOMBRE " + superheroes.get(posicion).getNomSuperheroe() + " ID " + superheroes.get(posicion).getIdSuperheroe());

      
       
        
        boolean encontrado = false;
        
        
       
     
        
        //Esta sentencia no funciona. ?
        //char genero = this.TextFieldGenero.getText().charAt(0);
        
        //System.out.println(genero);
        
        
        if(jButtonSuperPoderesInsertar.isEnabled() == true){
        //Aqui contemplo el caso para si estan las celdas vacias no me deje insertar nada
        
            if (!"".equals(this.TextFieldName1.getText().trim()) && !"".equals(this.TextFieldDaño.getText().trim())
                 && !"".equals(this.TextFieldPotencia.getText().trim())) {

                               
                                //BUSQUEDA NOMBRE Recorro EL ARRAY SUPERHEROES 
                                //BUSQUEDA NOMBRE Recorro EL ARRAY SUPERHEROES 
                                   for(int i=0; i<superpoderes.size() && encontrado == false; i++){


                                   //Compruebo si el nombreVillano existe en el sistema
                                       if(!this.TextFieldName1.getText().equals(superpoderes.get(i).getNombrePoder())){

                                           //DEBUG COMO RECORRE EL BUCKLE


                                       }else{

                                           //for de debug para saber los superheroes que hay cuando EL NOMBRE YA EXISTE
                                           for(int j=0; j<superpoderes.size(); j++){};
                                          // System.out.println(superpoderes.get(j).getNombrePoder() + superpoderes.get(j).getId_SuperPoder());
                                           //System.out.println("entra");
                                           JOptionPane.showMessageDialog(null, "Lo sentimos este nombre ya existe", "alert", JOptionPane.ERROR_MESSAGE);
                                           //System.out.println("Lo sentimos este nombreVillano ya existe");
                                            encontrado = true;

                                       }   
                     }

                     if(encontrado == false){
                      
                          
                            NombrePoder = this.TextFieldName1.getText();
                            Daño = Integer.parseInt(this.TextFieldDaño.getText());
                            Potencia = Integer.parseInt(this.TextFieldPotencia.getText());
                     
                             
                             //Construyo un objeto de tipo superpoder enlazado con la tabla superheroes y los atributos introducidos por el usuario
                             Superpoderes nuevo = new Superpoderes(NombrePoder, Daño, Potencia, jComboBoxSuperPoderes.getItemAt(seleccion)); 
                             

                            //Inserto el objeto en el array
                            this.superpoderes.add(nuevo);
                             
                            nuevo.setId_SuperPoder(superpoderes.size()-1);

                            ActualizarTablaSuperPoderes(superpoderes);
                            
                             //Actualizo el combobox de los objetos relacionados
                             LlenarComboBox();

                            //Una vez introducido el nuevo superheroe bloqueo y pongo en blanco de nuevo los TextField
                            SuperPoderesLimpiarTextField();

                            SegundaTablaBloquearoDesbloquearEscrituraTextField(1);

                     }
               
                   }else{
                        jButtonSuperPoderesModificar.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "No puedes insertar campos vacios", "Alert", JOptionPane.ERROR_MESSAGE);  
                 }
            
        }
        
        if(jButtonSuperPoderesModificar.isEnabled() == true && encontrado == false){
         
       
            //Modifico el superpoder seleccionado
            
            this.superpoderes.get(TablaSuperPoderes.getSelectedRow()).setNombrePoder(this.TextFieldName1.getText());
            /*
            System.out.println("");
            System.out.println("");
            System.out.println("nombre"+this.TextFieldName.getText() );
            */
            this.superpoderes.get(TablaSuperPoderes.getSelectedRow()).setDaño(Integer.parseInt(this.TextFieldDaño.getText()));
            this.superpoderes.get(TablaSuperPoderes.getSelectedRow()).setPotencia(Integer.parseInt(this.TextFieldPotencia.getText()));
            this.superpoderes.get(TablaSuperPoderes.getSelectedRow()).setId_SuperHeroe(seleccion);
            this.superpoderes.get(TablaSuperPoderes.getSelectedRow()).setNombreSuperHeroe(jComboBoxSuperPoderes.getItemAt(seleccion));
            
            
            //Refresco el Array en el Layout
            this.ActualizarTablaSuperPoderes(superpoderes);
        }
        
        
        //Desbloqueo los botones
            jButtonSuperPoderesInsertar.setEnabled(true);
            jButtonSuperPoderesModificar.setEnabled(true);
            jButtonSuperPoderesEliminar.setEnabled(true);
            
        //Bloqueo ComboBox
            jComboBoxSuperPoderes.setEnabled(false);
            
             //this.ActualizarTablaSuperPoderes(superpoderes);
            
        //BLOQUEO Y LIMPIO LA ESCRITURA
        SegundaTablaBloquearoDesbloquearEscrituraTextField(1);
        this.SuperPoderesLimpiarTextField();
        
    }//GEN-LAST:event_jButtonSuperPoderesGuardarActionPerformed

    private void jButtonSuperPoderesCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuperPoderesCancelarActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
          
        this.SuperPoderesLimpiarTextField();
        this.SegundaTablaBloquearoDesbloquearEscrituraTextField(1);
        //Desbloqueo los botones
            jButtonSuperPoderesInsertar.setEnabled(true);
            jButtonSuperPoderesModificar.setEnabled(true);
            jButtonSuperPoderesEliminar.setEnabled(true);
            jComboBoxSuperPoderes.setEnabled(false);
       
        
    }//GEN-LAST:event_jButtonSuperPoderesCancelarActionPerformed

    private void TextFieldPotenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldPotenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldPotenciaActionPerformed

    private void jComboBoxSuperPoderesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSuperPoderesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSuperPoderesActionPerformed

    private void jComboBoxSuperPoderesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxSuperPoderesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSuperPoderesMouseClicked

    private void TablaMarcasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMarcasMouseClicked
       
        seleccion = TablaMarcas.getSelectedRow();
        
        //System.out.println("seleccion" + seleccion);
        //System.out.println("nombre villano asignado = " + marcas.get(seleccion).getVillanos().getNomVillano());
        
        jComboBoxMarcas.removeAll();
        
        this.TextFieldMarcaName.setText(marcas.get(seleccion).getNombreMarca());
        this.TextFieldMarcaAño.setText(Integer.toString(marcas.get(seleccion).getAñoCreacion()));
        this.TextFieldMarcaPelicula.setText(Boolean.toString(marcas.get(seleccion).isPelicula()));
        
        jComboBoxMarcas.addItem(marcas.get(seleccion).getNombreVillano());
        
    }//GEN-LAST:event_TablaMarcasMouseClicked

    private void jButtonMarcaInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMarcaInsertarActionPerformed
    
        jButtonMarcaModificar.setEnabled(false);
        jButtonMarcaEliminar.setEnabled(false); 
        
         //DESBLOQUEO EL COMBOBOX AL INSERTAR Y LO RELLENO CON LOS VALORES DEL PRIMERO
        jComboBoxMarcas.setEnabled(true);
        jComboBoxMarcas.removeAllItems();
        this.LlenarComboBoxMarcas();
        
        //Pongo los cmapos en vacio 
        this.MarcasLimpiarTextField();

        //Desbloqueo los "TextField" para poder editarlos
        this.CuartaTablaBloquearoDesbloquearEscrituraTextField(0);
        //Traigo los valores de cada uno de los textfield y me creo las variables para poder mandarselas al constructor
          
    }//GEN-LAST:event_jButtonMarcaInsertarActionPerformed

    private void jButtonMarcaEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMarcaEliminarActionPerformed
        // TODO add your handling code here:
           //Primero realizamos un filtro por si no se ha realizado
        //ninguna tabla no te deje eliminar ninguna fila
        
        if(TablaMarcas.getSelectedRow() != -1){
            
      
          
           //Elimino del array
            marcas.remove(seleccion);
            modelo4.removeRow(TablaMarcas.getSelectedRow());
            
            //Pongo los huecos en blanco despues de Eliminar en la tabla y en el vector
            this.MarcasLimpiarTextField();
            
           
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila", "alert", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonMarcaEliminarActionPerformed

    private void TextFieldMarcaNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldMarcaNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldMarcaNameActionPerformed

    private void TextFieldMarcaPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldMarcaPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldMarcaPeliculaActionPerformed

    private void jButtonMarcaGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMarcaGuardarActionPerformed
        // TODO add your handling code here:
         boolean encontrado = false;
        seleccion = jComboBoxMarcas.getSelectedIndex();
       
         //System.out.println("this.TextFieldMarcaName.getText() " + this.TextFieldMarcaName.getText());
        
        if(jButtonMarcaInsertar.isEnabled() == true){
        //Aqui contemplo el caso para si estan las celdas vacias no me deje insertar nada
        
            if (!"".equals(this.TextFieldMarcaName.getText().trim()) && !"".equals(this.TextFieldMarcaAño.getText().trim())
                 && !"".equals(this.TextFieldMarcaPelicula.getText().trim())) {

                
                                 
                 //BUSQUEDA NOMBRE Recorro EL ARRAY SUPERHEROES 
                                   for(int i=0; i<marcas.size() && encontrado == false; i++){


                                   //Compruebo si el nombreVillano existe en el sistema
                                       if(!this.TextFieldMarcaName.getText().equals(marcas.get(i).getNombreMarca())){

                                           //DEBUG COMO RECORRE EL BUCKLE


                                       }else{

                                           //for de debug para saber los superheroes que hay cuando EL NOMBRE YA EXISTE
                                           for(int j=0; j<marcas.size(); j++)
                                           System.out.println(marcas.get(j).getNombreMarca() + marcas.get(j).getId_Villano());

                                           JOptionPane.showMessageDialog(null, "Lo sentimos este nombre ya existe", "alert", JOptionPane.ERROR_MESSAGE);
                                           //System.out.println("Lo sentimos este nombreVillano ya existe");
                                            encontrado = true;

                                       }   
                                    }

                     if(encontrado == false){
                               //Seleccion del combobox pero no tengo aun el id del objeto padre seleccionado es justo la posicion que se encuentra en el vector
                            int seleccion = jComboBoxMarcas.getSelectedIndex();
                                                       
                              //Llamo a la clase object para dar espacio de memoria a la fila que voy a insertar
                            Object[] row = { this.TextFieldMarcaName.getText(), this.TextFieldMarcaAño.getText(), this.TextFieldMarcaPelicula.getText(),jComboBoxMarcas.getItemAt(seleccion)};  
                                                                                                                                            //Traigo el string del item seleccionado en el comobox
                            // Añado la nueva fila con los datos del NUEVO objeto superheroe a la tabla
                            modelo4.addRow(row);
                            
                            
                            NombreMarca = this.TextFieldMarcaName.getText();
                            AñoCreacion =Integer.parseInt(this.TextFieldMarcaAño.getText());
                            Pelicula = Boolean.valueOf( this.TextFieldMarcaPelicula.getText());
                            
                            //ASOCIAMOS EL OBJETO VILLANO CORRSPONIDENTE A LA MARCA
                            
                            //Construyo un objeto de tipo superpoder enlazado con la tabla superheroes y los atributos introducidos por el usuario
                             Marcas nuevo = new Marcas( NombreMarca, AñoCreacion, Pelicula,  jComboBoxMarcas.getItemAt(seleccion)); 
                             

                            //Inserto el objeto en el array
                             this.marcas.add(nuevo);
                             
                             //Guardo el tamaño del array de superpodere e inserto el nuevo id del superpoder 
                            int tamaño = marcas.size();
                            nuevo.setId_Marca(tamaño-1);
                            this.ActualizarTablaMarcas(marcas);
                            //Una vez introducido el nuevo superheroe bloqueo y pongo en blanco de nuevo los TextField
                            MarcasLimpiarTextField();

                            CuartaTablaBloquearoDesbloquearEscrituraTextField(1);

                     }
                
                   }else{
                        JOptionPane.showMessageDialog(null, "No puedes insertar campos vacios", "Alert", JOptionPane.ERROR_MESSAGE);  
                
                    }
        }
     
        if(jButtonMarcaModificar.isEnabled() == true && encontrado == false){
          
            //Modifico el superheroe seleccionado
            this.marcas.get(TablaMarcas.getSelectedRow()).setNombreMarca(this.TextFieldMarcaName.getText());
            this.marcas.get(TablaMarcas.getSelectedRow()).setAño(Integer.valueOf(this.TextFieldMarcaAño.getText()));
            this.marcas.get(TablaMarcas.getSelectedRow()).setPelicula(Boolean.valueOf(this.TextFieldMarcaPelicula.getText()));
            this.marcas.get(TablaMarcas.getSelectedRow()).setId_Villano((seleccion));
            this.marcas.get(TablaMarcas.getSelectedRow()).setNombreVillano(jComboBoxMarcas.getItemAt(seleccion));
             //Refresco el Array en el Layout
            this.ActualizarTablaMarcas(marcas);
        } 
           
       
          //Desbloqueo los botones
            jButtonMarcaInsertar.setEnabled(true);
            jButtonMarcaModificar.setEnabled(true);
            jButtonMarcaEliminar.setEnabled(true);
         
          //Bloqueo ComboBox
            jComboBoxMarcas.setEnabled(false);
            
             //this.ActualizarTablaSuperPoderes(superpoderes);
            
        //BLOQUEO Y LIMPIO LA ESCRITURA
       CuartaTablaBloquearoDesbloquearEscrituraTextField(1);
        this.MarcasLimpiarTextField();
    }//GEN-LAST:event_jButtonMarcaGuardarActionPerformed

    private void jButtonMarcaCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMarcaCancelarActionPerformed
        // TODO add your handling code here:
           this.MarcasLimpiarTextField();
        this.TerceraTablaBloquearoDesbloquearEscrituraTextField(1);
        //Desbloqueo los botones
            jButtonMarcaInsertar.setEnabled(true);
            jButtonMarcaModificar.setEnabled(true);
            jButtonMarcaEliminar.setEnabled(true);
            jComboBoxMarcas.setEnabled(false);
    }//GEN-LAST:event_jButtonMarcaCancelarActionPerformed

    private void jComboBoxMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMarcasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMarcasActionPerformed

    private void jButtonMarcaModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMarcaModificarActionPerformed
        // TODO add your handling code here:
         if(TablaMarcas.getSelectedRow() != -1){
            
            //Bloqueo los demas botones para que el usuario no tenga acceso en ese momento hasta que le de a guardar
            jButtonMarcaInsertar.setEnabled(false);
            jButtonMarcaEliminar.setEnabled(false);
            
            //DESBLOQUEO EL COMBOBOX AL INSERTAR Y LO RELLENO CON LOS VALORES DEL PRIMERO
            jComboBoxMarcas.setEnabled(true);
            jComboBoxMarcas.removeAllItems();
            this.LlenarComboBoxMarcas();
            
            //Desbloqueo los textfield para poder modificarlos
            this.CuartaTablaBloquearoDesbloquearEscrituraTextField(0);

        }else{
            
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila TONTO", "alert", JOptionPane.ERROR_MESSAGE);
        }        

    }//GEN-LAST:event_jButtonMarcaModificarActionPerformed

    private void TextFieldGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldGeneroActionPerformed

    private void jComboBoxVillanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVillanosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVillanosActionPerformed

    private void jButtonVillanosCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVillanosCancelarActionPerformed
        // TODO add your handling code here:
        this.VillanosLimpiarTextField();
        this.TerceraTablaBloquearoDesbloquearEscrituraTextField(1);
        //Desbloqueo los botones
        jButtonVillanoInsertar.setEnabled(true);
        jButtonVillanosModificar.setEnabled(true);
        jButtonVillanosEliminar.setEnabled(true);
        jComboBoxVillanos.setEnabled(false);
    }//GEN-LAST:event_jButtonVillanosCancelarActionPerformed

    private void jButtonVillanosGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVillanosGuardarActionPerformed
        // TODO add your handling code here:
         seleccion = jComboBoxVillanos.getSelectedIndex();
     
        boolean encontrado = false;

       

        if(jButtonVillanoInsertar.isEnabled() == true){
            //Aqui contemplo el caso para si estan las celdas vacias no me deje insertar nada

            if (!"".equals(this.TextFieldVillanoName.getText().trim()) && !"".equals(this.TextFieldVillanoHabilidad.getText().trim())
                && !"".equals(this.TextFieldVillanoGenero.getText().trim()) && !"".equals(this.TextFieldVillanoMascara.getText().trim()) ) {

                if(this.TextFieldVillanoGenero.getText().length() == 1 && this.TextFieldVillanoGenero.getText().charAt(0) == 'H' || this.TextFieldVillanoGenero.getText().charAt(0) == 'M'){

                    //BUSQUEDA NOMBRE Recorro EL ARRAY DE VILLANOS DENTRO DE SUPERHEROES Y COMPARO EL NOMBRE INTRODUCIDO
                    //BUSQUEDA NOMBRE Recorro EL ARRAY SUPERHEROES 
                                   for(int i=0; i<villanos.size() && encontrado == false; i++){


                                   //Compruebo si el nombreVillano existe en el sistema
                                       if(!this.TextFieldVillanoName.getText().equals(villanos.get(i).getNombreVillano())){

                                           //DEBUG COMO RECORRE EL BUCKLE


                                       }else{

                                           //for de debug para saber los superheroes que hay cuando EL NOMBRE YA EXISTE
                                           for(int j=0; j<villanos.size(); j++)
                                           System.out.println(villanos.get(j).getNombreVillano() + villanos.get(j).getId_SuperHeroe());

                                           JOptionPane.showMessageDialog(null, "Lo sentimos este nombre ya existe", "alert", JOptionPane.ERROR_MESSAGE);
                                           //System.out.println("Lo sentimos este nombreVillano ya existe");
                                            encontrado = true;

                                       }   
                     }

                    if(encontrado == false){

                        //Seleccion del combobox pero no tengo aun el id del objeto padre seleccionado es justo la posicion que se encuentra en el vector
                        int seleccion_n = jComboBoxVillanos.getSelectedIndex();

                        //NUEVA FORMA Y LA CORRECTA PRIMERO MODIFICO EL OBJETO EN LA BASE DE DATOS Y LUEGO TRAIGO DE VUELTA EL ARRAY

                        //INSERTAMOS EN LA BASE DE DATOS CON LOS ATRIBUTOS DEL USUARIO

                        NombreVillano = this.TextFieldVillanoName.getText();
                        Habilidad = this.TextFieldVillanoHabilidad.getText();
                        mascara = Boolean.valueOf(this.TextFieldVillanoMascara.getText());
                        genero = this.TextFieldVillanoGenero.getText().charAt(0);

                        //ASOCIACMOS TAMBIEN SU OBJETO superheroe CORRESPONDIENTE
                        //superheroes.get(seleccion);

                        //Construyo un objeto de tipo superpoder enlazado con la tabla superheroes y los atributos introducidos por el usuario
                        Villanos nuevo = new Villanos(NombreVillano, Habilidad , mascara, this.TextFieldVillanoGenero.getText().charAt(0), jComboBoxVillanos.getItemAt(seleccion_n)); 


                        //Inserto el objeto en el array
                        this.villanos.add(nuevo);

                        //Guardo el tamaño del array de superpodere e inserto el nuevo id del superpoder 
                        
                        nuevo.setId_Villano(villanos.size()-1);
                        this.ActualizarTablaVillanos(villanos);

                        //Una vez introducido el nuevo superheroe bloqueo y pongo en blanco de nuevo los TextField
                        VillanosLimpiarTextField();

                        TerceraTablaBloquearoDesbloquearEscrituraTextField(1);

                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Datos en los campos Incorrectos!", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No puedes insertar campos vacios", "Alert", JOptionPane.ERROR_MESSAGE);
            }

        }

        if(jButtonVillanosModificar.isEnabled() == true && encontrado == false){
            int seleccion = jComboBoxVillanos.getSelectedIndex();

            //Modifico el superheroe seleccionado
            this.villanos.get(Tablavillanos.getSelectedRow()).setNombreVillano(this.TextFieldVillanoName.getText());
            this.villanos.get(Tablavillanos.getSelectedRow()).setHabilidad(this.TextFieldVillanoHabilidad.getText());
            this.villanos.get(Tablavillanos.getSelectedRow()).setGenero(this.TextFieldVillanoGenero.getText().charAt(0));
            this.villanos.get(Tablavillanos.getSelectedRow()).setMascara(Boolean.valueOf(this.TextFieldVillanoMascara.getText()));
            this.villanos.get(Tablavillanos.getSelectedRow()).setId_SuperHeroe((seleccion));
            this.villanos.get(Tablavillanos.getSelectedRow()).setNombreSuperHeroe(jComboBoxVillanos.getItemAt(seleccion));
            
            //Refresco el Array en el Layout
            this.ActualizarTablaVillanos(villanos);

            //Refresco el Array en el Layout
            this.ActualizarTablaVillanos(villanos);

        }

        //Desbloqueo los botones
        jButtonVillanoInsertar.setEnabled(true);
        jButtonVillanosModificar.setEnabled(true);
        jButtonVillanosEliminar.setEnabled(true);

        //Bloqueo ComboBox
        jComboBoxVillanos.setEnabled(false);

        //this.ActualizarTablaSuperPoderes(superpoderes);

        //BLOQUEO Y LIMPIO LA ESCRITURA
        TerceraTablaBloquearoDesbloquearEscrituraTextField(1);
        this.VillanosLimpiarTextField();

    }//GEN-LAST:event_jButtonVillanosGuardarActionPerformed

    private void TextFieldVillanoGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldVillanoGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldVillanoGeneroActionPerformed

    private void TextFieldVillanoNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldVillanoNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldVillanoNameActionPerformed

    private void jButtonVillanosModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVillanosModificarActionPerformed
        //Filtro con mensaje si no ha seleccionado ninguna fila
        if(Tablavillanos.getSelectedRow() != -1){

            //Bloqueo los demas botones para que el usuario no tenga acceso en ese momento hasta que le de a guardar
            jButtonVillanoInsertar.setEnabled(false);
            jButtonVillanosEliminar.setEnabled(false);

            //DESBLOQUEO EL COMBOBOX AL INSERTAR Y LO RELLENO CON LOS VALORES DEL PRIMERO
            jComboBoxVillanos.setEnabled(true);
            jComboBoxVillanos.removeAllItems();
            this.LlenarComboBox();

            //Desbloqueo los textfield para poder modificarlos
            this.TerceraTablaBloquearoDesbloquearEscrituraTextField(0);

        }else{

            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila TONTO", "alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonVillanosModificarActionPerformed

    private void jButtonVillanosEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVillanosEliminarActionPerformed

        //Primero realizamos un filtro por si no se ha realizado
        //ninguna tabla no te deje eliminar ninguna fila
        

        if(Tablavillanos.getSelectedRow() != -1){

            //Elimino del vector
           villanos.remove(villanos.get(Tablavillanos.getSelectedRow()));
         
          
           //Actualizo la tabla
           this.ActualizarTablaVillanos(villanos);
    
            //Actualizo la tabla
            this.ActualizarTablaVillanos(villanos);

            //BLoqueo el combo box y lo dejo limpio
            jComboBoxVillanos.setEnabled(false);
            jComboBoxVillanos.removeAllItems();

            //Pongo los huecos en blanco despues de Eliminar en la tabla y en el vector
            this.VillanosLimpiarTextField();

        }else{

            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila", "alert", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonVillanosEliminarActionPerformed

    private void jButtonVillanoInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVillanoInsertarActionPerformed

        //Bloqeo los botones Modificar y Eliminar
        jButtonVillanosModificar.setEnabled(false);
        jButtonVillanosEliminar.setEnabled(false);

        //DESBLOQUEO EL COMBOBOX AL INSERTAR Y LO RELLENO CON LOS VALORES DEL PRIMERO
        jComboBoxVillanos.setEnabled(true);
        jComboBoxVillanos.removeAllItems();
        this.LlenarComboBox();

        //Pongo los cmapos en vacio
        this.VillanosLimpiarTextField();

        //Desbloqueo los "TextField" para poder editarlos
        this.TerceraTablaBloquearoDesbloquearEscrituraTextField(0);
        //Traigo los valores de cada uno de los textfield y me creo las variables para poder mandarselas al constructor
        //e introducirlo en el vector para evitar repetidos

    }//GEN-LAST:event_jButtonVillanoInsertarActionPerformed

    private void TablavillanosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablavillanosMouseClicked
            seleccion = Tablavillanos.getSelectedRow();
        
        /*
        System.out.println("Posicion = " + seleccion);
        System.out.println("dimension = " + villanos.size());
        */
        
        jComboBoxVillanos.removeAllItems();
        this.TextFieldVillanoName.setText(villanos.get(seleccion).getNombreVillano());
        this.TextFieldVillanoHabilidad.setText((villanos.get(seleccion).getHabilidad()));
        this.TextFieldVillanoGenero.setText(Character.toString(villanos.get(seleccion).getGenero()));
        this.TextFieldVillanoMascara.setText(Boolean.toString(villanos.get(seleccion).isMascara()));
        jComboBoxVillanos.addItem(villanos.get(seleccion).getNombreSuperHeroe());
       
        
    }//GEN-LAST:event_TablavillanosMouseClicked

    private void jPanelSuperHeroesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSuperHeroesMouseClicked

        this.ActualizarTablaSuperHeroes(superheroes);
        
        
        //this.LlenarComboBoxSuperHeroes();
    }//GEN-LAST:event_jPanelSuperHeroesMouseClicked

    private void jPanelMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMarcaMouseClicked
      
        this.ActualizarTablaMarcas(marcas);
    }//GEN-LAST:event_jPanelMarcaMouseClicked

    private void jPanelVillanosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelVillanosMouseClicked
        // TODO add your handling code here:
     
        this.ActualizarTablaVillanos(villanos);
    }//GEN-LAST:event_jPanelVillanosMouseClicked

    private void jPanelSuperPoderesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSuperPoderesMouseClicked
      
        this.ActualizarTablaSuperPoderes(superpoderes);
    }//GEN-LAST:event_jPanelSuperPoderesMouseClicked

    private void TextFieldVillanoMascaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldVillanoMascaraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldVillanoMascaraActionPerformed

    private void jButtonCerrarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarVentanaActionPerformed
        // TODO add your handling code here:
       
         //for(int j=0; j<superheroes.size(); j++)
        //System.out.println("Array superheroes antes de escribir" + superheroes.get(j).getNombre() + " " + superheroes.get(j).getId_SuperHeroe());


        try {
            SuperHeroesEscribirFicherosXML.SuperHeroesEscribirFicherosXML(superheroes);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            SuperPoderesEscribirFicherosXML.SuperPoderesEscribirFicherosXML(superpoderes);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            VillanosEscribirFicherosXML.VillanosEscribirFicherosXML(villanos);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            MarcasEscribirFicherosXML.MarcasEscribirFicherosXML(marcas);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        // System.out.println("array " + superpoderes.size());
       
        System.exit(0);
    }//GEN-LAST:event_jButtonCerrarVentanaActionPerformed

    private void jButtonCerrarVentana1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarVentana1ActionPerformed
        // TODO add your handling code here:
        for(int j=0; j<superpoderes.size(); j++)
        System.out.println("Array SUPERPODERES antes de escribir" + superpoderes.get(j).getNombrePoder() + " " + superpoderes.get(j).getId_SuperPoder());

        
        try {
            SuperHeroesEscribirFicherosXML.SuperHeroesEscribirFicherosXML(superheroes);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            SuperPoderesEscribirFicherosXML.SuperPoderesEscribirFicherosXML(superpoderes);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            VillanosEscribirFicherosXML.VillanosEscribirFicherosXML(villanos);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            MarcasEscribirFicherosXML.MarcasEscribirFicherosXML(marcas);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        // System.out.println("array " + superpoderes.size());
        for(int j=0; j<superheroes.size(); j++)
        System.out.println("Array superheroes antes de salir" + superheroes.get(j).getNombre() + superheroes.get(j).getId_SuperHeroe());

        System.exit(0);
    }//GEN-LAST:event_jButtonCerrarVentana1ActionPerformed

    private void jButtonCerrarVentana2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarVentana2ActionPerformed
        // TODO add your handling code here:
        
        for(int j=0; j<villanos.size(); j++)
            System.out.println("Array villanos antes de salir" + villanos.get(j).getNombreVillano() + villanos.get(j).getId_Villano() + villanos.get(j).getGenero());

        
        try {
            SuperHeroesEscribirFicherosXML.SuperHeroesEscribirFicherosXML(superheroes);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            SuperPoderesEscribirFicherosXML.SuperPoderesEscribirFicherosXML(superpoderes);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            VillanosEscribirFicherosXML.VillanosEscribirFicherosXML(villanos);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            MarcasEscribirFicherosXML.MarcasEscribirFicherosXML(marcas);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        // System.out.println("array " + superpoderes.size());
        for(int j=0; j<superheroes.size(); j++)
        System.out.println("Array superheroes antes de salir" + superheroes.get(j).getNombre() + superheroes.get(j).getId_SuperHeroe());

        System.exit(0);
    }//GEN-LAST:event_jButtonCerrarVentana2ActionPerformed

    private void jButtonCerrarVentana3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarVentana3ActionPerformed
        // TODO add your handling code here:
        try {
            SuperHeroesEscribirFicherosXML.SuperHeroesEscribirFicherosXML(superheroes);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            SuperPoderesEscribirFicherosXML.SuperPoderesEscribirFicherosXML(superpoderes);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            VillanosEscribirFicherosXML.VillanosEscribirFicherosXML(villanos);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            MarcasEscribirFicherosXML.MarcasEscribirFicherosXML(marcas);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
        }

        // System.out.println("array " + superpoderes.size());
        for(int j=0; j<superheroes.size(); j++)
        System.out.println("Array superheroes antes de salir" + superheroes.get(j).getNombre() + superheroes.get(j).getId_SuperHeroe());

        System.exit(0);
    }//GEN-LAST:event_jButtonCerrarVentana3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VISTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VISTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VISTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VISTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                try {
                    new VISTA().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(VISTA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaMarcas;
    private javax.swing.JTable TablaSuperHeroes;
    private javax.swing.JTable TablaSuperPoderes;
    private javax.swing.JTable Tablavillanos;
    private javax.swing.JTextField TextFieldCapa;
    private javax.swing.JTextField TextFieldDaño;
    private javax.swing.JTextField TextFieldGenero;
    private javax.swing.JTextField TextFieldHabilidad;
    private javax.swing.JTextField TextFieldMarcaAño;
    private javax.swing.JTextField TextFieldMarcaName;
    private javax.swing.JTextField TextFieldMarcaPelicula;
    private javax.swing.JTextField TextFieldName;
    private javax.swing.JTextField TextFieldName1;
    private javax.swing.JTextField TextFieldPotencia;
    private javax.swing.JTextField TextFieldVillanoGenero;
    private javax.swing.JTextField TextFieldVillanoHabilidad;
    private javax.swing.JTextField TextFieldVillanoMascara;
    private javax.swing.JTextField TextFieldVillanoName;
    private javax.swing.JButton jButtonCerrarVentana;
    private javax.swing.JButton jButtonCerrarVentana1;
    private javax.swing.JButton jButtonCerrarVentana2;
    private javax.swing.JButton jButtonCerrarVentana3;
    private javax.swing.JButton jButtonMarcaCancelar;
    private javax.swing.JButton jButtonMarcaEliminar;
    private javax.swing.JButton jButtonMarcaGuardar;
    private javax.swing.JButton jButtonMarcaInsertar;
    private javax.swing.JButton jButtonMarcaModificar;
    private javax.swing.JButton jButtonSuperHeroesCancelar;
    private javax.swing.JButton jButtonSuperHeroesEliminar;
    private javax.swing.JButton jButtonSuperHeroesGuardar;
    private javax.swing.JButton jButtonSuperHeroesInsertar;
    private javax.swing.JButton jButtonSuperHeroesModificar;
    private javax.swing.JButton jButtonSuperPoderesCancelar;
    private javax.swing.JButton jButtonSuperPoderesEliminar;
    private javax.swing.JButton jButtonSuperPoderesGuardar;
    private javax.swing.JButton jButtonSuperPoderesInsertar;
    private javax.swing.JButton jButtonSuperPoderesModificar;
    private javax.swing.JButton jButtonVillanoInsertar;
    private javax.swing.JButton jButtonVillanosCancelar;
    private javax.swing.JButton jButtonVillanosEliminar;
    private javax.swing.JButton jButtonVillanosGuardar;
    private javax.swing.JButton jButtonVillanosModificar;
    private javax.swing.JComboBox<String> jComboBoxMarcas;
    private javax.swing.JComboBox<String> jComboBoxSuperPoderes;
    private javax.swing.JComboBox<String> jComboBoxVillanos;
    private javax.swing.JLabel jLabelAño;
    private javax.swing.JLabel jLabelCapa;
    private javax.swing.JLabel jLabelCapa1;
    private javax.swing.JLabel jLabelDaño;
    private javax.swing.JLabel jLabelGenero;
    private javax.swing.JLabel jLabelGenero1;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelName1;
    private javax.swing.JLabel jLabelNameMarca;
    private javax.swing.JLabel jLabelNameVillano;
    private javax.swing.JLabel jLabelPelicula;
    private javax.swing.JLabel jLabelPotencia;
    private javax.swing.JLabel jLabelSuperPoder;
    private javax.swing.JLabel jLabelSuperPoder1;
    private javax.swing.JPanel jPanelMarca;
    private javax.swing.JPanel jPanelSuperHeroes;
    private javax.swing.JPanel jPanelSuperPoderes;
    private javax.swing.JPanel jPanelVillanos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}


class FondoPanel extends JPanel{

    private Image imagen;
    
    @Override
    public void paint (Graphics g){
    
        imagen = new ImageIcon(getClass().getResource("/IMAGENES/Marcas4jtable.jpg")).getImage();
        
        g.drawImage(imagen,0, 0, getWidth(), getHeight(),this);
        
        setOpaque(false);
        
        super.paint(g);
    }
    
    
class FondoPanel2 extends JPanel{

        private Image imagen;

        @Override
        public void paint (Graphics g){

            imagen = new ImageIcon(getClass().getResource("/IMAGENES/Marcas4jtable.jpg")).getImage();

            g.drawImage(imagen,0, 0, getWidth(), getHeight(),this);

            setOpaque(false);

            super.paint(g);
        }
    }
}