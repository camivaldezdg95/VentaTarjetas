<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%> 
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
        <title>Registrar entrevista</title>
    </head>

    <body>
        <div class="container">
            <h5>Nueva Entrevista</h5>
            <br>
            <form action="MotorDecision?accion=evaluarNuevaEntrevista" method="POST">

                <div class="row g-10 mb-3">
                    <div class="col-sm-2">
                        <label class="form-label">DNI:</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" minlength="7" maxlength="8" pattern="[0-9]+" class="form-control" name="txtDni" placeholder="Número de DNI">
                    </div>
                </div>

                <div class="row g-10 mb-3" >
                    <div class="col-sm-2">
                        <label class="form-label">Apellido y nombre:</label>
                    </div>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" maxlength="100" pattern="[A-Za-z]+[,][\s][A-Za-z\s]+" name="txtNombre" placeholder="Apellido, nombres">
                    </div>
                </div>

                <div class="row g-10 mb-3">
                    <div class="col-sm-2">
                        <label class="form-label">Teléfono:</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" pattern="[0-9]+" maxlength="20" name="txtTelefono" placeholder="Número de teléfono">
                    </div>
                </div>

                <div class="row g-10 mb-3">
                    <div class="col-sm-2">
                        <label class="form-label">Email:</label>
                    </div>
                    <div class="col-sm-4">
                        <input type="email" class="form-control" maxlength="100" name="txtEmail" placeholder="prospecto@gmail.com">
                    </div>
                </div>

                <div class="row g-10 mb-3">
                    <div class="col-sm-2">
                        <label class="form-label">Vendedor:</label>
                    </div>
                    <div class="col-sm-4">
                        <select class="form-select" aria-label="Select 1" name="selVendedor">
                            <option selected>Seleccione el vendedor</option>
                            <c:forEach var="vendedor" items="${listav}">
                                <option value="<c:out value="${vendedor.id}"></c:out>"> <c:out value="${vendedor.nombre}"></c:out></option>                                                      
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row g-10 mb-3">
                    <div class="col-sm-2">
                        <label class="form-label">Canal:</label>
                    </div>
                    <div class="col-sm-4">
                        <select class="form-select" aria-label="Select 2" name="selCanal">
                            <option selected>Seleccione el canal</option>
                            <c:forEach var="canal" items="${listac}">
                                <option value="<c:out value="${canal.id}"></c:out>"> <c:out value="${canal.nombre}"></c:out></option>
                            </c:forEach>
                        </select>

                    </div>
                </div>

                <!-- Botones-->

                <div class="row g-10 mb-3">
                    <div class="col-auto">
                        <button type="submit" name="btnEvaluar" id="boton-evaluar" class="btn btn-danger" value="evaluar">Evaluar </button>                        
                    </div>

                    <div class="col-auto">
                        <a class="btn btn-secondary" id="btnCancelar" href="ControladorEntrevistas?accion=Entrevistas&inicio=0" role="button">Cancelar</a>                        
                    </div>
                </div>
            </form>
        </div>


    </div>
</body>

</html>