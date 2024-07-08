package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListaDAOStr {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(String tabla) {
        // Devuelve la lista de canales en el arraylist list
        String sql = "SELECT * FROM " + tabla;
        List<ItemStr> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ItemStr item = new ItemStr();                
                item.setId(rs.getString("id"));
                item.setNombre(rs.getString("nombre"));
                lista.add(item);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
        return lista;
    }
    
    
}
