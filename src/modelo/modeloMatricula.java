/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Manuel
 */
public class modeloMatricula extends conexion implements interfazMatricula{
    
    public boolean añadirMatricula(String dni, String nombre, String apellidos, String fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo, String fecha, double coste, DefaultListModel lista){
    
        //consulta sql
            try {
                //Inserta cliente
                String q=" INSERT INTO cliente ( dni , nombre, apellidos, fechaNacimiento, direccion, codPostal, ciudad, telefono, correo ) "
                    + "VALUES ('" + dni + "', '" + nombre + "', '" + apellidos + "', '" + fechaNacimiento + "', '" + direccion + "', " + codPostal + ", '" + ciudad + "', " + telefono + ", '" + correo + "') ";
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                
                //Insertamos matricula
                String q3=" INSERT INTO matricula (idCliente)" + "VALUES ('" + dni +"')";
                PreparedStatement pstm3 = this.getConexion().prepareStatement(q3);
                pstm3.execute();
                pstm3.close();
                
                //Recogemos la id de la matricula
                int maximo = 0;
                String q4="select idMatricula as todo from matricula where idCliente = '" + dni + "'";
                PreparedStatement pstm4 = this.getConexion().prepareStatement(q4);
                ResultSet res4 = pstm4.executeQuery();
                while(res4.next()){
                     maximo = res4.getInt("todo");
                }
                
                //Insertamos la mensualidad
                String q5 = "INSERT INTO mensualidad (idMatricula, fecha, coste)" + "VALUES ('" + maximo + "', '" + fecha + "', '" + coste + "')";
                PreparedStatement pstm5 = this.getConexion().prepareStatement(q5);
                pstm5.execute();
                pstm5.close();
                
                for(int i = 0; i < lista.size(); i++){
           
                    String id = (String) lista.getElementAt(i);

                    List<String> l = Arrays.asList(id.split(" "));

                    id = l.get(0);

                    try {
                        
                        String q6 = "INSERT INTO tarifa VALUES ('" + maximo + "', '" + id + "')";
                        PreparedStatement pstm6 = this.getConexion().prepareStatement(q6);
                        pstm6.execute();
                        pstm6.close();

                  } catch (SQLException ex) {
                    Logger.getLogger(modeloCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
                return true;
                }catch(SQLException e){
                    System.err.println( e.getMessage() );
                    return false;
                }
                
    }
}
