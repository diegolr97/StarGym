/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public interface interfazCliente {
    public DefaultListModel listClases(String dni);
   
    public DefaultTableModel listarClientes();
    public boolean añadirCliente(String dni, String nombre, String apellidos, String fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo);
    public boolean eliminarCliente(String dni);
    public boolean modificarClienteTable(String dni, String nombre, String apellidos, String fecha, String direccion, int codPostal, String ciudad, int telefono, String correo);
    public DefaultTableModel listarClientesLetra(String nombre2);
    public boolean añadirAdministrador(String idEmpleado, String clave, int admin, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal);
    public boolean modificarCliente(String dni, String nombre, String apellidos, String fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo);
    public DefaultTableModel listarClientesDNI(String dni);
    public DefaultTableModel listarClientesApellidos(String apellidos);
    public DefaultTableModel listarClientesCobros();
    public DefaultTableModel listarClientesNombre(String nombre);
    public DefaultTableModel listarCobros(String dni);
    public boolean insertarMensualidad(String dni, String fecha, int idMatricula);
        
    
      
    
}
