package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProspectoDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        // Devuelve la lista de prospectos en el arraylist list
        String sql = "SELECT * FROM prospecto";
        List<Prospecto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Prospecto prosp = new Prospecto();
                prosp.setDni(rs.getString("dni"));
                prosp.setNombre(rs.getString("nombre"));
                prosp.setEmail(rs.getString("email"));
                prosp.setTelefono(rs.getString("telefono"));
                lista.add(prosp);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
        return lista;
    }
    
    public boolean encontrar(String dni) {
        boolean hallado = false;
        String sql = "SELECT * FROM prospecto WHERE dni =?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();    
            while (rs.next()) {
                hallado = true;
            } 
         } catch (Exception e) {          
            System.out.println(e.toString());
            System.out.println("Error al buscar el prospecto");
        }
        return hallado;
    }
    
    public boolean modificar(Prospecto prospecto) {
            int modificado;
            String sql = "UPDATE prospecto SET nombre=?, email=?, telefono=? WHERE dni=?";
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1,prospecto.getNombre());
                ps.setString(2,prospecto.getEmail());
                ps.setString(3,prospecto.getTelefono());
                ps.setString(4,prospecto.getDni());
                modificado = ps.executeUpdate();
            } catch (Exception e) {          
                System.out.println(e.toString());
                System.out.println("Error al modificar el prospecto");
                return false;
            }               
            cn.Desconectar();
            if (modificado ==0) return false;
            else return true;
    }
    
    public boolean agregar(Prospecto prospecto) {
            int modificado;
            String sql = "INSERT INTO prospecto VALUES(?, ?,  ?, ?)";
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1,prospecto.getDni());
                ps.setString(2,prospecto.getNombre().toUpperCase());
                ps.setString(3,prospecto.getEmail());
                ps.setString(4,prospecto.getTelefono());                
                modificado = ps.executeUpdate();
            } catch (Exception e) {          
                System.out.println(e.toString());
                System.out.println("Error al agregar el prospecto");
                return false;
            }
            cn.Desconectar();
            if (modificado ==0) return false;
            else return true;
        }        
}
