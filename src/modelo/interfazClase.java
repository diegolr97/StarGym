/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public interface interfazClase {
    public DefaultTableModel listarClases();
    public boolean añadirClase(String nombre, double precio, String idMonitor);
    public DefaultComboBoxModel comboMonitores();
     public boolean eliminarClase(int idClase2);
      public boolean modificarClase(int idClase, String nombre, double precio, String idMonitor);
    
    
}
