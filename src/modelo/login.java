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
public class login extends conexion implements interfazLogin {
    
    @Override
    public int iniciarSesion(String DNI, String Contraseña) {
        String dni= "";
        String contraseña = "";
        int admin = 0;
        int comp= 2;
        //consulta sql
        try {
            String q = "SELECT * FROM empleado ";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            
            
            while (res.next()) {
                //recorre uno a uno el Nombre y la Contraseña de cada Medico
                dni = res.getString("idEmpleado");
                contraseña = res.getString("clave");
                admin = res.getInt("admin");
                //Si coincide los datos puesto con los datos de la base de datos
                //puedes entrar al menu
                
                 if(dni.equals(DNI) && contraseña.equals(Contraseña) && admin == 1){
              
                    comp = 1;
                    
                }else if(dni.equals(DNI) && contraseña.equals(Contraseña) && admin == 0){
                    comp=0;
                    
                     
                 }
                    
                 
            }
            res.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        } 
        return comp;
    }
    
}
