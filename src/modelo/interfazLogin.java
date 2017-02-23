/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public interface interfazLogin {
    public int iniciarSesion(String Nombre, String Contraseña);
    public boolean inicioAdmin();   
    
}
