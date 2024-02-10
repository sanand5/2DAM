/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.mueblesshop.gui;

import javax.swing.JOptionPane;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class VentanaMuebles extends javax.swing.JDialog {

    /**
     * Creates new form VentanaMuebles
     */
    TablaVentas tabla;
    public VentanaMuebles(java.awt.Frame parent, boolean modal, TablaVentas tabla) {
        super(parent, modal);
        this.tabla = tabla;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonSalon = new javax.swing.JButton();
        jButtonCocina = new javax.swing.JButton();
        jButtonTerraza = new javax.swing.JButton();
        jButtonHabitacion = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        jButtonSalon.setText("Salón");
        jButtonSalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalonActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSalon);

        jButtonCocina.setText("Cocina");
        jButtonCocina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCocinaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCocina);

        jButtonTerraza.setText("Terraza");
        jButtonTerraza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerrazaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonTerraza);

        jButtonHabitacion.setText("Habitación");
        jButtonHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHabitacionActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonHabitacion);

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("Que tipo de mueble quieres añadir?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private String pedirMueble() {
        String nombre = JOptionPane.showInputDialog(this,"Introduce el nombre de el mueble:","Nombre del mueble",JOptionPane.INFORMATION_MESSAGE);
        return nombre;
        
    }
    
    private void jButtonSalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalonActionPerformed
        tabla.anadirVenta(0, pedirMueble());
    }//GEN-LAST:event_jButtonSalonActionPerformed

    private void jButtonCocinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCocinaActionPerformed
        tabla.anadirVenta(1, pedirMueble());
    }//GEN-LAST:event_jButtonCocinaActionPerformed

    private void jButtonTerrazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerrazaActionPerformed
        tabla.anadirVenta(2, pedirMueble());
    }//GEN-LAST:event_jButtonTerrazaActionPerformed

    private void jButtonHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHabitacionActionPerformed
        tabla.anadirVenta(3, pedirMueble());
    }//GEN-LAST:event_jButtonHabitacionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCocina;
    private javax.swing.JButton jButtonHabitacion;
    private javax.swing.JButton jButtonSalon;
    private javax.swing.JButton jButtonTerraza;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}