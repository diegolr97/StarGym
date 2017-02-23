/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class modeloClase extends conexion implements interfazClase {
    public DefaultTableModel listarClases()
    {
        DefaultTableModel tablemodel = new DefaultTableModel();
    
      int registros = 0;
      String[] columNames = {"Identificador", "Nombre", "Precio", "Monitor"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
       CallableStatement cstmt = this.getConexion().prepareCall("{call numeroClases}");
        ResultSet res = cstmt.executeQuery();
         res.next();
         registros = res.getInt("todo");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
     Object[][] data = new String[registros][4];
     try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
        CallableStatement cstmt = this.getConexion().prepareCall("{call listarClases}");
        ResultSet res = cstmt.executeQuery();
         int i=0;
         while(res.next()){
                               
                data[i][0] = res.getString("c.idClase");
                data[i][1] = res.getString("c.nombre");
                data[i][2] = res.getString("c.precio");
                data[i][3] = res.getString("m.nombre") + " " + res.getString("m.apellidos");
                
                     
            i++;
         }
         res.close();
        //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
        }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
        
    }
    @Override
    public boolean añadirClase(String nombre, double precio, String idMonitor){
     boolean res=false;
        
        try {
            CallableStatement cstm = this.getConexion().prepareCall("{call añadirClase(?,?,?)}");
            
            cstm.setString(1, nombre);
            cstm.setDouble(2, precio);
            cstm.setString(3, idMonitor);
           
            cstm.executeUpdate();
            
            cstm.close();
            res=true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage() + "     \n  " + ex.getSQLState());
        }
        return res;
        }
    @Override
    public DefaultComboBoxModel comboMonitores(){
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM monitores");
            ResultSet res = pstm.executeQuery();

            while(res.next()){
                String nombre = "";
                String apellidos = "";

                res.getString("nombre");
                res.getString("apellidos");
                
                String monitor = nombre + " - " + apellidos;
                
                m.addElement(res.getString("idMonitor") + " - " + res.getString("nombre") + " " + res.getString("apellidos"));

                
            }
        }catch(SQLException e){
            e.printStackTrace();
            
        }
        return m;
    }
    
    @Override
    public DefaultComboBoxModel comboTablaClases(){
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM monitores");
            ResultSet res = pstm.executeQuery();

            while(res.next()){
                                
                m.addElement(res.getString("idMonitor"));

                
            }
        }catch(SQLException e){
            e.printStackTrace();
            
        }
        return m;
    }
    
    @Override
    public boolean eliminarClase(int idClase2){
        boolean res=false;
        try {
            //Preparamos la funcion que va a ejecutar la eliminacion
            CallableStatement cstm = this.getConexion().prepareCall("{call eliminarClase(?)}");
            //Indicas el tipo de dato que devuelve
            //Indicas el parametro que le pasas, en este caso el codigo del bar y el dni
            cstm.setInt(1, idClase2);
            //Ejecutas la funcion
            cstm.executeUpdate();
             //Recoges el resultado
            cstm.close();
            res=true;
            
            
        } catch (Exception e) {
        }
        return res;
    }
    @Override
    public boolean modificarClase(int idClase, String nombre, double precio, String idMonitor){
        boolean res=false;
        try {
            CallableStatement cstm = this.getConexion().prepareCall("{call modificarClase(?,?,?,?)}");
            
            cstm.setInt(1, idClase);
            cstm.setString(2, nombre);
            cstm.setDouble(3, precio);
            cstm.setString(4, idMonitor);
            
          
            
            cstm.executeUpdate();
            
            cstm.close();
            res=true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage() + "     \n  " + ex.getSQLState());
        }
        return res;
    }
    
    @Override
    public DefaultListModel listTodasClases(){
    DefaultListModel model = new DefaultListModel();
        try {
           PreparedStatement pstm = this.getConexion().prepareStatement( "select * from clase");
           ResultSet res = pstm.executeQuery();
            
            while (res.next())
            {
                String itemCode = res.getString("idClase") + " - " + res.getString("nombre"); 
                model.addElement(itemCode); 
            }
            
            res.close();
            pstm.close();
          } catch (SQLException ex) {
            Logger.getLogger(modeloCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
       return model;
    }
    
    //Metodo para calcular el precio de la matricula al añadir una clase
    @Override
    public float precioMatricula(DefaultListModel mt){
        
        float precio = 0;
        float total = 0;
        
        for(int i = 0; i < mt.size(); i++){

            String id = (String) mt.getElementAt(i);
            
            List<String> l = Arrays.asList(id.split(" "));
        
            id = l.get(0);
        
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement( "select precio from clase where idClase = '"  + id + " ' ");
                ResultSet res = pstm.executeQuery();

                 while (res.next()) 
                 {
                    precio = res.getFloat("precio"); 
                      
                 }

                 res.close();
                 pstm.close();
          } catch (SQLException ex) {
            Logger.getLogger(modeloCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
            total = total + precio;
            
        }
        
        return total;
    }
    
    //Metodo para sacar las clases que no tiene un clinete
    @Override
    public DefaultListModel listClasesNo(String dni){
    DefaultListModel model = new DefaultListModel();
        try {
           PreparedStatement pstm = this.getConexion().prepareStatement( "select * from clase where nombre not in (select d.nombre from cliente a, matricula b, tarifa c, clase d where a.dni=b.idCliente and b.idMatricula=c.idMatricula and c.idClase=d.idClase and a.dni='"  + dni + " ')");
           ResultSet res = pstm.executeQuery();
            
            while (res.next()) 
            {
                String itemCode = res.getString("idClase") + " - " + res.getString("nombre"); 
                model.addElement(itemCode); 
            }
            
            res.close();
            pstm.close();
          } catch (SQLException ex) {
            Logger.getLogger(modeloCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
       return model;
    }
    
    //Modificar clases cliente
    @Override
    public boolean modificarTarifa(String dni, DefaultListModel lista){
        
        try {
            //Recogemos la id de la matricula
            int maximo = 0;
            String q="select idMatricula as todo from matricula where idCliente = '" + dni + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                 maximo = res.getInt("todo");
            }
        
            //Eliminamos las tarifas con el idMatricula recogido previamente
            String q1 = "DELETE FROM tarifa WHERE idMatricula = " + maximo;
            PreparedStatement pstm1 = this.getConexion().prepareStatement(q);
            pstm1.execute();
            pstm1.close();
            
            for(int i = 0; i < lista.size(); i++){
           
                    String id = (String) lista.getElementAt(i);

                    List<String> l = Arrays.asList(id.split(" "));

                    id = l.get(0);

                    try {
                        
                        String q2 = "INSERT INTO tarifa VALUES ('" + maximo + "', '" + id + "')";
                        PreparedStatement pstm2 = this.getConexion().prepareStatement(q2);
                        pstm2.execute();
                        pstm2.close();

                  } catch (SQLException ex) {
                    Logger.getLogger(modeloCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
            return true;
        } catch (SQLException e) {
            System.err.println( e.getMessage() );
            return false;
        } 
    }
}
