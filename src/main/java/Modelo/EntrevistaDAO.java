package Modelo;

import Config.Conexion;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class EntrevistaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public int registrar(Entrevista ent) {
        
        String sql = "INSERT INTO entrevista (dni, fecha, vendedor, canal, estado) VALUES (?, ?, ?, ?, ?)";                        
        int nroentrevista = 1;
                
        try {
            // Registrar la entrevista
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, ent.getDni());
            ps.setDate(2, ent.getFecha());
            ps.setString(3, ent.getVendedor());
            ps.setString(4, ent.getCanal());
            ps.setInt(5, ent.getEstado_id());
            ps.execute();
            
            // Obtener el número de entrevista
            sql = "SELECT MAX(id) FROM entrevista";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                nroentrevista = rs.getInt(1);            
                }
        } catch (Exception e) {
            System.out.println(e.toString());
            nroentrevista = 1;
        }
        cn.Desconectar();
        System.out.println(nroentrevista);

        return nroentrevista;
    }
    
    public void cambiarEstado(int id, int estado) {        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("UPDATE entrevista SET estado=? WHERE id=?");
            ps.setInt(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());            
        }
        cn.Desconectar();       
    }
    
    public void asignarLimites(int id, double lcm, double ce) {                
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("UPDATE entrevista SET lcm=?, ce=? WHERE id=?");
            ps.setDouble(1, lcm);
            ps.setDouble(2, ce);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cn.Desconectar();        
    }
        
    public Entrevista recuperar(String ident) {        
        Entrevista retornar = new Entrevista();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("SELECT * FROM vista_entrevistas WHERE id=?");
            ps.setString(1, ident);
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setId(rs.getInt("id"));
                retornar.setDni(rs.getString("dni"));                
                retornar.setEstado(rs.getString("estado"));
                retornar.setFecha(rs.getDate("fecha"));                
                retornar.setNombre(rs.getString("nombre"));                               
                retornar.setCanal(rs.getString("canal"));
                retornar.setVendedor(rs.getString("vendedor"));            
                retornar.setLcm(rs.getDouble("lcm"));
                retornar.setCe(rs.getDouble("ce"));
            }
            ps = con.prepareStatement("SELECT * FROM canales WHERE nombre=?");
            ps.setString(1, retornar.getCanal());
            rs = ps.executeQuery();
            if (rs.next()) retornar.setCanal_id(Integer.parseInt(rs.getString("id")));            
        } catch (Exception e) {
            System.out.println(e.toString());
            retornar.setDni("error");
        }
        cn.Desconectar();
        return retornar;
    }

    public List listar() {
        String sql = "SELECT * FROM vista_entrevistas";
        List<Entrevista> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Entrevista ent = new Entrevista();
                ent.setId(rs.getInt("id"));
                ent.setDni(rs.getString("dni"));                
                ent.setEstado(rs.getString("estado"));
                ent.setFecha(rs.getDate("fecha"));                
                ent.setNombre(rs.getString("nombre"));                               
                ent.setCanal(rs.getString("canal"));
                ent.setVendedor(rs.getString("vendedor"));
                lista.add(ent);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cn.Desconectar();
        return lista;
    }
    
     public List listarParcial(int desde, int hasta) {
        String sql = "SELECT * FROM vista_entrevistas LIMIT ?, ?";
        List<Entrevista> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, desde);
            ps.setInt(2, hasta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Entrevista ent = new Entrevista();
                ent.setId(rs.getInt("id"));
                ent.setDni(rs.getString("dni"));                
                ent.setEstado(rs.getString("estado"));
                ent.setFecha(rs.getDate("fecha"));                
                ent.setNombre(rs.getString("nombre"));                               
                ent.setCanal(rs.getString("canal"));
                ent.setVendedor(rs.getString("vendedor"));
                lista.add(ent);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cn.Desconectar();
        return lista;
    }
     
     public List filtrar(String desde, String hasta, String dni, String nombre, String vendedor, String canal, String estado, int inicio) {
        
        List<Entrevista> lista = new ArrayList<>();        
        
        // Declaración e inicialización de variables            
        String sqlFecha = "";        
        String sqlDni = "";
        String sqlNombre = "";
        String sqlVendedor = "";
        String sqlCanal ="";
        String sqlEstado ="";           
        int parametros = 0;
        
        //Mostrar parametros
        System.out.println("Desde: "+desde);
        System.out.println("Hasta: "+hasta);
        System.out.println("DNI: "+dni);
        System.out.println("Nombre: "+nombre);
        System.out.println("Vendedor: "+vendedor);
        System.out.println("Canal: "+canal);
        System.out.println("Estado: "+estado);
        
        System.out.println("Inicio:" + inicio);
        
        // Armar setencia SQL
        String sql = "SELECT * FROM vista_entrevistas ";
        
        if ((desde==null) || (desde.isEmpty())) {                        
            if ((hasta==null) || (hasta.isEmpty())) {
                // No hacer nada
            } else {
                parametros++;
                sqlFecha = "WHERE fecha  <'"+ hasta+"' ";
                }
        } else if ((hasta==null) || (hasta.isEmpty())) {      
            parametros++;
            sqlFecha ="WHERE fecha >'" + desde + "' ";
            } else {
                parametros++;
                sqlFecha ="WHERE fecha BETWEEN '"+desde+"' AND '"+hasta+"' ";
            }
        
         if (!((dni==null) || (dni.isEmpty()))) {
            if (parametros==0) {
                sqlDni = "WHERE dni LIKE '%" + dni +"%'";
                parametros ++;            
            } else {
                sqlDni = " AND dni =" + dni;
                parametros ++;
            }
         }
        
        if (!((nombre==null) || (nombre.isEmpty()))) {
             if (parametros==0) {
                 sqlNombre = "WHERE nombre LIKE '%" + nombre +"%'";             
                 parametros ++;
             } else {
                 sqlNombre = " AND nombre LIKE '%" + nombre +"%'";
                 parametros ++;
             }
        }            
     
        if (!(vendedor.equalsIgnoreCase("vacio"))) {
            if (parametros==0) {
                sqlVendedor = "WHERE vendedor ='" + vendedor +"'";
                parametros ++;
            } else {
                sqlVendedor = " AND vendedor ='" + vendedor+"'";                
                parametros ++;
            }                        
        }
        
        if (!(canal.equalsIgnoreCase("vacio"))) {
            if (parametros==0) {
                sqlCanal = "WHERE canal ='" + canal+"'";                
                parametros ++;
            } else {
                sqlCanal = " AND canal ='" + canal +"'";                
                parametros ++;
            }            
        }
        
        if (!(estado.equalsIgnoreCase("vacio"))) {
            if (parametros==0) {
                sqlCanal = "WHERE estado ='" + estado+"'";                
                parametros ++;
            } else {
                sqlEstado = " AND estado ='" + estado+"'";                
                parametros ++;            
            }            
        }
        
        sql += sqlFecha + sqlDni + sqlNombre + sqlVendedor + sqlCanal + sqlEstado +" LIMIT "+inicio+",10";
        
        System.out.println("Filtrado:");
        System.out.println(sql);
                
        // Ejecutar query, generar la lista de registros y devolver el resultado        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);            
            rs = ps.executeQuery();
            while (rs.next()) {
                Entrevista ent = new Entrevista();
                ent.setId(rs.getInt("id"));
                ent.setDni(rs.getString("dni"));                
                ent.setEstado(rs.getString("estado"));
                ent.setFecha(rs.getDate("fecha"));                
                ent.setNombre(rs.getString("nombre"));                               
                ent.setCanal(rs.getString("canal"));
                ent.setVendedor(rs.getString("vendedor"));
                lista.add(ent);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cn.Desconectar();
        return lista;
    }
    
public int getRowCount(String desde, String hasta, String dni, String nombre, String vendedor, String canal, String estado) {
        
        // Declaración e inicialización de variables
        int rc = 0;
        String sqlFecha ="";
        String sqlDni = "";
        String sqlNombre = "";
        String sqlVendedor ="";
        String sqlCanal ="";
        String sqlEstado ="";        
        int parametros = 0;               
        
        System.out.println(desde);
        System.out.println(hasta);
        System.out.println(dni);
        System.out.println(nombre);
        System.out.println(vendedor);
        System.out.println(canal);
        System.out.println(estado);
        
        
        // Armar setencia SQL
        String sql = "SELECT * FROM vista_entrevistas ";
        
        if ((desde==null) || (desde.isEmpty())) {                        
            if ((hasta==null) || (hasta.isEmpty())) {
                // No hacer nada
            } else {
                parametros++;
                sqlFecha = "WHERE fecha  <'"+ hasta+"' ";
                }
        } else if ((hasta==null) || (hasta.isEmpty())) {      
            parametros++;
            sqlFecha ="WHERE fecha >'" + desde + "' ";
            } else {
                parametros++;
                sqlFecha ="WHERE fecha BETWEEN '"+desde+"' AND '"+hasta+"' ";
            }
        
         if (!((dni==null) || (dni.isEmpty()))) {
            if (parametros==0) {
                sqlDni = "WHERE dni LIKE '%" + dni +"%'";
                parametros ++;            
            } else {
                sqlDni = " AND dni =" + dni;
                parametros ++;
            }
         }
        
        if (!((nombre==null) || (nombre.isEmpty()))) {
             if (parametros==0) {
                 sqlNombre = "WHERE nombre LIKE '%" + nombre +"%'";             
                 parametros ++;
             } else {
                 sqlNombre = " AND nombre LIKE '%" + nombre +"%'";
                 parametros ++;
             }
        }            
     
        if (!(vendedor.equalsIgnoreCase("vacio"))) {
            if (parametros==0) {
                sqlVendedor = "WHERE vendedor ='" + vendedor +"'";
                parametros ++;
            } else {
                sqlVendedor = " AND vendedor ='" + vendedor+"'";                
                parametros ++;
            }                        
        }
        
        if (!(canal.equalsIgnoreCase("vacio"))) {
            if (parametros==0) {
                sqlCanal = "WHERE canal ='" + canal+"'";                
                parametros ++;
            } else {
                sqlCanal = " AND canal ='" + canal +"'";                
                parametros ++;
            }            
        }
        
        if (!(estado.equalsIgnoreCase("vacio"))) {
            if (parametros==0) {
                sqlCanal = "WHERE estado ='" + estado+"'";                
                parametros ++;
            } else {
                sqlEstado = " AND estado ='" + estado+"'";                
                parametros ++;            
            }            
        }
        
        sql += sqlFecha + sqlDni + sqlNombre + sqlVendedor + sqlCanal + sqlEstado;
        
        
        System.out.println(sql);
        
        // Ejecutar query, contar los registros y devolver el resultado
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();            
            if (rs.last()) rc = rs.getRow();            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cn.Desconectar();
        System.out.println("Rowcount:" + rc);

        return rc;
    }
     
}
