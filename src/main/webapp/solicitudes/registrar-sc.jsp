<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%> 
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
        <title>Registrar Solicitud de Crédito</title>
    </head>

    <body>

        <div class="container">

            <form method="POST" action="ControladorSolicitudes?accion=registrarSolicitud" id="formSolicitud">

                <!-- Tabs navs -->

                <ul class="nav nav-tabs" id="tab-sc" role="tablist">

                    <li class="nav-item" role="presentation">
                        <a class="nav-link active" aria-current="page" href="#internos" role="tab"
                           id="tab-internos"
                           data-bs-toggle="tab"
                           aria-controls="internos"
                           aria-selected="true"
                           >Datos internos</a>
                    </li>

                    <li class="nav-item" role="presentation">
                        <a class="nav-link" href="#personales" role="tab"
                           id="tab-personales"
                           data-bs-toggle="tab"
                           aria-controls="personales"
                           aria-selected="false"
                           >Datos personales</a>
                    </li>

                    <li class="nav-item" role="presentation">
                        <a class="nav-link" href="#laborales" role="tab"
                           id="tab-laborales"
                           data-bs-toggle="tab"
                           aria-controls="laborales"
                           aria-selected="false"
                           >Datos laborales</a>
                    </li>
                </ul>

                <!-- Tabs content -->



                <div class="tab-content mt-5" id="contenido">

                    <!-- Tab internos -->
                    <div
                        class="tab-pane fade show active mt-5"
                        id="internos"
                        role="tabpanel"
                        aria-labelledby="tab-internos">

                        <div class="row">

                            <div class="col-md-2">                
                                <label for="txtSC" class="form-label">Nº Solicitud</label>
                                <input type="text" class="form-control" id="txtSC" name="txtSC">
                            </div>  

                            <div class="col-md-2">                
                                <label for="txtFecha" class="form-label">Fecha</label>
                                <input type="date" class="form-control" id="txtFecha" value="${hoy}" name="txtFecha">
                            </div> 
                        </div>

                        <div class="row mt-4">
                            <div class="col-md-3">
                                <label class="form-label">Vendedor</label>

                                <select class="form-select" name="selVendedor">

                                    <option selected value="<c:out value="${vVendedor}"></c:out>"><c:out value="${filtroVendedor}"></c:out></option>

                                    <c:forEach var="vendedor" items="${listav}">
                                        <option value="<c:out value="${vendedor.id}"></c:out>"> <c:out value="${vendedor.nombre}"></c:out></option>                                                      
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-3">
                                <label class="form-label">Canal</label>

                                <select class="form-select" name="selCanal">
                                    <option selected value="<c:out value="${vCanal}"></c:out>"><c:out value="${filtroCanal}"></c:out></option>
                                    <c:forEach var="canal" items="${listac}">
                                        <option value="<c:out value="${canal.id}"></c:out>"> <c:out value="${canal.nombre}"></c:out></option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>

                        <div class="row mt-4">

                            <div class="col-md-3">
                                <label class="form-label">Sucursal</label>

                                <select class="form-select" name="selSucursal">
                                    <option selected value="vacio">Seleccione la Sucursal</option>
                                    <c:forEach var="sucursal" items="${listasuc}">
                                        <option value="<c:out value="${sucursal.id}"></c:out>"> <c:out value="${sucursal.nombre}"></c:out></option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-3">
                                <label class="form-label">Modalidad</label>

                                <select class="form-select" aria-label="Select 5" name="selModalidad">
                                    <option selected value="vacio">Seleccione la modalidad</option>
                                    <c:forEach var="modalidad" items="${listam}">
                                        <option value="<c:out value="${modalidad.id}"></c:out>"> <c:out value="${modalidad.nombre}"></c:out></option>                                                      
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                                    
                                    <input type="hidden" name="lcm" value="${nuevaSC.lcm}">
                                    <input type="hidden" name="ce" value="${nuevaSC.ce}">
                                    <input type="hidden" name="presentar" id="presentar" value="no">

                    </div>

                    <!-- Tab personales -->

                    <div
                        class="tab-pane fade mt-5"
                        id="personales"
                        role="tabpanel"
                        aria-labelledby="tab-personales">  

                        <div class="row">

                            <div class="col-md-2">
                                <label for="txtDNI" class="form-label">DNI</label>
                                    <input type="text"  minlength="7" maxlength="8" pattern="[0-9]+" class="form-control" id="txtDNI" name="txtDNI" value="${nuevaSC.dni}">
                            </div>
                            <div class="col-md-3">
                                <label for="txtNombre" class="form-label">Apellido y nombre</label>
                                <input type="text" class="form-control" id="txtNombre" maxlength="100" pattern="[A-Za-z]+[,][\s][A-Za-z\s]+" name="txtNombre" value="${nuevaSC.nombre}">
                            </div>

                            <div class="col-2">
                                <label for="selSexo" class="form-label">Sexo</label>
                                <select class="form-select" id="selSexo" name="selSexo">
                                    <option selected value="vacio">Seleccione el sexo</option>                                    
                                    <c:forEach var="sexo" items="${listasexo}">
                                        <option value="<c:out value="${sexo.id}"></c:out>"> <c:out value="${sexo.nombre}"></c:out></option>                                                      
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="col-md-2">                                
                                <p>PEP</p>
                                <div class="form-check form-check-inline">
                                    <input type="radio" class="form-check-input" id="no" name="inputPEP" value="0" checked>
                                    <label for="no" class="form-check-label">No</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" class="form-check-input" id="si" name="inputPEP" value="1">
                                    <label for="si" class="form-check-label">Si</label>                                
                                </div>
                            </div>

                        </div>

                        <div class="row mt-4">

                            <div class="col-2">
                                <label for="txtCUIL" class="form-label">CUIL</label>
                                <input type="text" class="form-control" id="txtCUIL" name="txtCUIL" maxlength="11" pattern="[0-9]+">
                            </div>
                            
                            <div class="col-3">
                                <label for="txtNacimiento" class="form-label">Fecha de Nacimiento</label>
                                <input type="date" class="form-control" id="txtNacimiento" name="txtNacimiento">
                            </div>
                            
                            <div class="col-3">
                                <label for="selNac" class="form-label">Nacionalidad</label>
                                <select class="form-select" id="selNac" name="selNac" required >
                                    <option selected value="vacio">Seleccione la nacionalidad</option>
                                    <c:forEach var="nacionalidad" items="${listanac}">
                                        <option value="<c:out value="${nacionalidad.id}"></c:out>"> <c:out value="${nacionalidad.nombre}"></c:out></option>                                                      
                                    </c:forEach>
                                </select>
                            </div>

                        </div>

                        <div class="row mt-4">

                            <div class="col-md-3">
                                <label for="inputCalle" class="form-label">Calle</label>
                                <input type="text" class="form-control" id="inputCalle" name="txtCalle" maxlength="50">
                            </div>
                            <div class="col-md-1">
                                <label for="inputNro" class="form-label">Nro</label>
                                <input type="text" class="form-control" id="inputNro" name="txtNro" maxlength="5">
                            </div>
                            <div class="col-md-1">
                                <label for="inputPiso" class="form-label">Piso</label>
                                <input type="text" class="form-control" id="inputPiso" maxlength="2" name="txtPiso">
                            </div>
                            <div class="col-md-1">
                                <label for="inputDpto" class="form-label">Dpto</label>
                                <input type="text" class="form-control" id="inputDpto" maxlength="2" name="txtDpto">
                            </div>
                        </div>

                        <div class="row mt-4">

                            <div class="col-md-3">
                                <label for="inputBarrio" class="form-label">Barrio</label>
                                <input type="text" class="form-control" id="inputBarrio" maxlength="50" name="txtBarrio">
                            </div>
                            <div class="col-md-1">
                                <label for="inputMza" class="form-label">Mza</label>
                                <input type="text" class="form-control" id="inputMza" maxlength="2" name="txtMza">                                
                            </div>
                            <div class="col-md-1">
                                <label for="inputLote" class="form-label">Lote</label>
                                <input type="text" class="form-control" id="inputLote" maxlength="2" name="txtLote">
                            </div>

                        </div>

                        <div class="row mt-4">

                            <div class="col-md-3">
                                <label for="inputLocalidad" class="form-label">Localidad</label>
                                <input type="text" class="form-control" id="inputLocalidad" name="txtLocalidad">
                            </div>
                            <div class="col-md-2">
                                <label for="inputCP" class="form-label">C.P.</label>
                                <input type="text" class="form-control" id="inputCP" maxlength="8" name="txtCP">
                            </div>
                            <div class="col-md-3">
                                <label for="selProvincia" class="form-label">Provincia</label>                                
                                <select class="form-select" id="selProvincia" name="selProvincia" required >
                                    <option selected value="vacio">Seleccione la provincia</option>
                                    <c:forEach var="provincia" items="${listaprov}">
                                        <option value="<c:out value="${provincia.id}"></c:out>"> <c:out value="${provincia.nombre}"></c:out></option>                                                      
                                    </c:forEach>
                                </select>                                
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col-md-3">
                                <label for="inputTelefono" class="form-label">Teléfono</label>
                                <input type="text" class="form-control" id="inputTelefono" name="txtTelefono" maxlength="10" value="<c:out value="${nuevaSC.telefono}"></c:out>">                                
                                </div>
                                <div class="col-md-4">
                                    <label for="inputEmail" class="form-label">Email</label>
                                    <input type="text" class="form-control" id="inputEmail" name="txtEmail" maxlength="50" value="<c:out value="${nuevaSC.email}"></c:out>">
                                </div>


                            </div>

                        </div>

                        <!-- Tab laborales -->

                        <div
                            class="tab-pane fade mt-5"
                            id="laborales"
                            role="tabpanel"
                            aria-labelledby="tab-laborales">         

                            <div class="row mt-4">
                                <div class="col-md-2">
                                    <label for="inputCUIT" class="form-label">CUIT Empleador</label>
                                    <input type="text" class="form-control" minlength="11" maxlength="11" pattern="[0-9]+" id="inputCUIT" name="txtEmpleador">
                                </div>                                
                                <div class="col-md-3">
                                    <label for="inputRazonSocial" class="form-label">Razon Social / Denominación </label>
                                    <input type="text" class="form-control" id="inputRazonSocial" maxlength="50" name="txtRazonSocial">
                                </div>
                            </div>

                            <div class="row mt-4">
                                <div class="col-md-3">                                
                                    <label for="selOcupacion" class="form-label">Ocupacion</label>                                
                                    <select class="form-select" id="selOcupacion" name="selOcupacion" required>
                                        <option selected value="vacio">Seleccione la ocupación</option>
                                    <c:forEach var="ocupacion" items="${listaocup}">
                                        <option value="<c:out value="${ocupacion.id}"></c:out>"> <c:out value="${ocupacion.nombre}"></c:out></option>                                                      
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label for="inputIngresos" class="form-label">Ingresos netos</label>
                                <input type="text" class="form-control" id="inputIngresos" name="txtIngresos">
                            </div>
                            <div class="col-md-2">
                                <label for="inputFechaIngreso" class="form-label">Fecha de Ingreso</label>
                                <input type="date" class="form-control" id="inputFechaIngreso" name="txtFechaIngreso">
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col-md-3">
                                <label for="inputCalleL" class="form-label">Calle</label>
                                <input type="text" class="form-control" id="inputCalleL" maxlength="50" name="txtCalleL">
                            </div>
                            <div class="col-md-1">
                                <label for="inputNroL" class="form-label">Nro.</label>
                                <input type="text" class="form-control" id="inputNroL" maxlength="5" name="txtNroL">
                            </div>
                            <div class="col-md-1">
                                <label for="inputPisoL" class="form-label">Piso</label>
                                <input type="text" class="form-control" id="inputPisoL" maxlength="2" name="txtPisoL">
                            </div>
                            <div class="col-md-1">
                                <label for="inputDptoL" class="form-label">Dpto</label>
                                <input type="text" class="form-control" id="inputDptoL" maxlength="2" name="txtDptoL">
                            </div>
                        </div>

                        <div class="row mt-4">

                            <div class="col-md-3">
                                <label for="inputLocalidadL" class="form-label">Localidad</label>
                                <input type="text" class="form-control" id="inputLocalidadL" maxlength="50" name="txtLocalidadL">
                            </div>
                            <div class="col-md-2">
                                <label for="inputCPL" class="form-label">C.P.</label>
                                <input type="text" class="form-control" id="inputCPL" maxlength="8" name="txtCPL">
                            </div>
                            <div class="col-md-3">
                                <label for="selProvinciaL" class="form-label">Provincia</label>
                                <select class="form-select" id="selProvinciaL" name="selProvinciaL"required >
                                    <option selected value="vacio">Seleccione la provincia</option>
                                    <c:forEach var="provincia" items="${listaprov}">
                                        <option value="<c:out value="${provincia.id}"></c:out>"> <c:out value="${provincia.nombre}"></c:out></option>                                                      
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col-md-3">
                                <label for="inputTelefonoL" class="form-label">Teléfono laboral</label>
                                <input type="text" class="form-control" id="inputTelefonoL" maxlength="10" name="txtTelefonoL">                                
                            </div>
                        </div>

                    </div>
                </div>


                <!-- Botones -->

                <div class="row mb-3 mt-5">
                    <div class="col-auto">
                        <!--<label for="inputAnexos" class="form-label">Elegir archivos</label>-->
                        <button class="btn btn-danger mt-4">Anexar documentación</button>                        
                        <!--
                        <input class="form-control mt-4" type="file" name="anexos" accept="image/*,.pdf" id="inputAnexos" multiple>
                        -->

                    </div>
                    <div class="col-auto">
                        <button class="btn btn-danger mt-4" type="submit" title="Registrar sin presentar">Grabar</button>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-danger mt-4" onclick="confirmarSolicitud()" title="Confirmar como presentada">Confirmar</button>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-danger mt-4">Imprimir</button>
                    </div>
                    <div class="col-auto">
                        <a class="btn btn-secondary mt-4" href="ControladorSolicitudes?accion=Solicitudes&inicio=0" role="button">Cancelar</a>                        
                    </div>
                </div>

            </form>
        </div>

        <script>

            function confirmarSolicitud() {
                var formulario = document.getElementById("formSolicitud");
                var presentar = document.getElementById("presentar");
                presentar.value= "si";
                formulario.submit();
            }

            window.onload = manejadorEventos();

            function manejadorEventos() {
                var elemento = document.getElementById("inputCUIT");
                elemento.addEventListener("blur", buscarEmpleador);
            }


            function buscarEmpleador() {
                // alert("evento disparado");
                var CUIT = document.getElementById("inputCUIT").value;
                var empleadorNombre = document.getElementById("inputRazonSocial");

                var http = new XMLHttpRequest();
                url = 'ControladorSolicitudes?accion=buscarEmpleador&cuit=' + CUIT;
                http.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        console.log(${empleadorNombre});
                        empleadorNombre.value = this.responseText;
                    }
                };
                http.open('get', url);
                http.send();
            }

        </script>


    </body>

</html>