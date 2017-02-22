
package modelo;
import java.sql.*;

/**
 *
 * @author Diego Lucas Romero
 * @version 22/09/2016 17:01, Diego Lucas Romero
 */

/**
 * Concexion a la base de datos utilizando el conector JDBD
 */
public class conexion {
    
    private static String  host = "79.148.236.236";  
    private String db = "dam20_Gimnasio";
    private String user = "dam20";
    private String password = "salesianas";
    private String url = "jdbc:mysql://"+host+"/"+db;
    private Connection conn = null;

   /** Constructor de clase */
   public conexion(){
        this.url = "jdbc:mysql://79.148.236.236/"+this.db;
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la database (conexión)
         conn = DriverManager.getConnection( this.url, this.user , this.password );
         
           System.out.println("conexion exitosa");
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }

/**
 * obtener database (conexión=
 * @return  conn 
 */
   public Connection getConexion()
   {
    return this.conn;
   }
    
}
