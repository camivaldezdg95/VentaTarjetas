package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReporteProspectoPorEstadoDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(Date desde, Date hasta) {        
        String sql = "select `prospectos por estado`.`nombre` AS `estado`,count(`prospectos por estado`.`dni`) AS `cantidad` from `ventatarjetas`.`prospectos por estado` where fecha between ?  and ? group by `prospectos por estado`.`nombre`";
        
        List<ReporteProspectoPorEstado> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, desde);
            ps.setDate(2, hasta);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReporteProspectoPorEstado reporte1 = new ReporteProspectoPorEstado();                
                reporte1.setEstado(rs.getString("estado"));
                reporte1.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                lista.add(reporte1);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        cn.Desconectar();
        return lista;
    }
    
}
