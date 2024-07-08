package Modelo;

import Config.Conexion;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class SolicitudDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrar(Solicitud sc) {

        String sql = "INSERT INTO solicitud (dni, fecha, vendedor, canal, estado, id, sucursal, provincia, "
                + "modalidad, cuil, nacionalidad, sexo, calle, nro, barrio, piso, dpto, mza, lote, localidad, cp, "
                + "email, telefono, ocupacion, empleador, fechaingreso, ingresos, callel, nrol, pisol, dptol, cpl, "
                + "localidadl, provincial, telefonol, nacimiento, lcm, ce "
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Registrar la solicitud
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, sc.getDni());
            ps.setDate(2, sc.getFecha());
            ps.setString(3, sc.getVendedor_id());
            ps.setInt(4, sc.getCanal_id());
            ps.setInt(5, sc.getEstado_id());

            ps.setString(6, sc.getIdSol());
            ps.setInt(7, sc.getId_sucursal());
            ps.setString(8, sc.getProvincia());
            ps.setInt(9, sc.getId_modalidad());
            ps.setString(10, sc.getCuil());
            ps.setInt(11, sc.getId_nacionalidad());
            ps.setInt(12, sc.getId_sexo());
            ps.setString(13, sc.getCalle());
            ps.setString(14, sc.getNro());
            ps.setString(15, sc.getBarrio());
            ps.setString(16, sc.getPiso());
            ps.setString(17, sc.getDpto());
            ps.setString(18, sc.getMza());
            ps.setString(19, sc.getLote());
            ps.setString(20, sc.getLocalidad());
            ps.setString(21, sc.getCp());
            ps.setString(22, sc.getEmail());
            ps.setString(23, sc.getTelefono());
            ps.setInt(24, sc.getId_ocupacion());
            ps.setString(25, sc.getEmpleador());
            ps.setDate(26, sc.getFechaIngreso());
            ps.setDouble(27, sc.getIngresos());
            ps.setString(28, sc.getCalleL());
            ps.setString(29, sc.getNroL());
            ps.setString(30, sc.getPisoL());
            ps.setString(31, sc.getDptoL());
            ps.setString(32, sc.getCpL());
            ps.setString(33, sc.getLocalidadL());
            ps.setString(34, sc.getProvinciaL());
            ps.setString(35, sc.getTelefonoL());
            ps.setDate(36, sc.getFechaNacimiento());

            ps.setDouble(37, sc.getLcm());
            ps.setDouble(38, sc.getCe());

            ps.execute();

        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        cn.Desconectar();

        return true;
    }

    public boolean cambiarEstado(String id, String estado) {
        boolean resultado = true;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("UPDATE solicitud SET estado=? WHERE id=?");
            ps.setString(1, estado);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
            resultado = false;
        }
        cn.Desconectar();
        return resultado;
    }

    public Solicitud recuperarSolicitud(String id) {
        Solicitud retornar = new Solicitud();
        String x = "";
        retornar.setIdSol(id);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("SELECT * FROM solicitud WHERE id=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setDni(rs.getString("dni"));
                retornar.setFecha(rs.getDate("fecha"));
                retornar.setCanal_id(rs.getInt("canal"));
                retornar.setVendedor_id(rs.getString("vendedor"));
                retornar.setId_provincia(rs.getString("provincia"));
                retornar.setId_sucursal(rs.getInt("sucursal"));
                retornar.setId_estado(rs.getInt("estado"));
                retornar.setId_modalidad(rs.getInt("modalidad"));
                retornar.setCuil(rs.getString("cuil"));
                retornar.setFechaNacimiento(rs.getDate("nacimiento"));
                retornar.setId_nacionalidad(rs.getInt("nacionalidad"));
                retornar.setId_sexo(rs.getInt("sexo"));
                retornar.setCalle(rs.getString("calle"));
                retornar.setNro(rs.getString("nro"));
                retornar.setPiso(rs.getString("piso"));
                retornar.setDpto(rs.getString("dpto"));
                retornar.setBarrio(rs.getString("barrio"));
                retornar.setMza(rs.getString("mza"));
                retornar.setLote(rs.getString("lote"));
                retornar.setLocalidad(rs.getString("localidad"));
                retornar.setTelefono(rs.getString("telefono"));
                retornar.setEmail(rs.getString("email"));
                retornar.setCp(rs.getString("cp"));
                retornar.setPEP(rs.getBoolean("pep"));
                retornar.setEmpleador(rs.getString("empleador"));
                retornar.setId_ocupacion(rs.getInt("ocupacion"));
                retornar.setFechaIngreso(rs.getDate("fechaingreso"));
                retornar.setIngresos(rs.getDouble("ingresos"));
                retornar.setCalleL(rs.getString("callel"));
                retornar.setNroL(rs.getString("nrol"));
                retornar.setPisoL(rs.getString("pisol"));
                retornar.setDptoL(rs.getString("dptol"));
                retornar.setLocalidadL(rs.getString("localidadl"));
                retornar.setId_provincial(rs.getString("provincial"));
                retornar.setCuil(rs.getString("cuil"));
                retornar.setCpL(rs.getString("cpl"));
                retornar.setTelefonoL(rs.getString("telefonol"));
                retornar.setLcm(rs.getDouble("lcm"));
                retornar.setCe(rs.getDouble("ce"));
                retornar.setId_aclaracion(rs.getInt("rechazo_aclaracion"));
                retornar.setId_rechazo(rs.getInt("rechazo_tipo"));
                retornar.setId_causal(rs.getInt("rechazo_causal"));
                retornar.setObservaciones(rs.getString("observaciones"));                
            }

            // Obtener valores de tablas relacionadas
            // Obtener nombre
            ps = con.prepareStatement("SELECT * FROM prospecto WHERE dni=?");
            ps.setString(1, retornar.getDni());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setNombre(rs.getString("nombre"));
            }

            // Obtener sucursal
            ps = con.prepareStatement("SELECT * FROM sucursal WHERE id=?");
            ps.setInt(1, retornar.getId_sucursal());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setSucursal(rs.getString("nombre"));
            }

            // Obtener estado
            ps = con.prepareStatement("SELECT * FROM estado_solicitud WHERE id=?");
            ps.setInt(1, retornar.getId_estado());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setEstado(rs.getString("nombre"));
            }
            
            // Obtener rechazo
            ps = con.prepareStatement("SELECT * FROM rechazo_tipo WHERE id=?");
            ps.setInt(1, retornar.getId_rechazo());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setRechazo(rs.getString("nombre"));
            }
            
            // Obtener causal
            ps = con.prepareStatement("SELECT * FROM rechazo_causal WHERE id=?");
            ps.setInt(1, retornar.getId_causal());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setCausal(rs.getString("nombre"));
            }            

            // Obtener aclaracion
            ps = con.prepareStatement("SELECT * FROM rechazo_aclaracion WHERE id=?");
            ps.setInt(1, retornar.getId_aclaracion());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setAclaracion(rs.getString("nombre"));
            }
            
            // Obtener canal
            ps = con.prepareStatement("SELECT * FROM canales WHERE id=?");
            ps.setInt(1, retornar.getCanal_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setCanal(rs.getString("nombre"));
            }

            // Obtener vendedor
            ps = con.prepareStatement("SELECT * FROM vendedores WHERE id=?");
            ps.setString(1, retornar.getVendedor_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setVendedor(rs.getString("nombre"));
            }

            // Obtener sexo
            ps = con.prepareStatement("SELECT * FROM sexo WHERE id=?");
            ps.setInt(1, retornar.getId_sexo());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setSexo(rs.getString("nombre"));
            }

            // Obtener modalidad
            ps = con.prepareStatement("SELECT * FROM modalidad WHERE id=?");
            ps.setInt(1, retornar.getId_modalidad());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setModalidad(rs.getString("nombre"));
            }

            // Obtener nacionalidad
            ps = con.prepareStatement("SELECT * FROM nacionalidad WHERE id=?");
            ps.setInt(1, retornar.getId_nacionalidad());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setNacionalidad(rs.getString("nombre"));
            }

            // Obtener empleador
            ps = con.prepareStatement("SELECT * FROM empleador WHERE cuit=?");
            ps.setString(1, retornar.getEmpleador());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setNombreEmpleador(rs.getString("nombre"));
            }

            // Obtener ocupación
            ps = con.prepareStatement("SELECT * FROM ocupacion WHERE id=?");
            ps.setInt(1, retornar.getId_ocupacion());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setOcupacion(rs.getString("nombre"));
            }

            // Obtener provincia
            ps = con.prepareStatement("SELECT * FROM provincias WHERE id=?");
            ps.setString(1, retornar.getId_provincia());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setProvincia(rs.getString("nombre"));
            }
            ps = con.prepareStatement("SELECT * FROM provincias WHERE id=?");
            ps.setString(1, retornar.getId_provincial());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setProvinciaL(rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.print("Error: " + e.toString());
            System.out.println(" en linea " + e.getStackTrace()[0].getLineNumber());
            retornar.setDni("error");
        }
        cn.Desconectar();
        return retornar;
    }

    public boolean registrarRechazo(String tipo, String causal, String aclaracion, String observaciones, String id) {

        boolean resultado = true;

        try {
            con = cn.Conexion();
            ps = con.prepareStatement("UPDATE solicitud SET rechazo_tipo =?, rechazo_causal =?, rechazo_aclaracion=?, observaciones=? WHERE id=?");
            ps.setString(1, tipo);
            ps.setString(2, causal);
            ps.setString(3, aclaracion);
            ps.setString(4, observaciones);
            ps.setString(5, id);
            ps.executeUpdate();
            System.out.println("Observaciones: "+observaciones);

        } catch (Exception e) {
            System.out.println(e.toString());
            resultado = false;
        }
        cn.Desconectar();
        return resultado;
    }

    public boolean modificarLimites(String lcm, String ce, String id) {
        boolean resultado = true;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("UPDATE solicitud SET lcm =?, ce =? WHERE id=?");
            ps.setDouble(1, Double.parseDouble(lcm));
            ps.setDouble(2, Double.parseDouble(ce));
            ps.setString(3, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.toString());
            resultado = false;
        }
        cn.Desconectar();
        return resultado;
    }

    public Solicitud recuperarEntrevista(String id) {
        Solicitud retornar = new Solicitud();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("SELECT * FROM entrevista WHERE id=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setDni(rs.getString("dni"));
                retornar.setFecha(rs.getDate("fecha"));
                retornar.setCanal_id(Integer.parseInt(rs.getString("canal")));
                retornar.setVendedor_id(rs.getString("vendedor"));
                retornar.setLcm(rs.getDouble("lcm"));
                retornar.setCe(rs.getDouble("ce"));
            }
            // Obtener valores de tablas relacionadas

            // Obtener datos del prospecto
            ps = con.prepareStatement("SELECT * FROM prospecto WHERE dni=?");
            ps.setString(1, retornar.getDni());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setNombre(rs.getString("nombre"));
                retornar.setEmail(rs.getString("email"));
                retornar.setTelefono(rs.getString("telefono"));
            }

            // Obtener canal
            ps = con.prepareStatement("SELECT * FROM canales WHERE id=?");
            ps.setInt(1, retornar.getCanal_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setCanal(rs.getString("nombre"));
            }

            // Obtener vendedor
            ps = con.prepareStatement("SELECT * FROM vendedores WHERE id=?");
            ps.setString(1, retornar.getVendedor_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                retornar.setVendedor(rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            retornar.setDni("error");
        }
        cn.Desconectar();
        return retornar;
    }

    public List listar() {
        String sql = "SELECT * FROM vista_solicitudes";
        List<Solicitud> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud sc = new Solicitud();
                sc.setIdSol(rs.getString("id"));
                sc.setFecha(rs.getDate("fecha"));
                sc.setDni(rs.getString("dni"));
                sc.setNombre(rs.getString("nombre"));
                sc.setCanal(rs.getString("canal"));
                sc.setVendedor(rs.getString("vendedor"));
                sc.setSucursal(rs.getString("sucursal"));
                sc.setEstado(rs.getString("estado"));
                lista.add(sc);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cn.Desconectar();
        return lista;
    }

    public List filtrar(String desde, String hasta, String dni, String nombre, String vendedor, String canal, String estado, int inicio) {

        List<Solicitud> lista = new ArrayList<>();

        // Declaración e inicialización de variables            
        String sqlFecha = "";
        String sqlDni = "";
        String sqlNombre = "";
        String sqlVendedor = "";
        String sqlCanal = "";
        String sqlEstado = "";
        int parametros = 0;

        //Mostrar parametros
        System.out.println("Desde: " + desde);
        System.out.println("Hasta: " + hasta);
        System.out.println("DNI: " + dni);
        System.out.println("Nombre: " + nombre);
        System.out.println("Vendedor: " + vendedor);
        System.out.println("Canal: " + canal);
        System.out.println("Estado: " + estado);

        System.out.println("Inicio:" + inicio);

        // Armar setencia SQL
        String sql = "SELECT * FROM vista_solicitudes ";

        if ((desde == null) || (desde.isEmpty())) {
            if ((hasta == null) || (hasta.isEmpty())) {
                // No hacer nada
            } else {
                parametros++;
                sqlFecha = "WHERE fecha  <'" + hasta + "' ";
            }
        } else if ((hasta == null) || (hasta.isEmpty())) {
            parametros++;
            sqlFecha = "WHERE fecha >'" + desde + "' ";
        } else {
            parametros++;
            sqlFecha = "WHERE fecha BETWEEN '" + desde + "' AND '" + hasta + "' ";
        }

        if (!((dni == null) || (dni.isEmpty()))) {
            if (parametros == 0) {
                sqlDni = "WHERE dni LIKE '%" + dni + "%'";
                parametros++;
            } else {
                sqlDni = " AND dni =" + dni;
                parametros++;
            }
        }

        if (!((nombre == null) || (nombre.isEmpty()))) {
            if (parametros == 0) {
                sqlNombre = "WHERE nombre LIKE '%" + nombre + "%'";
                parametros++;
            } else {
                sqlNombre = " AND nombre LIKE '%" + nombre + "%'";
                parametros++;
            }
        }

        if (!(vendedor.equalsIgnoreCase("vacio"))) {
            if (parametros == 0) {
                sqlVendedor = "WHERE vendedor ='" + vendedor + "'";
                parametros++;
            } else {
                sqlVendedor = " AND vendedor ='" + vendedor + "'";
                parametros++;
            }
        }

        if (!(canal.equalsIgnoreCase("vacio"))) {
            if (parametros == 0) {
                sqlCanal = "WHERE canal ='" + canal + "'";
                parametros++;
            } else {
                sqlCanal = " AND canal ='" + canal + "'";
                parametros++;
            }
        }

        if (!(estado.equalsIgnoreCase("vacio"))) {
            if (parametros == 0) {
                sqlCanal = "WHERE estado ='" + estado + "'";
                parametros++;
            } else {
                sqlEstado = " AND estado ='" + estado + "'";
                parametros++;
            }
        }

        sql += sqlFecha + sqlDni + sqlNombre + sqlVendedor + sqlCanal + sqlEstado + " LIMIT " + inicio + ",10";

        System.out.println("Filtrado:");
        System.out.println(sql);

        // Ejecutar query, generar la lista de registros y devolver el resultado        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud sc = new Solicitud();
                sc.setIdSol(rs.getString("id"));
                sc.setDni(rs.getString("dni"));
                sc.setEstado(rs.getString("estado"));
                sc.setFecha(rs.getDate("fecha"));
                sc.setNombre(rs.getString("nombre"));
                sc.setCanal(rs.getString("canal"));
                sc.setVendedor(rs.getString("vendedor"));
                lista.add(sc);
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
        String sqlFecha = "";
        String sqlDni = "";
        String sqlNombre = "";
        String sqlVendedor = "";
        String sqlCanal = "";
        String sqlEstado = "";
        int parametros = 0;

        System.out.println(desde);
        System.out.println(hasta);
        System.out.println(dni);
        System.out.println(nombre);
        System.out.println(vendedor);
        System.out.println(canal);
        System.out.println(estado);

        // Armar setencia SQL
        String sql = "SELECT * FROM vista_solicitudes ";

        if ((desde == null) || (desde.isEmpty())) {
            if ((hasta == null) || (hasta.isEmpty())) {
                // No hacer nada
            } else {
                parametros++;
                sqlFecha = "WHERE fecha  <'" + hasta + "' ";
            }
        } else if ((hasta == null) || (hasta.isEmpty())) {
            parametros++;
            sqlFecha = "WHERE fecha >'" + desde + "' ";
        } else {
            parametros++;
            sqlFecha = "WHERE fecha BETWEEN '" + desde + "' AND '" + hasta + "' ";
        }

        if (!((dni == null) || (dni.isEmpty()))) {
            if (parametros == 0) {
                sqlDni = "WHERE dni LIKE '%" + dni + "%'";
                parametros++;
            } else {
                sqlDni = " AND dni =" + dni;
                parametros++;
            }
        }

        if (!((nombre == null) || (nombre.isEmpty()))) {
            if (parametros == 0) {
                sqlNombre = "WHERE nombre LIKE '%" + nombre + "%'";
                parametros++;
            } else {
                sqlNombre = " AND nombre LIKE '%" + nombre + "%'";
                parametros++;
            }
        }

        if (!(vendedor.equalsIgnoreCase("vacio"))) {
            if (parametros == 0) {
                sqlVendedor = "WHERE vendedor ='" + vendedor + "'";
                parametros++;
            } else {
                sqlVendedor = " AND vendedor ='" + vendedor + "'";
                parametros++;
            }
        }

        if (!(canal.equalsIgnoreCase("vacio"))) {
            if (parametros == 0) {
                sqlCanal = "WHERE canal ='" + canal + "'";
                parametros++;
            } else {
                sqlCanal = " AND canal ='" + canal + "'";
                parametros++;
            }
        }

        if (!(estado.equalsIgnoreCase("vacio"))) {
            if (parametros == 0) {
                sqlCanal = "WHERE estado ='" + estado + "'";
                parametros++;
            } else {
                sqlEstado = " AND estado ='" + estado + "'";
                parametros++;
            }
        }

        sql += sqlFecha + sqlDni + sqlNombre + sqlVendedor + sqlCanal + sqlEstado;

        System.out.println(sql);

        // Ejecutar query, contar los registros y devolver el resultado
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
            if (rs.last()) {
                rc = rs.getRow();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cn.Desconectar();
        System.out.println("Rowcount:" + rc);

        return rc;
    }

}
