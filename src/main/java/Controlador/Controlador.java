package Controlador;

import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");        
        PrintWriter out = response.getWriter();
        out.println("acción:" + accion);
        switch (accion) {

            case "Ingresar":
                
                String usuario = request.getParameter("txtuser");
                String password = request.getParameter("txtpass");                
                UsuarioDAO userDAO = new UsuarioDAO();
                Usuario user = new Usuario();                
                user = userDAO.validar(usuario, password);                
                if (user.getUsuario() != null) {
                    // out.println("Ingreso OK");
                    request.setAttribute("usuario", user);
                    response.setStatus(200);
                    request.getRequestDispatcher("Controlador?accion=Principal").forward(request, response);
                } else {
                    // out.println("Ingreso NO OK");
                    response.setStatus(400);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            
            case "Salir":    
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
                
            case "Principal":
                request.getRequestDispatcher("principal.jsp").forward(request, response);
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
