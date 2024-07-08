<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>        
        <title>Resultados entrevista</title>
    </head>
    <body>

        <div class="container">

            <h5>Resultados de Entrevista</h5>
            <br> 
            <form id="form-evaluar" method="POST" action="MotorDecision?accion=evaluarEntrevistaExistente">
                <div class="row">
                    <div class="col">
                        <label>Entrevista:  </label>
                        <input type="text" id="id" style="border-style: none;" readonly name="id" value="<c:out value="${entrevista.id}"></c:out>" />
                        </div>                               
                        <div class="col">Estado de la entrevista: <label id="etiqueta"> <c:out value="${entrevista.estado}"></c:out></label></div>
                    </div>
                    <div class="row">
                        <div class="col">DNI:  <c:out value="${entrevista.dni}"></c:out></div>                
                    <div class="col">Nombre: <c:out value="${entrevista.nombre}"></c:out></div>
                    </div>
                    <div class="row">
                        <div class="col">Vendedor: <c:out value="${entrevista.vendedor}"></c:out></div>
                    <c:set var="lct" value="${entrevista.canal}"/>
                    <div class="col">Canal: <c:out value="${lct}"></c:out></div>
                    </div>
                    <div class="row">                
                        <div class="col">Límite de crédito mensual:
                        <c:set var="lcm" value="${entrevista.lcm}" />
                        <fmt:setLocale value = "es_CO"/>
                        <fmt:formatNumber value="${lcm}" type="currency" currencySymbol="$ " minFractionDigits="2" />
                    </div>
                    <c:set var="lct" value="${entrevista.ce*lcm}"/>
                    <div class="col">Límite de crédito total: <fmt:formatNumber value="${lct}" type="currency" currencySymbol="$ " minFractionDigits="2" />
                    </div>
                </div>
            </form>
            <br>
            <table class="table table-striped table-responsive" id="resultado-ent">                
                <thead>
                    <tr>
                        <th>id regla</th>
                        <th>Nombre corto</th>
                        <th>Descripción</th>
                        <th>Valor</th>
                        <th>Valor de aceptación</th>
                        <th>Pass / fail</th>                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="rdose" items="${listares}">
                        <tr>
                            <td> <c:out value="${rdose.regla}"></c:out></td>
                            <td> <c:out value="${rdose.nombre_corto}"></c:out></td>                            
                            <td> <c:out value="${rdose.descripcion}"></c:out></td>
                            <td> <c:out value="${rdose.valor}"></c:out></td>
                            <td> <c:out value="${rdose.aceptacion}"></c:out></td>
                            <td> <c:out value="${rdose.pass}"></c:out></td>                            
                            </tr>
                    </c:forEach>
                </tbody>
            </table>   

            <!-- Botones-->
            <br>
            <div class="row g-10 mb-3">
                <div class="col-auto">
                    <button class="btn btn-danger" id="btnEvaluar" type="submit" form="form-evaluar" role="button">Evaluar</button>                        
                </div>

                <div class="col-auto">
                    <a class="btn btn-danger" id="btnSC" href="ControladorSolicitudes?accion=nuevaSolicitud&ent_id=<c:out value="${entrevista.id}"></c:out>" role="button">Confeccionar Solicitud de Crédito</a>                        
                    </div>

                    <div class="col-auto">
                        <a class="btn btn-danger" id="btnDesistir" href="ControladorEntrevistas?accion=Desistir&identre=<c:out value="${entrevista.id}"></c:out>" role="button">Desistir</a>                        
                </div>

                <div class="col-auto">
                    <a class="btn btn-secondary" id="btnVolver" href="ControladorEntrevistas?accion=Entrevistas&inicio=0" role="button">Volver</a>                        
                </div>
            </div>
            <script>
                window.onload = modificarBotones;

                function modificarBotones() {
                    var estado = document.getElementById('etiqueta');
                    var btnEvaluar = document.getElementById('btnEvaluar');
                    var btnSC = document.getElementById('btnSC');
                    var btnDesistir = document.getElementById('btnDesistir');
                    console.log("1" + estado.textContent + "1");
                    switch (estado.textContent) {
                        case ' Procedente':
                        {
                            console.log("Proc");
                            btnEvaluar.classList.add("disabled");
                            btnSC.classList.remove("disabled");
                            btnDesistir.classList.remove("disabled");
                            break;
                        }
                        case ' Considerar':
                        {
                            console.log("Consid");
                            btnEvaluar.classList.add("disabled");
                            btnSC.classList.remove("disabled");
                            btnDesistir.classList.remove("disabled");
                            break;
                        }
                        case ' Nueva':
                        {
                            console.log("Nuev");
                            btnEvaluar.classList.remove("disabled");
                            btnSC.classList.add("disabled");
                            btnDesistir.classList.remove("disabled");
                            break;
                        }
                        case ' Error de comunicacion':
                        {
                            console.log("Error-com");
                            btnEvaluar.classList.remove("disabled");
                            btnSC.classList.add("disabled");
                            btnDesistir.classList.remove("disabled");
                            break;
                        }
                        case ' Rechazada':
                        {
                            console.log("Rechazada");
                            btnEvaluar.classList.add("disabled");
                            btnSC.classList.add("disabled");
                            btnDesistir.classList.add("disabled");
                            break;
                        }
                        case ' Desistida':
                        {
                            console.log("Desist");
                            btnEvaluar.classList.add("disabled");
                            btnSC.classList.add("disabled");
                            btnDesistir.classList.add("disabled");
                            break;
                        }
                    }
                }

            </script>
    </body>

</html>
