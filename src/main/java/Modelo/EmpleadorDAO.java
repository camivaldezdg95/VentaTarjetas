package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpleadorDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public String buscar(String cuit) {
        
        String resultado="";        
        try {
            // Registrar la solicitud
            con = cn.Conexion();
            ps = con.prepareStatement("SELECT * FROM empleador WHERE cuit=?");
            ps.setString(1, cuit);            
            rs = ps.executeQuery();
            
            if (rs.next()) resultado = rs.getString("nombre");            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            resultado="error";
        }
        cn.Desconectar();        
        return resultado;
    }
    
    public boolean agregar(Empleador empleador) {
                        
        try {
            // Registrar la solicitud
            con = cn.Conexion();
            ps = con.prepareStatement("INSERT INTO empleador (cuit, nombre) VALUES (?,?)");
            ps.setString(1, empleador.getCuit());
            ps.setString(2, empleador.getNombre());                    
            ps.execute();
            
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        cn.Desconectar();        

        return true;
        
    }
    
}
