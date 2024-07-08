package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultadoDAO {

Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(String entrevista) {
        // Devuelve la lista de resultados en el arraylist lista
        String sql = "select r.id, r.nombre_corto, r.descripcion, r.aceptacion, re.valor, re.pass, re.entrevista " +
                "from regla r "+
                "inner join resultado re on r.id = re.regla "+
                "inner join entrevista e on e.id = re.entrevista "+
                "where e.id =" + entrevista + " order by r.id";
        List<Resultado> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Resultado res= new Resultado();                
                res.setRegla(rs.getInt("id"));
                res.setNombre_corto(rs.getString("nombre_corto"));
                res.setDescripcion(rs.getString("descripcion"));
                res.setAceptacion(rs.getString("aceptacion"));
                res.setValor(rs.getString("valor"));
                res.setPass(rs.getBoolean("pass"));
                res.setEntrevista(rs.getInt("entrevista"));                
                lista.add(res);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
        return lista;
    }

    public void registrar(Resultado resultado) {
        String sql ="INSERT INTO resultado (entrevista, valor, regla, pass) VALUES (?, ?, ?, ?)";         
        try {
            con = cn.Conexion();            
            ps = con.prepareStatement(sql);
            ps.setInt(1, resultado.getEntrevista());
            ps.setString(2, resultado.getValor());
            ps.setInt(3, resultado.getRegla());
            ps.setBoolean(4, resultado.isPass());
            ps.execute();                        
            } 
         catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
    }
    
    public void borrar(int entrevista) {
        String sql = "DELETE FROM resultado WHERE entrevista=?";
        try {
            con = cn.Conexion();            
            ps = con.prepareStatement(sql);
            ps.setInt(1, entrevista);
            ps.execute();            
            } 
         catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
    }
    
    
}
