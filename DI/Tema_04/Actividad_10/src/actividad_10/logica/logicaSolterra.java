/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividad_10.logica;

import actividad_10.dto.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class logicaSolterra {
    List <Producto> listProductos = new ArrayList<>();

    public logicaSolterra() {
        listProductos = new ArrayList<>();
        listProductos.add(new Producto("P001", "Panel Solar 100W", "Panel solar monocristalino de 100W"));
        listProductos.add(new Producto("P002", "Batería de Litio 12V", "Batería recargable de litio de 12V, 200Ah"));
        listProductos.add(new Producto("P003", "Inversor Solar 3000W", "Inversor solar de onda sinusoidal pura, 3000W"));
        listProductos.add(new Producto("P004", "Regulador de Carga 20A", "Regulador de carga solar PWM de 20A"));
        listProductos.add(new Producto("P005", "Cable Solar 5 metros", "Cable solar fotovoltaico de 5 metros"));
    }
    
    public List<Producto> getListProductos() {
        return listProductos;
    }
    
    

    
    
    
    
}
