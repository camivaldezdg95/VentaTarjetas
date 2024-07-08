function cambiarInicio(nuevoinicio) {
    var formulario = document.getElementById("filtros");
    console.log("vieja acción: ");
    console.log(formulario.action);
    formulario.action = "Controlador?accion=Entrevistas&inicio=" + nuevoinicio;
    console.log("nueva acción: ");
    console.log(formulario.action);
    formulario.submit;                        
}
