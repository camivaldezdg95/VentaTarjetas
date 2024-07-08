package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModalidadDAO {

     Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        // Devuelve la lista de modalidades en el arraylist list
        String sql = "SELECT * FROM modalidad";
        List<Modalidad> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Modalidad mod = new Modalidad();                
                mod.setId(rs.getInt("id"));
                mod.setNombre(rs.getString("nombre"));
                lista.add(mod);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
        return lista;
    }
    
}
