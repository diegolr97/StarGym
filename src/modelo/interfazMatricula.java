/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.DefaultListModel;

/**
 *
 * @author Manuel
 */
public interface interfazMatricula {
    public boolean añadirMatricula(String dni, String nombre, String apellidos, String fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo, String fecha, double coste, DefaultListModel lista);
}
