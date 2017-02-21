/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author diego
 */
public class Matricula extends conexion {
    
    
    
    public boolean añadirMatricula(String dni, String nombre, String apellidos, String fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo, String fecha, double coste){
    
        //consulta sql
                String q=" INSERT INTO cliente ( dni , nombre, apellidos, fechaNacimiento, direccion, codPostal, ciudad, telefono, correo ) "
                    + "VALUES ('" + nombre + "', '" + apellidos + "', '" + fechaNacimiento + "', '" + direccion + "', '" + codPostal + "', '" + ciudad + "', '" + telefono + "', '" + correo + "') ";
                String q2="select * from cliente";
                String registros=null;
                int maximo = 0;
                String q3=" INSERT INTO Matricula (idCliente)" + "VALUES ('" + registros +"')";
                String q4="select max(idMatricula) as todo from matricula";
                String q5="INSERT INTO Mensualidad (idMatricula, fecha, coste)" + "VALUES ('" + maximo + "', '" + fecha + "', '" + coste + "')";
            
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                PreparedStatement pstm2 = this.getConexion().prepareStatement(q2);
                ResultSet res = pstm2.executeQuery();
                while(res.next()){
                  registros = res.getString("dni");
                }
                PreparedStatement pstm3 = this.getConexion().prepareStatement(q3);
                pstm3.execute();
                pstm3.close();
                PreparedStatement pstm4 = this.getConexion().prepareStatement(q2);
                ResultSet res4 = pstm2.executeQuery();
                while(res4.next()){
                     maximo = res.getInt("todo");
                }
                PreparedStatement pstm5 = this.getConexion().prepareStatement(q);
                pstm5.execute();
                
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
    }
}


