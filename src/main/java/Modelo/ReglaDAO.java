package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReglaDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(String canal) {
        // Devuelve la lista de reglas en el arraylist list
        String sql = "select r.id, r.nombre_corto, r.descripcion, r.aceptacion, r.prioridad, r.fuente "+
                "from regla r " +
                "inner join politica_regla on r.id = politica_regla.regla "+
                "inner join politica on politica.id = politica_regla.politica "+
                "inner join canales on canales.politica = politica.id "+
                "where canales.id =" + canal;
        List<Regla> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Regla regla = new Regla ();                
                regla.setId(rs.getInt("id"));
                regla.setNombre_corto(rs.getString("nombre_corto"));
                regla.setDescripcion(rs.getString("descripcion"));
                regla.setPrioridad(rs.getInt("prioridad"));
                regla.setAceptacion(rs.getString("aceptacion"));
                regla.setFuente(rs.getString("fuente"));               
                lista.add(regla);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
        return lista;
    }
    
}
