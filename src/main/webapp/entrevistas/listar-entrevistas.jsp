<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%> 
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

        <title>Entrevistas</title>
    </head>
    <body>

        <div class="grid-container">

            <div class="nada">

            </div>

            <div class="header">
                <h5>Entrevistas</h5>
            </div>

            <div class="derecha">            
                <a class="btn btn-danger" href="ControladorEntrevistas?accion=nuevaEntrevista" role="button">Nueva
                    Entrevista</a>

            </div>

            <div class="aside">
                <!-- Menu de filtros-->
                <form name="filtros" id="filtros" action="ControladorEntrevistas?accion=Entrevistas&inicio=0" method="POST">
                    <label for="fecha-desde" class="form-label">Fecha Desde</label>
                    <input type="date" class="form-control" name="fechadesde" id="fecha-desde" value="<c:out value="${fechadesde}" />">

                    <label for="fecha-hasta" class="form-label mt-2">Fecha Hasta</label>
                    <input type="date" class="form-control" name="fechahasta" id="fecha-hasta" value="<c:out value="${fechahasta}" />">

                    <label for="filtro-dni" class="form-label mt-2">DNI</label>
                    <input type="text" class="form-control" placeholder="Ingrese un DNI" id="filtro-dni" name="filtrodni" value="<c:out value="${filtroDni}" />">

                    <label for="filtro-nombre" class="form-label mt-2">Nombre</label>
                    <input type="text" class="form-control" placeholder="Ingrese un nombre" id="filtro-nombre" name="filtronombre" value="<c:out value="${filtroNombre}" />">

                    <label for="filtro-vendedor" class="form-label mt-2">Vendedor</label>                
                    <select class="form-select" name="selVendedor" id="filtro-vendedor" >

                        <option selected value="<c:out value="${vVendedor}"></c:out>"><c:out value="${filtroVendedor}"></c:out></option>

                        <c:forEach var="vendedor" items="${listav}">
                            <option value="<c:out value="${vendedor.nombre}"></c:out>"> <c:out value="${vendedor.nombre}"></c:out></option>                                                      
                        </c:forEach>
                    </select>

                    <label for="filtro-canal" class="form-label mt-2">Canal</label>                
                    <select class="form-select" name="selCanal" id="filtro-canal">
                        <option selected value="<c:out value="${vCanal}"></c:out>"><c:out value="${filtroCanal}"></c:out></option>
                        <c:forEach var="canal" items="${listac}">
                            <option value="<c:out value="${canal.nombre}"></c:out>"> <c:out value="${canal.nombre}"></c:out></option>
                        </c:forEach>
                    </select>

                    <label for="filtro-estado" class="form-label mt-2">Estado</label>
                    <select class="form-select" name="selEstado" id="filtro-estado">

                        <option selected value="<c:out value="${vEstado}"></c:out>"><c:out value="${filtroEstado}"></c:out></option>

                        <c:forEach var="state" items="${listaest}">
                            <option value="<c:out value="${state.nombre}"></c:out>"> <c:out value="${state.nombre}"></c:out></option>
                        </c:forEach>
                    </select>

                    <div class="d-grid gap-2">
                        <button class="btn btn-danger mt-3" onClick="cambiarInicio(0)">Aplicar filtros</button>
                    </div>
                </form>
            </div>

            <div class="main">
                <table class="table table-striped table-responsive" id="entrevistast">

                    <thead>
                        <tr>
                            <th>id</th>
                            <th>Fecha</th>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Vendedor</th>
                            <th>Canal</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="entrevista" items="${listae}">

                            <tr>
                                <td>
                                    <c:out value="${entrevista.id}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${entrevista.fecha}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${entrevista.dni}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${entrevista.nombre}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${entrevista.vendedor}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${entrevista.canal}"></c:out>
                                    </td>
                                    <td>
                                    <c:out value="${entrevista.estado}"></c:out>
                                    </td>
                                    <td> <a href="ControladorEntrevistas?accion=mostrarRdosEnt&id=<c:out value=" ${entrevista.id}" />"
                                        ><img src="img/detalles.png" alt="Detalles" title="Detalles" height="30" width="30" /></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

            <div class="footer">

                <p> Mostrando desde <c:out value="${(rc==0)? 0: inicio+1}"/> hasta <c:out value="${(inicio+10>rc)? rc: inicio+10}"/> de <c:out value="${rc}" ></c:out> registros</p>

                    <div class="list-group list-group-horizontal">

                        <button class="list-group-item list-group-item-dark" onClick="cambiarInicio(0)">Inicio</button>
                        
                        <button class="list-group-item list-group-item-dark" onClick="cambiarInicio(${(inicio-10<0)? 0: inicio-10})">Anterior</button>
                        
                        <button class="list-group-item list-group-item-dark" onClick="cambiarInicio(${(inicio+10 > rc)? inicio: inicio+10})">Siguiente</button>
                    
                        <button class="list-group-item list-group-item-dark" onClick="cambiarInicio(${(rc-10<0) ? 0: rc-10})">Final</button>

                    </div>

            </div>

        </div>

        <script>
            
            function cambiarInicio(nuevoinicio) {
                var formulario = document.getElementById("filtros");
                console.log("vieja acción: ");
                console.log(formulario.action);
                formulario.action = "ControladorEntrevistas?accion=Entrevistas&inicio=" + nuevoinicio;
                console.log("nueva acción: ");
                console.log(formulario.action);
                formulario.submit();                        
            }
        </script>

    </body>
</html>
