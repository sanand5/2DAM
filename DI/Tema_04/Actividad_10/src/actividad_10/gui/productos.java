/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package actividad_10.gui;

import actividad_10.dto.Producto;
import actividad_10.gui.tablemodels.ProductosTableModel;
import actividad_10.logica.logicaSolterra;
import java.util.ArrayList;
import java.util.List;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class productos extends javax.swing.JDialog {

    /**
     * Creates new form productos
     */
    private logicaSolterra logicaSolterra = new logicaSolterra();
    public productos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarTabla();
    }
    
    private void inicializarTabla() {
        ProductosTableModel tmp = new ProductosTableModel(logicaSolterra.getListProductos());
        jTableTabla.setModel(tmp);
        
        TableRowSorter<ProductosTableModel> sorter = new TableRowSorter<>(tmp);
        jTableTabla.setRowSorter(sorter);
        
        List<SortKey> sortKey = new ArrayList<>();
        sortKey.add(new SortKey(0,SortOrder.ASCENDING));
        sorter.setSortKeys(sortKey);
        
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
        setTitle("Nuestros Productos");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
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