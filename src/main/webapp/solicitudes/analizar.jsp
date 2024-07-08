<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%> 

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
        <title>Analizar Solicitud de Crédito</title>
    </head>
    <body>

        <div class="container-fluid">

            <div class="card" >
                <div class="card-header">
                    <h5 class="card-title">Datos internos</h5>
                </div>

                <div class="card-body">

                    <div class="row">

                        <div class="col-md-3">                
                            <label class="form-label-sm">Nº Solicitud: ${solicitud.getIdSol()}</label>                                
                        </div>  

                        <div class="col-md-2">                
                            <label class="form-label-sm">Fecha: ${solicitud.getFecha()}</label>                                
                        </div> 

                        <div class="col-md-3">
                            <label class="form-label-sm">Vendedor: ${solicitud.getVendedor()}</label>
                        </div>

                        <div class="col-md-3">
                            <label class="form-label-sm">Canal: ${solicitud.getCanal()} </label>
                        </div>

                    </div>

                    <div class="row">

                        <div class="col-md-3">
                            <label class="form-label-sm">Sucursal: ${solicitud.getSucursal()}</label>                                
                        </div>

                        <div class="col-md-2">
                            <label class="form-label-sm">Modalidad: ${solicitud.getModalidad()}</label>                                
                        </div>

                        <div class="col-md-3">
                            LCM: <input class="form-imput-sm" name="lcm" value="${solicitud.getLcm()}" form="formAnalizar">                                
                        </div>

                        <div class="col-md-3">
                            CE: <input class="form-input-sm" name="ce" value="${solicitud.getCe()}" form="formAnalizar">                                
                        </div>

                    </div>                                    

                </div>
            </div>

            <!-- Tab personales -->

            <div class="card mt-4" >
                <div class="card-header">
                    <h5 class="card-title">Datos personales</h5>
                </div>

                <div class="card-body">

                    <div class="row">

                        <div class="col-md-3">
                            <label class="form-label-sm">DNI: ${solicitud.getDni()}</label>                                    
                        </div>

                        <div class="col-2">
                            <label class="form-label-sm">CUIL: ${solicitud.getCuil()}</label>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label-sm">Apellido y nombre: ${solicitud.getNombre()}</label>                                
                        </div>

                        <div class="col-2">
                            <label class="form-label-sm">Sexo: ${solicitud.getSexo()}</label>                                
                        </div>



                    </div>



                    <div class="row">

                        <div class="col-3">
                            <label class="form-label-sm">Fecha de Nacimiento: ${solicitud.getFechaNacimiento()}</label>                    
                        </div>

                        <div class="col-2">
                            <label class="form-label-sm">Nacionalidad: ${solicitud.getNacionalidad()}</label>                                
                        </div>

                        <div class="col-md-2">                                
                            <label class="form-label-sm">PEP: ${solicitud.isPEP()}</label>                                                                
                        </div>

                    </div>

                    <div class="row">

                        <div class="col-3">
                            <label class="form-label-sm">Calle: ${solicitud.getCalle()}</label>                
                        </div>
                        <div class="col-1">
                            <label class="form-label-sm">Nro: ${solicitud.getNro()}</label>                
                        </div>
                        <div class="col-1">
                            <label class="form-label-sm">Piso: ${solicitud.getPiso()}</label>                
                        </div>
                        <div class="col-1">
                            <label class="form-label-sm">Dpto: ${solicitud.getDpto()}</label>                
                        </div>
                        <div class="col-1">
                            <label class="form-label-sm">Mza: ${solicitud.getMza()}</label>                                                
                        </div>
                        <div class="col-1">
                            <label class="form-label-sm">Lote: ${solicitud.getLote()}</label>                
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-3">
                            <label class="form-label-sm">Barrio: ${solicitud.getBarrio()}</label>                
                        </div>

                        <div class="col-3">
                            <label class="form-label-sm">Localidad: ${solicitud.getLocalidad()}</label>                
                        </div>
                        <div class="col-1">
                            <label class="form-label-sm">CP: ${solicitud.getCp()}</label>                
                        </div>
                        <div class="col-3">
                            <label class="form-label-sm">Provincia: ${solicitud.getProvincia()}</label>                                
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-3">
                            <label class="form-label-sm">Teléfono: ${solicitud.getTelefono()}</label>                
                        </div>
                        <div class="col-4">
                            <label class="form-label-sm">Email: ${solicitud.getEmail()}</label>                    
                        </div>

                    </div>

                </div>
            </div>


            <!-- Tab laborales -->

            <div class="card mt-4" >
                <div class="card-header">
                    <h5 class="card-title">Datos laborales</h5>
                </div>

                <div class="card-body">

                    <div class="row">
                        <div class="col-md-3">
                            <label class="form-label-sm">CUIT Empleador: ${solicitud.getEmpleador()} </label>                            
                        </div>                                

                        <div class="col-md-4">
                            <label class="form-label-sm">Razón social / denominación: ${solicitud.getNombreEmpleador()}</label>                            
                        </div>                                
                    </div>

                    <div class="row">

                        <div class="col-md-3">                                
                            <label class="form-label-sm">Ocupacion: ${solicitud.getOcupacion()}</label>                                

                        </div>
                        <div class="col-md-2">
                            <label class="form-label-sm">Ingresos netos: ${solicitud.getIngresos()}</label>                            
                        </div>
                        <div class="col-md-2">
                            <label class="form-label-sm">Fecha de Ingreso: ${solicitud.getFechaIngreso()}</label>                            
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3">
                            <label class="form-label-sm">Calle: ${solicitud.getCalleL()}</label>                            
                        </div>
                        <div class="col-md-1">
                            <label class="form-label-sm">Nro: ${solicitud.getNroL()}</label>                            
                        </div>
                        <div class="col-md-1">
                            <label class="form-label-sm">Piso: ${solicitud.getPisoL()}</label>                            
                        </div>
                        <div class="col-md-1">
                            <label class="form-label-sm">Dpto: ${solicitud.getDptoL()}</label>                            
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-md-3">
                            <label class="form-label-sm">Localidad: ${solicitud.getLocalidadL()}</label>                            
                        </div>
                        <div class="col-md-2">
                            <label class="form-label-sm">CP: ${solicitud.getCpL()}</label>                            
                        </div>
                        <div class="col-md-3">
                            <label class="form-label-sm">Provincia: ${solicitud.getProvinciaL()}</label>
                        </div>

                        <div class="col-md-3">
                            <label class="form-label-sm">Teléfono laboral: ${solicitud.getTelefonoL()}</label>                            
                        </div>
                    </div>

                </div>
            </div>



            <!-- Botones -->

            <form action="ControladorSolicitudes" method="POST" id="formAnalizar">
                <input type="hidden" name="idS" value="${solicitud.getIdSol()}">
                <div class="row mb-3 mt-3">

                    <div class="row mb-3">
                        <div class="col-sm-2">
                            <label>Nuevo estado</label>
                            <select name="selEstado" id="estado2" class="form-select">

                                <option selected value="<c:out value="${solicitud.getId_estado()}"></c:out>"> <c:out value="${solicitud.getEstado()}"></c:out></option>
                                <c:forEach var="state" items="${listaest}">
                                    <option value="<c:out value="${state.id}"></c:out>"><c:out value="${state.nombre}"></c:out></option>
                                </c:forEach>

                            </select>

                        </div>

                        <div class="col-sm-3">
                            <label>Tipo de rechazo</label>

                            <select class="form-select" name="selTipo">
                                <option selected value="${vRechazo}">${filtroRechazo}</option>
                                <c:forEach var="tipo" items="${lista_tipos}">
                                    <option value="<c:out value="${tipo.id}"></c:out>"><c:out value="${tipo.nombre}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-sm-2">
                            <label>Causal</label>                                               
                            <select class="form-select" name="selCausal">
                                <option selected value="${vCausal}">${filtroCausal}</option>
                                <c:forEach var="causal" items="${lista_causales}">
                                    <option value="<c:out value="${causal.id}"></c:out>"><c:out value="${causal.nombre}"></c:out></option>
                                </c:forEach>
                            </select>

                        </div>


                        <div class="col-sm-2">
                            <label>Aclaración</label>

                            <select class="form-select" name="selAclaracion">
                                <option selected value="${vAclaracion}"">${filtroAclaracion}</option>
                                <c:forEach var="aclaracion" items="${lista_aclaraciones}">
                                    <option value="<c:out value="${aclaracion.id}"></c:out>"><c:out value="${aclaracion.nombre}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">

                        <label for="observaciones" class="form-label">Observaciones</label>
                        <textarea class="form-control" name="observaciones" id="observaciones" rows="2">${solicitud.getObservaciones()}</textarea>

                    </div>

                    <div class="row mb-3">
                        <div class="col-auto">
                            <button type="submit" class="btn btn-danger" name="accion" value="mostrarAdjuntos" role="button">Mostrar documentos adjuntos</button>
                        </div>

                        <div class="col-auto">
                            <button type="submit" name="accion" value="cambiarEstado" class="btn btn-danger">Grabar nuevo estado</button>
                        </div>

                        <div class="col-auto">
                            <a class="btn btn-danger" href="ControladorSolicitudes?accion=Solicitudes&inicio=0" role="button">Volver</a>
                        </div>
                    </div>

            </form>
        </div>

    </div>

</body>
</html>