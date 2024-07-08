package Controlador;

import Modelo.Entrevista;
import Modelo.EntrevistaDAO;
import Modelo.Prospecto;
import Modelo.ProspectoDAO;
import Modelo.Regla;
import Modelo.ReglaDAO;
import Modelo.Resultado;
import Modelo.ResultadoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MotorDecision extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        PrintWriter out = response.getWriter();
        Entrevista entrevista = new Entrevista();        
        EntrevistaDAO edao = new EntrevistaDAO();        
        String accion = request.getParameter("accion");
        String canal="";
        
        switch (accion) {
            case  "evaluarNuevaEntrevista":
                 // Recuperar variables del formulario
                String dni = request.getParameter("txtDni");
                String nombre = request.getParameter("txtNombre");
                String telefono = request.getParameter("txtTelefono");
                String email = request.getParameter("txtEmail");
                String vendedor = request.getParameter("selVendedor");
                canal = request.getParameter("selCanal");                
        
                // Buscar prospecto. Si ya existìa, se lo sobreescribe. Si no, se lo crea.
                Prospecto prospecto = new Prospecto(dni, nombre, email, telefono);        
                ProspectoDAO pdao = new ProspectoDAO();
                if (pdao.encontrar(prospecto.getDni())) pdao.modificar(prospecto);
                else pdao.agregar(prospecto);        
        
                // Registrar nueva entrevista                
                long miliseconds = System.currentTimeMillis();
                Date hoy = new Date(miliseconds);
                entrevista.setCanal(canal);
                entrevista.setVendedor(vendedor);
                entrevista.setFecha(hoy);
                entrevista.setDni(dni);
                entrevista.setEstado_id(5);
                entrevista.setId(edao.registrar(entrevista));
                break;

            case "evaluarEntrevistaExistente":
                 // Recuperar variables del formulario
                String id = request.getParameter("id");
                entrevista = edao.recuperar(id);
                // out.println("Analizando entevista: " +entrevista.getId());
                canal = String.valueOf(entrevista.getCanal_id());
                // out.println("Canal:" +canal+".");
                break;
            }
        
        // Obtener listado de reglas
        ReglaDAO rdao = new ReglaDAO();
        List<Regla> listareglas = rdao.listar(canal);

        
        for (Regla x: listareglas) {
            out.print("Id:" + x.getId()+" ");
            out.print("Descripcion:" + x.getDescripcion() +" ");
            out.print("Nombre Corto:" + x.getNombre_corto()+" ");
            out.print("Aceptacion:" + x.getAceptacion()+" ");
            out.print("Prioridad:" + x.getPrioridad()+" ");
            out.println("Fuente:" + x.getFuente()+" ");            
            }
        
        // Borrar resultados previos, si hubiera para la misma entrevista
        Resultado resultado = new Resultado();
        ResultadoDAO resdao = new ResultadoDAO();
        resdao.borrar(entrevista.getId());
        
        
        // Determinar máxima prioridad
        int maxpriority = 0;
        int prioridad = 0;
        for(Regla registro:listareglas) {
            prioridad = registro.getPrioridad();
            if (prioridad > maxpriority) maxpriority = prioridad;
        }

        // out.println("Maxima prioridad: " + maxpriority);        
        
        // Evaluar las reglas en orden de prioridad
        Evaluar:
        for(int p=1; p < maxpriority + 1; p++) {
            // out.println("Evaluando prioridad..." + p);
            for(Regla criterio:listareglas) {
                // out.println("Analizando regla..." + criterio.getNombre_corto());
                if (criterio.getPrioridad() != p) {
                    // out.println("Saltear regla");
                    continue;}
                out.println("Evaluando regla..." + criterio.getNombre_corto());                
                resultado.setValor(obtenerValor(criterio, entrevista.getDni()));                
                resultado.setEntrevista(entrevista.getId());
                resultado.setRegla(criterio.getId());                
                resultado.setPass(evaluar(criterio,resultado.getValor()));
                resultado.setNombre_corto(criterio.getNombre_corto());
                resultado.setDescripcion(criterio.getDescripcion());
                resultado.setAceptacion(criterio.getAceptacion());
                out.println("Agregando resultado a la lista");
                // listares.add(resultado);                
                out.println("Registrando resultado");
                resdao.registrar(resultado);

                // out.println("Evaluando continuidad...");
                if (!resultado.isPass()) 
                {
                    out.println("Regla no superada. Escapàndo evaluacion");
                    break Evaluar;                
                } else {
                    out.println("Regla superada");
                }
            }
        }

        out.println("Mostrando resultados...");
        List<Resultado> listares = new ArrayList<>();  
        listares = resdao.listar(String.valueOf(entrevista.getId()));
        for (Resultado z:listares) {
            out.print("Entrevista: " + z.getEntrevista());
            out.print(" Regla: " + z.getRegla());
            out.print(" Nombre corto: " + z.getNombre_corto()); 
            out.print(" Descripcion: " + z.getDescripcion());
            out.print(" Aceptacion: " + z.getAceptacion());
            out.print(" Valor: " + z.getValor());
            out.println(" Passs: " + z.isPass());            
            }

        // Registrar el resultado de la entrevista (1=procedente, 3=rechazada)
        if (resultado.isPass()) {
            entrevista.setEstado_id(1);
            entrevista.setLcm(obtenerLcm(entrevista.getDni()));
            entrevista.setCe(obtenerCe(entrevista.getDni()));
            edao.asignarLimites(entrevista.getId(), entrevista.getLcm(), entrevista.getCe());
            }
        else entrevista.setEstado_id(3);        
        edao.cambiarEstado(entrevista.getId(), entrevista.getEstado_id());


        // Mostrar resultados
        entrevista = edao.recuperar(String.valueOf(entrevista.getId()));
        request.setAttribute("entrevista", entrevista);
        request.setAttribute("listares", listares);                
        request.getRequestDispatcher("entrevistas/mostrar-resultados-ent.jsp").forward(request, response);          
        
    }

    private double obtenerLcm(String dni) {
        double nse = Math.random();
        return Math.round((-164360*nse +222221)*0.45);
    }
    
    private double obtenerCe(String dni) {
        return 3;
    }
    
    
    private String obtenerValor(Regla regla, String DNI) {
        
        String temp ="vacio";
        double Dni = 0, Edad = 0, aprobacion = Math.random();        
        int edad = 0;
        // Conectarse a la fuente y obtener el resultado para el DNI dado
        switch (regla.getNombre_corto()) {
            case "CREDIMAS":
                temp ="N";
                break;
            case "BUREAU":                
                if (aprobacion< 0.15) temp = String.valueOf(Math.round(3*Math.random()+2));
                else temp ="1";
                break;
            case "EDAD":
                Dni = Integer.parseInt(DNI) / 1000000;
                Edad = 0.0199 * Dni * Dni - 2.6164 * Dni + 97.988;
                Edad = Math.round(Edad);
                edad = (int) Edad;
                System.out.println("Edad: "+ edad);
                temp = String.valueOf(edad);
                break;
            case "SCORE":
                if (aprobacion < 0.15) temp = String.valueOf(Math.round(343.29*Math.random()-3.2929));
                else temp = String.valueOf(Math.round(780.72*Math.random()+226.08));                
                break;
            case "IDENTIDAD":
                temp ="S";
                break;
            case "REFCOM":
                temp ="N";
                break;
            case "IIC":
                temp ="N";;
                break;                           
        }
        // Comparar el valor obtenido con el valor de aprobación
        
        return temp;
    }
    
    private boolean evaluar(Regla regla, String valor) {
        System.out.println("Regla: "+ regla.getNombre_corto() + " Aceptacion=" + regla.getAceptacion());
        String comparador= regla.getAceptacion().substring(0,1);        
        String aceptacion= regla.getAceptacion().substring(1,regla.getAceptacion().length());
        System.out.println("Comparador=" + comparador);
        System.out.println("Aceptacion=" + aceptacion);
        System.out.println("Valor=" + valor);
        boolean temp = false;
        switch (comparador) {
            case "=":
                temp = valor.equals(aceptacion);                
                break;
            case ">":
                temp = Integer.parseInt(valor) > Integer.parseInt(aceptacion);
                break;
            case "<":
                temp = Integer.parseInt(valor) < Integer.parseInt(aceptacion);
                break;
        }        
        return temp;
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
