package Controlador;

import Modelo.ReporteProspectoPorEstado;
import Modelo.ReporteProspectoPorEstadoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorReportes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        PrintWriter out = response.getWriter();
        switch (accion) {

            case "reportes":
                request.getRequestDispatcher("reportes/reportes.jsp").forward(request, response);
                break;

            case "generarReporte":
                String desde = request.getParameter("fecha-desde");
                String hasta = request.getParameter("fecha-hasta");
                String reporte = request.getParameter("sel-reporte");
                String respuesta = "";                
                switch (reporte) {
                    case "0":
                        request.setAttribute("tipoError", "Debe seleccionar un reporte de la lista");
                        respuesta = "reportes/error-reporte.jsp";
                        break;
                    case "1":
                        if (desde.isEmpty() || hasta.isEmpty()) {
                            request.setAttribute("tipoError", "Debe indicar fechas de inicio y fin");
                            respuesta = "reportes/error-reporte.jsp";
                        } else {
                            ReporteProspectoPorEstadoDAO reporteDAO = new ReporteProspectoPorEstadoDAO();
                            List<ReporteProspectoPorEstado> reporteFinal = reporteDAO.listar(Date.valueOf(desde), Date.valueOf(hasta));
                            request.setAttribute("reporte", reporteFinal);
                            respuesta = "reportes/reporte_prospectos_por_estado.jsp";
                        }
                        break;
                }
                request.getRequestDispatcher(respuesta).forward(request, response);
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
