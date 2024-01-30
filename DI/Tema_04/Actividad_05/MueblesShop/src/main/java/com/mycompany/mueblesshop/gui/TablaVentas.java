/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.mueblesshop.gui;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class TablaVentas extends javax.swing.JDialog {

    /**
     * Creates new form TablaVentas
     */
    public TablaVentas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarTabla();
    }

    private void inicializarTabla() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[]{"Muebles de Salón", "Muebles de Cocina", "Muebles de Terraza", "Muebles de Habitación"});
        jTable1.setModel(dtm);
    }

    public void anadirVenta(int column, String producto) {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        for (int row = 0; row < dtm.getRowCount(); row++) {
            String mueble = dtm.getValueAt(row, column).toString();
            if (!mueble.isEmpty()) {
                String[] V = new String[dtm.getColumnCount()];
                for (int i = 0; i < dtm.getColumnCount(); i++) {
                    V[i] = (i == column) ? producto : (String) dtm.getValueAt(row, i);
                }
                dtm.insertRow(row, V);
                jTable1.setModel(dtm);
                break;
            } else {
                row++;
            }
        }
    }
    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) jTable1.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
