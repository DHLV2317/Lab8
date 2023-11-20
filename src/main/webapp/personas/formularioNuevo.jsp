<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nueva persona</title>
    <!-- icono de la página -->
    <link rel="icon" href="images/icono.png" />
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="per"/>
    </jsp:include>

    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Nueva persona</h1>
            <hr>
            <form method="POST" action="PersonasServlet">
                <div class="mb-3">
                    <label class="form-label" for="nombre">Nombre</label>
                    <input type="text" class="form-control form-control-sm" id="nombre" name="nombre">
                </div>

                <div class="mb-3">
                    <label class="form-label" for="genero">Género</label>
                    <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="genero" id="genero">
                        <option selected>-- Selecciona un género --</option>
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                        <option value="O">Otro</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label" for="profesion">Profesión</label>
                    <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="profesion" id="profesion">
                        <option selected>-- Selecciona una profesión --</option>
                        <option value="granjero">Granjero</option>
                        <option value="constructor">Constructor</option>
                        <option value="soldado">Soldado</option>
                        <option value="ninguna">Ninguna</option>
                    </select>
                </div>

                <a href="<%= request.getContextPath()%>/PersonasServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-success"/>
            </form>
        </div>
        <div class="col"></div>
    </div>

</div>
</body>
</html>
