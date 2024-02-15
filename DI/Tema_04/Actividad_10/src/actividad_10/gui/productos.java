/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package actividad_10.gui;

import actividad_10.dto.Producto;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class productos extends javax.swing.JDialog {

    /**
     * Creates new form productos
     */
    public productos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarTabla();
    }
    
    private void inicializarTabla() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[]{"ID", "Nombre", "Descripcion"});
        jTableTabla.setModel(dtm);
        
        anadirProducto(new Producto("P001", "Panel Solar 100W", "Panel solar monocristalino de 100W"));
        anadirProducto(new Producto("P002", "Batería de Litio 12V", "Batería recargable de litio de 12V, 200Ah"));
        anadirProducto(new Producto("P003", "Inversor Solar 3000W", "Inversor solar de onda sinusoidal pura, 3000W"));
        anadirProducto(new Producto("P004", "Regulador de Carga 20A", "Regulador de carga solar PWM de 20A"));
        anadirProducto(new Producto("P005", "Cable Solar 5 metros", "Cable solar fotovoltaico de 5 metros"));
    }
    
    public void anadirProducto(Producto p) {
    DefaultTableModel dtm = (DefaultTableModel) jTableTabla.getModel();

    // Agregar una nueva fila
    String[] rowData = new String[dtm.getColumnCount()];
    rowData[0] = p.getId();  // Supongamos que quieres agregar el ID del producto en la primera columna
    rowData[1] = p.getNombre();
    rowData[2] = p.getDescripcion();
    
    dtm.addRow(rowData);
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
        jTableTabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableTabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTabla;
    // End of variables declaration//GEN-END:variables
}
