<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>        
        <title>Prospectos por estado</title>
    </head>
    <body>
        
        <table class="table table-striped table-responsive">                
                <thead>
                    <tr>
                        <th>Estado</th>
                        <th>Cantidad</th>                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="linea" items="${reporte}">
                        <tr>
                            <td> <c:out value="${linea.estado}"></c:out></td>
                            <td> <c:out value="${linea.cantidad}"></c:out></td>                                                        
                            </tr>
                    </c:forEach>
                </tbody>
            </table>   
        
    </body>
</html>
