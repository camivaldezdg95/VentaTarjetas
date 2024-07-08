/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Canal;
import Modelo.CanalDAO;
import Modelo.Entrevista;
import Modelo.EntrevistaDAO;
import Modelo.EstadoEntrevista;
import Modelo.EstadoEntrevistaDAO;
import Modelo.Resultado;
import Modelo.ResultadoDAO;
import Modelo.Vendedor;
import Modelo.VendedorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorEntrevistas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        PrintWriter out = response.getWriter();
        switch (accion) {
            case "Entrevistas":

                // Recuperar parametros de filtrado
                String fechaDesde = request.getParameter("fechadesde");
                String fechaHasta = request.getParameter("fechahasta");
                String filtroDni = request.getParameter("filtrodni");
                if (filtroDni != null) {
                    filtroDni = filtroDni.trim();
                }
                String filtroNombre = request.getParameter("filtronombre");
                if (filtroNombre != null) {
                    filtroNombre = filtroNombre.toUpperCase();
                }
                String filtroVendedor = request.getParameter("selVendedor");
                filtroVendedor = (filtroVendedor == null) ? "vacio" : filtroVendedor.trim();
                String filtroCanal = request.getParameter("selCanal");
                filtroCanal = (filtroCanal == null) ? "vacio" : filtroCanal.trim();
                String filtroEstado = request.getParameter("selEstado");
                filtroEstado = (filtroEstado == null) ? "vacio" : filtroEstado.trim();

                // Contar registros
                EntrevistaDAO edao = new EntrevistaDAO();
                int rowCount = edao.getRowCount(fechaDesde, fechaHasta, filtroDni, filtroNombre, filtroVendedor, filtroCanal, filtroEstado);
                request.setAttribute("rc", rowCount);

                // Establecer fragmento de la lista a mostrar
                int inicio = Integer.parseInt(request.getParameter("inicio"));
                out.println(inicio);
                request.setAttribute("inicio", inicio);

                // Generar lista de entrevistas                                             
                List<Entrevista> listaFiltrada = edao.filtrar(fechaDesde, fechaHasta, filtroDni, filtroNombre, filtroVendedor, filtroCanal, filtroEstado, inicio);
                request.setAttribute("listae", listaFiltrada);

                // Generar lista de vendedores
                VendedorDAO vdao = new VendedorDAO();
                List<Vendedor> listav = vdao.listar();
                request.setAttribute("listav", listav);

                // Generar lista de canales
                CanalDAO cdao = new CanalDAO();
                List<Canal> listac = cdao.listar();
                request.setAttribute("listac", listac);

                // Generar lista de estados 
                EstadoEntrevistaDAO eedao = new EstadoEntrevistaDAO();
                List<EstadoEntrevista> listaest = eedao.listar();
                request.setAttribute("listaest", listaest);

                // Indicar parámetos de filtrado
                request.setAttribute("fechadesde", fechaDesde);
                request.setAttribute("fechahasta", fechaHasta);
                request.setAttribute("filtroDni", filtroDni);
                request.setAttribute("filtroNombre", filtroNombre);

                // Indicar valores de combos
                request.setAttribute("vVendedor", (filtroVendedor.equalsIgnoreCase("vacio")) ? "vacio" : filtroVendedor);
                request.setAttribute("filtroVendedor", (filtroVendedor.equalsIgnoreCase("vacio")) ? "Seleccione un Vendedor" : filtroVendedor);
                request.setAttribute("vCanal", (filtroCanal.equalsIgnoreCase("vacio")) ? "vacio" : filtroCanal);
                request.setAttribute("filtroCanal", (filtroCanal.equalsIgnoreCase("vacio")) ? "Seleccione un Canal" : filtroCanal);
                request.setAttribute("vEstado", (filtroEstado.equalsIgnoreCase("vacio")) ? "vacio" : filtroEstado);
                request.setAttribute("filtroEstado", (filtroEstado.equalsIgnoreCase("vacio")) ? "Seleccione un Estado" : filtroEstado);

                request.getRequestDispatcher("entrevistas/listar-entrevistas.jsp").forward(request, response);
                break;

            case "nuevaEntrevista":
                // Generar lista de vendedores
                VendedorDAO vdao3 = new VendedorDAO();
                List<Vendedor> listav3 = vdao3.listar();
                request.setAttribute("listav", listav3);
                
                // Generar lista de canales
                CanalDAO cdao3 = new CanalDAO();
                List<Canal> listac3 = cdao3.listar();
                request.setAttribute("listac", listac3);
                request.getRequestDispatcher("entrevistas/registrar-entrevista.jsp").forward(request, response);
                break;

            case "mostrarRdosEnt":
                // Recuperar el Id de la entrevista a mostrar
                String id = request.getParameter("id");
                
                // Obtener la lista de resultados de la entrevista seleccionada
                ResultadoDAO rdao = new ResultadoDAO();
                List<Resultado> listares = rdao.listar(id);
                
                // Obtener los datos de la emprevista a mostrar
                EntrevistaDAO edao2 = new EntrevistaDAO();
                Entrevista ent2 = edao2.recuperar(id);
                
                // Enviar los datos a la vista
                request.setAttribute("entrevista", ent2);
                request.setAttribute("listares", listares);
                request.getRequestDispatcher("entrevistas/mostrar-resultados-ent.jsp").forward(request, response);
                break;

            case "Desistir":
                // Cambiar estado a disistida
                String id3 = request.getParameter("identre");                
                EntrevistaDAO edao3 = new EntrevistaDAO();
                edao3.cambiarEstado(Integer.parseInt(id3), 10);
                
                // Mostrar resultados con estado modificado                
                ResultadoDAO rdao3 = new ResultadoDAO();
                List<Resultado> listares3 = rdao3.listar(id3);
                request.setAttribute("listares", listares3);
                Entrevista ent3 = edao3.recuperar(id3);
                request.setAttribute("entrevista", ent3);
                request.getRequestDispatcher("entrevistas/mostrar-resultados-ent.jsp").forward(request, response);
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
