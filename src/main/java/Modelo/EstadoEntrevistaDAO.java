package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstadoEntrevistaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        // Devuelve la lista de estados en el arraylist list
        String sql = "SELECT * FROM estado_entrevista";
        List<EstadoEntrevista> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EstadoEntrevista est = new EstadoEntrevista();                
                est.setId(rs.getInt("id"));
                est.setNombre(rs.getString("nombre"));
                lista.add(est);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
        return lista;
    }
    
    
}
    