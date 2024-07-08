package Controlador;

import Modelo.Canal;
import Modelo.CanalDAO;
import Modelo.EmpleadorDAO;
import Modelo.EstadoEntrevista;
import Modelo.EstadoSolicitudDAO;
import Modelo.Item;
import Modelo.ItemStr;
import Modelo.ListaDAO;
import Modelo.ListaDAOStr;
import Modelo.Prospecto;
import Modelo.ProspectoDAO;
import Modelo.Solicitud;
import Modelo.SolicitudDAO;
import Modelo.Vendedor;
import Modelo.VendedorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorSolicitudes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        PrintWriter out = response.getWriter();
        String respuesta = "";
        switch (accion) {
            case "Solicitudes":

                // Recuperar parametros de filtrado
                String fechaDesde2 = request.getParameter("fechadesde");
                String fechaHasta2 = request.getParameter("fechahasta");
                String filtroDni2 = request.getParameter("filtrodni");
                if (filtroDni2 != null) {
                    filtroDni2 = filtroDni2.trim();
                }
                String filtroNombre2 = request.getParameter("filtronombre");
                if (filtroNombre2 != null) {
                    filtroNombre2 = filtroNombre2.toUpperCase();
                }
                String filtroVendedor2 = request.getParameter("selVendedor");
                filtroVendedor2 = (filtroVendedor2 == null) ? "vacio" : filtroVendedor2.trim();
                String filtroCanal2 = request.getParameter("selCanal");
                filtroCanal2 = (filtroCanal2 == null) ? "vacio" : filtroCanal2.trim();
                String filtroEstado2 = request.getParameter("selEstado");
                filtroEstado2 = (filtroEstado2 == null) ? "vacio" : filtroEstado2.trim();

                // Contar registros
                SolicitudDAO sdao = new SolicitudDAO();
                int rowCount2 = sdao.getRowCount(fechaDesde2, fechaHasta2, filtroDni2, filtroNombre2, filtroVendedor2, filtroCanal2, filtroEstado2);
                request.setAttribute("rc", rowCount2);

                // Establecer fragmento de la lista a mostrar
                int inicio2 = Integer.parseInt(request.getParameter("inicio"));
                out.println(inicio2);
                request.setAttribute("inicio", inicio2);

                // Generar lista de solicitudes                                             
                List<Solicitud> listaFiltrada2 = sdao.filtrar(fechaDesde2, fechaHasta2, filtroDni2, filtroNombre2, filtroVendedor2, filtroCanal2, filtroEstado2, inicio2);
                request.setAttribute("listasc", listaFiltrada2);

                // Generar lista de vendedores
                VendedorDAO vdao2 = new VendedorDAO();
                List<Vendedor> listav2 = vdao2.listar();
                request.setAttribute("listav", listav2);

                // Generar lista de canales
                CanalDAO cdao2 = new CanalDAO();
                List<Canal> listac2 = cdao2.listar();
                request.setAttribute("listac", listac2);

                // Generar lista de estados 
                EstadoSolicitudDAO eedao2 = new EstadoSolicitudDAO();
                List<EstadoEntrevista> listaest2 = eedao2.listar();
                request.setAttribute("listaest", listaest2);

                // Indicar parámetos de filtrado
                request.setAttribute("fechadesde", fechaDesde2);
                request.setAttribute("fechahasta", fechaHasta2);
                request.setAttribute("filtroDni", filtroDni2);
                request.setAttribute("filtroNombre", filtroNombre2);

                // Indicar valores de combos
                request.setAttribute("vVendedor", (filtroVendedor2.equalsIgnoreCase("vacio")) ? "vacio" : filtroVendedor2);
                request.setAttribute("filtroVendedor", (filtroVendedor2.equalsIgnoreCase("vacio")) ? "Seleccione un Vendedor" : filtroVendedor2);
                request.setAttribute("vCanal", (filtroCanal2.equalsIgnoreCase("vacio")) ? "vacio" : filtroCanal2);
                request.setAttribute("filtroCanal", (filtroCanal2.equalsIgnoreCase("vacio")) ? "Seleccione un Canal" : filtroCanal2);
                request.setAttribute("vEstado", (filtroEstado2.equalsIgnoreCase("vacio")) ? "vacio" : filtroEstado2);
                request.setAttribute("filtroEstado", (filtroEstado2.equalsIgnoreCase("vacio")) ? "Seleccione un Estado" : filtroEstado2);

                request.getRequestDispatcher("solicitudes/listar-solicitudes.jsp").forward(request, response);
                break;

            case "nuevaSolicitud":

                // Recuperar datos de la entrevista que le dio origen
                String idEntrevista = request.getParameter("ent_id");
                Solicitud nuevaSC = new Solicitud();
                SolicitudDAO nuevaSCDAO = new SolicitudDAO();
                nuevaSC = nuevaSCDAO.recuperarEntrevista(idEntrevista);
                request.setAttribute("nuevaSC", nuevaSC);

                // Obtener la fecha de hoy
                long miliseconds = System.currentTimeMillis();
                Date hoy = new Date(miliseconds);
                request.setAttribute("hoy", hoy);

                // Generar lista de vendedores
                ListaDAOStr ldao = new ListaDAOStr();
                List<ItemStr> lista = ldao.listar("vendedores");
                request.setAttribute("listav", lista);

                // Generar lista de canales
                ListaDAO ldao2 = new ListaDAO();
                List<Item> lista2 = ldao2.listar("canales");
                request.setAttribute("listac", lista2);

                // Generar lista de modalidades                
                lista2 = ldao2.listar("modalidad");
                request.setAttribute("listam", lista2);

                // Generar lista de sucursales                
                lista2 = ldao2.listar("sucursal");
                request.setAttribute("listasuc", lista2);

                // Generar lista de sexos                
                lista2 = ldao2.listar("sexo");
                request.setAttribute("listasexo", lista2);

                // Generar lista de nacionalidades 
                lista2 = ldao2.listar("nacionalidad");
                request.setAttribute("listanac", lista2);

                // Generar lista de provincias 
                lista = ldao.listar("provincias");
                request.setAttribute("listaprov", lista);

                // Generar lista de ocupaciones 
                lista2 = ldao2.listar("ocupacion");
                request.setAttribute("listaocup", lista2);

                // Indicar valores de combos
                request.setAttribute("vVendedor", (nuevaSC.getVendedor_id() == null) ? "vacio" : nuevaSC.getVendedor_id());
                request.setAttribute("filtroVendedor", (nuevaSC.getVendedor() == null) ? "Seleccione un Vendedor" : nuevaSC.getVendedor());
                request.setAttribute("vCanal", (nuevaSC.getCanal_id() == 0) ? "vacio" : nuevaSC.getCanal_id());
                request.setAttribute("filtroCanal", (nuevaSC.getCanal() == null) ? "Seleccione un Canal" : nuevaSC.getCanal());

                request.getRequestDispatcher("solicitudes/registrar-sc.jsp").forward(request, response);
                break;

            case "registrarSolicitud":

                //Obtener parámetros del formulario
                String idS = request.getParameter("txtSC");
                String fechaS = request.getParameter("txtFecha");
                String vendedorS = request.getParameter("selVendedor");
                String canalS = request.getParameter("selCanal");
                String sucursalS = request.getParameter("selSucursal");
                String modalidadS = request.getParameter("selModalidad");
                String dniS = request.getParameter("txtDNI");
                String nombreS = request.getParameter("txtNombre");
                String sexoS = request.getParameter("selSexo");
                String cuilS = request.getParameter("txtCUIL");
                String nacimientoS = request.getParameter("txtNacimiento");
                String nacS = request.getParameter("selNac");
                String PEPS = request.getParameter("inputPEP");
                String calleS = request.getParameter("txtCalle");
                String nroS = request.getParameter("txtNro");
                String pisoS = request.getParameter("txtPiso");
                String dptoS = request.getParameter("txtDpto");
                String barrioS = request.getParameter("txtBarrio");
                String mzaS = request.getParameter("txtMza");
                String loteS = request.getParameter("txtLote");
                String localidadS = request.getParameter("txtLocalidad");
                String provinciaS = request.getParameter("selProvincia");
                String cpS = request.getParameter("txtCP");
                String emailS = request.getParameter("txtEmail");
                String telefonoS = request.getParameter("txtTelefono");
                String empleadorS = request.getParameter("txtEmpleador");
                String ocupacion = request.getParameter("selOcupacion");
                String ingresos = request.getParameter("txtIngresos");
                String fechaIngreso = request.getParameter("txtFechaIngreso");
                String calleLS = request.getParameter("txtCalleL");
                String nroLS = request.getParameter("txtNroL");
                String pisoLS = request.getParameter("txtPisoL");
                String dptoLS = request.getParameter("txtDptoL");
                String localidadLS = request.getParameter("txtLocalidadL");
                String provinciaLS = request.getParameter("selProvinciaL");
                if (provinciaLS.equalsIgnoreCase("vacio")) {
                    provinciaLS = "";
                }
                String cpLS = request.getParameter("txtCPL");
                String telefonoLS = request.getParameter("txtTelefonoL");
                String presentar = request.getParameter("presentar");
                String lcm = request.getParameter("lcm");
                String ce = request.getParameter("ce");

                // Mostrando los parámetros
                out.println("id Solic: " + idS);
                out.println("Fecha: " + fechaS);
                out.println("Vendedor: " + vendedorS);
                out.println("Canal: " + canalS);
                out.println("Sucursal: " + sucursalS);
                out.println("Modalidad: " + modalidadS);
                out.println("DNI: " + dniS);
                out.println("Nombre: " + nombreS);
                out.println("Sexo: " + sexoS);
                out.println("CUIL: " + cuilS);
                out.println("Fecha de Nacimiento: " + nacimientoS);
                out.println("Nacionalidad: " + nacS);
                out.println("PEP: " + PEPS);
                out.println("Calle: " + calleS);
                out.println("Nro: " + nroS);
                out.println("Piso: " + pisoS);
                out.println("Dpto: " + dptoS);
                out.println("Barrio: " + barrioS);
                out.println("Mza: " + mzaS);
                out.println("Lote: " + loteS);
                out.println("Localidad: " + localidadS);
                out.println("Provincia: " + provinciaS);
                out.println("CP: " + cpS);
                out.println("Email: " + emailS);
                out.println("Telefono: " + telefonoS);
                out.println("Empleador: " + empleadorS);
                out.println("Ocupación: " + ocupacion);
                out.println("Ingresos: " + ingresos);
                out.println("FechaIngreso: " + fechaIngreso);
                out.println("Calle Laboral: " + calleLS);
                out.println("Numero Laboral: " + nroLS);
                out.println("Piso Laboral: " + pisoLS);
                out.println("Dpto Laboral: " + dptoLS);
                out.println("Localidad Laboral: " + localidadLS);
                out.println("Provincia Laboral: " + provinciaLS);
                out.println("CP Laboral: " + cpLS);
                out.println("Telefono Laboral: " + telefonoLS);
                out.println("Presentar: " + presentar);
                out.println("LCM: " + lcm);
                out.println("CE: " + ce);

                // Validar datos ingresados                                
                if (nacimientoS == "") {
                    request.setAttribute("respuesta", "Error: La fecha de nacimiento es requerida");
                    request.getRequestDispatcher("solicitudes/resultado-solicitud.jsp").forward(request, response);
                    break;
                }
                if (calleS == "") {
                    request.setAttribute("respuesta", "Error: La calle del domicilio particular es requerida");
                    request.getRequestDispatcher("solicitudes/resultado-solicitud.jsp").forward(request, response);
                    break;
                }
                if (sexoS.equalsIgnoreCase("vacio")) {
                    request.setAttribute("respuesta", "Error: El sexo es requerido");
                    request.getRequestDispatcher("solicitudes/resultado-solicitud.jsp").forward(request, response);
                    break;
                } else {

                    // Asignar los datos del formulario al objeto Solicitud
                    out.println("Asignando los parámeros al objeto solicitud");
                    Solicitud newSC = new Solicitud();
                    newSC.setIdSol(idS.toUpperCase());
                    newSC.setFecha(Date.valueOf(fechaS));
                    newSC.setVendedor_id(vendedorS);
                    newSC.setCanal_id(Integer.parseInt(canalS));
                    newSC.setId_sucursal(Integer.parseInt(sucursalS));
                    newSC.setId_modalidad(Integer.parseInt(modalidadS));
                    newSC.setDni(dniS);
                    newSC.setNombre(nombreS.toUpperCase());
                    newSC.setId_sexo(Integer.parseInt(sexoS));
                    newSC.setCuil(cuilS);
                    newSC.setFechaNacimiento(Date.valueOf(nacimientoS));
                    newSC.setId_nacionalidad(Integer.parseInt(nacS));
                    if (PEPS.equalsIgnoreCase("1")) {
                        newSC.setPEP(true);
                    } else {
                        newSC.setPEP(false);
                    }
                    newSC.setCalle(calleS.toUpperCase());
                    newSC.setNro(nroS);
                    newSC.setPiso(pisoS);
                    newSC.setDpto(dptoS.toUpperCase());
                    newSC.setBarrio(barrioS.toUpperCase());
                    newSC.setMza(mzaS.toUpperCase());
                    newSC.setLote(loteS.toUpperCase());
                    newSC.setLocalidad(localidadS.toUpperCase());
                    newSC.setProvincia(provinciaS);
                    newSC.setCp(cpS);
                    newSC.setEmail(emailS.toLowerCase());
                    newSC.setTelefono(telefonoS);
                    newSC.setEmpleador(empleadorS);
                    newSC.setId_ocupacion(Integer.parseInt(ocupacion));
                    newSC.setIngresos(Double.parseDouble(ingresos));
                    if (!(fechaIngreso == "")) {
                        newSC.setFechaIngreso(Date.valueOf(fechaIngreso));
                    }
                    newSC.setCalleL(calleLS.toUpperCase());
                    newSC.setNroL(nroLS);
                    newSC.setPisoL(pisoLS);
                    newSC.setDptoL(dptoLS.toUpperCase());
                    newSC.setLocalidadL(localidadLS.toUpperCase());
                    newSC.setProvinciaL(provinciaLS);
                    newSC.setCpL(cpLS);
                    newSC.setTelefonoL(telefonoLS);
                    newSC.setLcm(Double.valueOf(lcm));
                    newSC.setCe(Double.valueOf(ce));
                    newSC.setEstado_id((presentar.equalsIgnoreCase("no")) ? 0 : 1);

                    // Registar la nueva solicitud en la BD 
                    out.println("Registrando la nueva solicitud");
                    SolicitudDAO newSCDAO = new SolicitudDAO();

                    // Buscar prospecto. Si ya existìa, se lo sobreescribe. Si no, se lo crea.
                    Prospecto prospecto = new Prospecto(dniS, nombreS, emailS, telefonoS);
                    ProspectoDAO pdao = new ProspectoDAO();
                    if (pdao.encontrar(prospecto.getDni())) {
                        pdao.modificar(prospecto);
                    } else {
                        pdao.agregar(prospecto);
                    }

                    // Devolver el resultado del proceso
                    boolean resultGrabar = newSCDAO.registrar(newSC);
                    if (resultGrabar) {
                        respuesta = "Solicitud " + idS + " grabada con exito";
                    } else {
                        respuesta = "Error: Fallo al grabar Solicitud";
                    }
                    request.setAttribute("respuesta", respuesta);
                    out.println(respuesta);
                    request.setAttribute("id", idS);
                    request.getRequestDispatcher("solicitudes/resultado-solicitud.jsp").forward(request, response);
                }

                break;

            case "anexar":
                break;

            case "buscarEmpleador":
                String cuit = request.getParameter("cuit");
                EmpleadorDAO empleadorDAO = new EmpleadorDAO();
                String resultado = empleadorDAO.buscar(cuit);
                // out.println("resultado = "+resultado);
                if (resultado.isEmpty()) {
                    request.setAttribute("empleadorNombre", "no encontrado");
                    out.println("no encontrado");
                } else {
                    request.setAttribute("empleadorNombre", resultado);
                    out.println(resultado);;
                }

                break;

            case "mostrarSolicitud":
                Solicitud solicitud2 = new Solicitud();
                SolicitudDAO scDAO2 = new SolicitudDAO();
                String idSC2 = request.getParameter("id");
                solicitud2 = scDAO2.recuperarSolicitud(idSC2);
                request.setAttribute("solicitud", solicitud2);
                request.getRequestDispatcher("solicitudes/mostrar-sc.jsp").forward(request, response);
                break;

            case "analizar":
                // recuperar solicitud
                Solicitud solicitud = new Solicitud();
                SolicitudDAO scDAO = new SolicitudDAO();
                String idSC = request.getParameter("id");
                solicitud = scDAO.recuperarSolicitud(idSC);
                request.setAttribute("solicitud", solicitud);
                
                // recuperar lista de estados 
                ListaDAO eedao3 = new ListaDAO();
                List<Item> listaest3 = eedao3.listar("estado_solicitud");
                request.setAttribute("listaest", listaest3);

                // recuperar lista de tipos de rechazo
                ListaDAO motivosDao = new ListaDAO();
                List<Item> listaTipos = motivosDao.listar("rechazo_tipo");
                request.setAttribute("lista_tipos", listaTipos);

                // recuperar listado de causales de rechazo
                ListaDAO causalesDao = new ListaDAO();
                List<Item> listaCausales = causalesDao.listar("rechazo_causal");
                request.setAttribute("lista_causales", listaCausales);

                // recuperar listado de aclaraciones
                ListaDAO aclaracionesDao = new ListaDAO();
                List<Item> listaAclaraciones = aclaracionesDao.listar("rechazo_aclaracion");
                request.setAttribute("lista_aclaraciones", listaAclaraciones);

                // Indicar valores de combos
                request.setAttribute("vRechazo", (solicitud.getId_rechazo() == 0) ? "" : solicitud.getId_rechazo());
                request.setAttribute("filtroRechazo", (solicitud.getRechazo() == null) ? "Seleccione un tipo de rechazo" : solicitud.getRechazo());
                request.setAttribute("vCausal", (solicitud.getId_causal() == 0) ? "" : solicitud.getId_causal());
                request.setAttribute("filtroCausal", (solicitud.getCausal() == null) ? "Seleccione una causal" : solicitud.getCausal());
                request.setAttribute("vAclaracion", (solicitud.getId_aclaracion() == 0) ? "" : solicitud.getId_aclaracion());
                request.setAttribute("filtroAclaracion", (solicitud.getAclaracion() == null) ? "Seleccione una aclaracion" : solicitud.getAclaracion());

                request.getRequestDispatcher("solicitudes/analizar.jsp").forward(request, response);
                break;

            case "mostrarAdjuntos":
                break;

            case "cambiarEstado":

                // Recuperar parámetros del formulario
                String idSol = request.getParameter("idS");
                String nuevoEstado = request.getParameter("selEstado");
                String nuevoTipo = request.getParameter("selTipo");
                String nuevaCausal = request.getParameter("selCausal");
                String nuevaAclaracion = request.getParameter("selAclaracion");
                String observaciones = request.getParameter("observaciones");
                String lcm2 = request.getParameter("lcm");
                String ce2 = request.getParameter("ce");

                // Validar datos ingresados
                if (Double.parseDouble(ce2) > 9.0) {
                    respuesta = "El valor del CE está fuera de rango. No se modificó CE.\n";
                }

                // Grabar cambios
                sdao = new SolicitudDAO();
                if (!(sdao.cambiarEstado(idSol, nuevoEstado))) {
                    respuesta = respuesta + "Error cambiando estado.\n";
                    System.out.println("Error cambiando estado");
                } else {
                    System.out.println("El estado se cambió correctamente");
                }
                if (!(sdao.registrarRechazo(nuevoTipo, nuevaCausal, nuevaAclaracion, observaciones, idSol))) {
                    respuesta = respuesta + "Error registrando rechazo.\n";
                    System.out.println("Error registrando rechazo");
                } else {
                    System.out.println("El rechazo se registró correctamente");
                }
                if (!(sdao.modificarLimites(lcm2, ce2, idSol))) {
                    respuesta = respuesta + "Error modificando límites de crédito\n";
                    System.out.println("Error modificando limites de credito");
                } else {
                    System.out.println("Los limites se modificaron correctamente");
                }
                if (respuesta.length() == 0) {
                    respuesta = "Los cambios se guardaron correctamente.";
                }
                request.setAttribute("respuesta", respuesta);
                request.getRequestDispatcher("solicitudes/resultado-solicitud.jsp").forward(request, response);
                break;

            default:
                out.println("No se especificó una acción válida");
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
