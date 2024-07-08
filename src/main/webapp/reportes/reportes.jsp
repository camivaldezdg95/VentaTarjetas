<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
        <title>Reportes de gestión</title>
    </head>

    <body>

        <div class="container">

            <form method="POST" action="ControladorReportes?accion=generarReporte" target="frame-reportes">

                <div class="row g-10 mb-3">
                    <div class="col-auto">

                        <label for="fecha-desde" class="form-label">Fecha Desde:</label>
                        <input type="date" name="fecha-desde" class="form-control" />
                    </div>
                    <div class="col-auto">

                        <label for="fecha-desde" class="form-label">Fecha Hasta:</label>
                        <input type="date" name="fecha-hasta" class="form-control" />

                    </div>
                </div>


                <div class="row g-10 mb-3">
                    <div class="col-auto">

                        <select class="form-select" name="sel-reporte" id="select-reporte" required>
                            <option value="0" selected>Seleccione el reporte</option>
                            <option value="1">Prospectos por estado y canal</option>
                            <option value="2">Solicitudes de Crédito por estado</option>
                            <option value="3">Motivos de rechazo de prospectos - resumido</option>
                            <option value="4">Motivos de rechazo de prospectos - detallado</option>
                            <option value="5">Motivos de rechazo de solicitudes - resumido</option>
                            <option value="6">Motivos de rechazo de solicitudes - detallado</option>
                        </select>
                    </div>
                </div>

                <!-- Botones-->

                <div class="row g-10 mb-3">
                    <div class="col-auto">
                        <button type="submit" class="btn btn-danger">Generar Reporte </button>
                    </div>            
                    <div class="col-auto">
                        <a class="btn btn-danger" " href="Controlador?accion=Principal" target="parent" role="button">Salir</a>                           
                    </div>
                </div>

            </form>

            <div class="mt-4" style="height: 70vh">
                <iframe name="frame-reportes" style="height:100%; width:100%">

                </iframe>
            </div>
        </div>

    </body>

</html>