package Modelo;

import Config.Conexion;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Usuario validar(String usuario, String password) {
        // si el usuario es validado, devolverá un objeto tipo usuario. Caso contrario devolverá null
        Usuario user = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE user=? AND pwd=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();          
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsuario(rs.getString("user"));
                user.setPassword(rs.getString("pwd"));
                user.setNombre(rs.getString("nombre"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.Desconectar();
        return user;
    }

    

}
