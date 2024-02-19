/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividad_10.gui.tablemodels;


import actividad_10.dto.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author andre
 */
public class ProductosTableModel extends AbstractTableModel{
    List<Producto> listaProductos; //
    String[] columnas = {"ID", "Nombre", "Descripción"};

    public ProductosTableModel(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public int getRowCount() {
        return this.listaProductos.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listaProductos.get(rowIndex).getId();
            case 1:
                return listaProductos.get(rowIndex).getNombre();
            case 2:
                return listaProductos.get(rowIndex).getDescripcion();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas [column];
    }
    
    
    
}
