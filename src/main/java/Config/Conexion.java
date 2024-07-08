package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/ventatarjetas?serverTimeZone=UTC";
    String user="leonardo";
    String pass="leonardo";
          
    public Connection Conexion() {
        // Método constructor de la clase, devuelve una conexión
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user, pass);
        } catch (Exception e) {
            System.out.println(e.toString());
            con = null;
        }
        return con;
    }
   
    public void Desconectar() {
        // Cierra la conexión
        try {
            if(con!=null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
