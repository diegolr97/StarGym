/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import clases.Administrador;
import clases.Trabajador;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import modelo.interfazAdministrador;
import modelo.interfazClase;
import modelo.interfazCliente;
import modelo.interfazLogin;
import modelo.interfazMaquina;
import modelo.interfazMonitor;
import modelo.interfazTrabajador;
import modelo.login;
import modelo.modeloAdministrador;
import modelo.modeloClase;
import modelo.modeloCliente;
import modelo.modeloMaquina;
import modelo.modeloMonitor;
import modelo.modeloTrabajador;

/**
 *
 * @author diego
 */
public class fachada {
    
    //---------Login--------------//
    
    private interfazLogin login = new login();
    
    public int iniciarSesion(String Nombre, String Contraseña){
        return login.iniciarSesion(Nombre, Contraseña);
    }
    
    //--------Clientes---------------//
    
    private interfazCliente cliente = new modeloCliente();
    
    
    public DefaultListModel listClases(String dni){
        return cliente.listClases(dni);
    }
    
    public DefaultTableModel listarClientes(){
         return cliente.listarClientes();
     }
     
    public boolean añadirCliente(String dni, String nombre, String apellidos, String fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo){
        return cliente.añadirCliente(dni, nombre, apellidos, fechaNacimiento, direccion, codPostal, ciudad, telefono, correo);
    }
    public boolean eliminarCliente(String dni){
         return cliente.eliminarCliente(dni);
     }
    public boolean modificarClienteTable(String dni, String nombre, String apellidos, String fecha, String direccion, int codPostal, String ciudad, int telefono, String correo){
        return cliente.modificarClienteTable(dni, nombre, apellidos, fecha, direccion, codPostal, ciudad, telefono, correo);
    }
    public DefaultTableModel listarClientesLetra(String nombre2){
        return cliente.listarClientesLetra(nombre2);
    }
    public boolean añadirAdministrador(String idEmpleado, String clave, int admin, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal){
        return cliente.añadirAdministrador(idEmpleado, clave, admin, nombre, apellidos, direccion, telefono, correo, codPostal);
    }
    public boolean modificarCliente(String dni, String nombre, String apellidos, String fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo){
         return cliente.modificarCliente(dni, nombre, apellidos, fechaNacimiento, direccion, codPostal, ciudad, telefono, correo);
     }
     
     //-------Aministradores---------//
     
    private interfazAdministrador admin = new modeloAdministrador();
     
    public DefaultListModel listAdmin() {
         return admin.listAdmin();
     }
    public Administrador datosAdmin(String idEmpleado){
         return admin.datosAdmin(idEmpleado);
     }
    public boolean modificarEmpleado(String idEmpleado, String clave, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal){
        return admin.modificarEmpleado(idEmpleado, clave, nombre, apellidos, direccion, telefono, correo, codPostal);
    }

    public boolean eliminarEmpleado(String idEmpleado2){
        return admin.eliminarEmpleado(idEmpleado2);
    }
    
     
     
     //----------clases-------------//
     
    private interfazClase clase = new modeloClase();
     
    public DefaultTableModel listarClases(){
         return clase.listarClases();
     }
    
    public boolean añadirClase(String nombre, double precio, String idMonitor){
        return clase.añadirClase(nombre, precio, idMonitor);
    }
    public DefaultComboBoxModel comboMonitores(){
        return clase.comboMonitores();
    }
    
    public DefaultComboBoxModel comboTablaClases(){
        return clase.comboTablaClases();
    }
    
    public boolean eliminarClase(int idClase){
        return clase.eliminarClase(idClase);
    }
    public boolean modificarClase(int idClase, String nombre, double precio, String idMonitor){
        return clase.modificarClase(idClase, nombre, precio, idMonitor);
    }
    
    
     
     //---------maquina--------------//
     
     private interfazMaquina maquina = new modeloMaquina();
     
     public DefaultTableModel listarMaquinas(){
         return maquina.listarMaquinas();
     }
     public boolean añadirMaquina(int idClase, String descripcion){
         return maquina.añadirMaquina(idClase, descripcion);
     }
     public DefaultComboBoxModel comboClases(){
         return maquina.comboClases();
     }
     public boolean eliminarMaquina(int idMaquina){
         return maquina.eliminarMaquina(idMaquina);
     }
     
     //-------monitores------------//
     
     private interfazMonitor monitor = new modeloMonitor();
     
     public DefaultTableModel listarMonitores(){
         return monitor.listarMonitores();
     }
      public boolean añadirMonitor(String idMonitor, String nombre, String apellidos, int telefono, String correo){
          
      return monitor.añadirMonitor(idMonitor, nombre, apellidos, telefono, correo);
     }
      public boolean eliminarMonitor(String idMonitor2){
          return monitor.eliminarMonitor(idMonitor2);
          
        }
      public boolean modificarMonitor(String idMonitor, String nombre, String apellidos, int telefono, String correo){
          return monitor.modificarMonitor(idMonitor, nombre, apellidos, telefono, correo);
      }
      
      //---------trabajadores-----------//
      private interfazTrabajador trabajador = new modeloTrabajador();
      
      public DefaultListModel listTrabajador(){
          return trabajador.listTrabajador();
      }
      public boolean añadirTrabajador(String idEmpleado, String clave, int admin, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal){
          return trabajador.añadirTrabajador(idEmpleado, clave, admin, nombre, apellidos, direccion, telefono, correo, codPostal);
      }
      public Trabajador datosTrabajador(String idEmpleado){
          return trabajador.datosTrabajador(idEmpleado);
      }
      public boolean eliminarTrabajador(String idEmpleado2){
          return trabajador.eliminarTrabajador(idEmpleado2);
      }
      public boolean modificarTrabajador(String idEmpleado, String clave, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal){
          return trabajador.modificarTrabajador(idEmpleado, clave, nombre, apellidos, direccion, telefono, correo, codPostal);
      }
     
    
    
    
}
