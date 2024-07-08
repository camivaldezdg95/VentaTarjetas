<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="estilos.css">
                
        <title>Venta de tarjetas</title>
    </head>
    <body>

        <!-- Barra de navegación -->
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
            <div class="container-fluid" style="justify-content: flex-start">
                <a class="navbar-brand" href="#">
                    <img src="img/Logo.png" alt="" height="30" alt="Logo" />
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" aria-current="page" href="ControladorEntrevistas?accion=Entrevistas&inicio=0" target="myFrame">Entrevistas</a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="ControladorSolicitudes?accion=Solicitudes&inicio=0" target="myFrame">Solicitudes de Crédito</a>
                        </li>                        
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="ControladorReportes?accion=reportes" target="myFrame">Reportes de gestión</a>
                        </li>                        
                    </ul>
                </div>

                <div class="dropdown">
                    <button type="button" style="margin-left: 10px; border: none; float: right;" class="btn btn-outline-light dropdown-toggle" data-bs-toggle="dropdown">
                        ${usuario.getNombre()}
                    </button>
                    <div class="dropdown-menu dropdown-menu-end text-center">
                        <a class="dropdown-item" href="#"><img src="img/user.jpg" alt="usuario" height="60" width="60"/></a>
                        <span class="dropdown-item-text">${usuario.getUsuario()}</span>
                        <span class="dropdown-item-text">${usuario.getEmail()}</span>
                        <hr class="dropdown-divider">
                        <form method="POST" action="Controlador">                            
                                <button name="accion" value="Salir" class="dropdown-item">Cerrar sesión</button>                            
                        </form>
                    </div>
                </div>

            </div>
        </nav>

<!-- Pantalla principal -->                        
                       
<div class="mt-4" style="height: 90vh">
    <iframe name="myFrame" style="height:100%; width:100%">
        
    </iframe>
</div>

    </body>
</html>
