package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(String tabla) {
        // Devuelve la lista de canales en el arraylist list
        String sql = "SELECT * FROM " + tabla;
        List<Item> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();                
                item.setId(rs.getInt("id"));
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
