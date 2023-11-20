<%@ page import="com.example.lab8.Beans.Profesion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nueva persona</title>
    <!-- icono de la página -->
    <link rel="icon" href="images/icono.png" />
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../includes/navbar.jsp">
    <jsp:param name="currentPage" value="per"/>
</jsp:include>
<div class='container'>


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
                    <select class="form-select form-select-sm"  name="genero" id="genero">
                        <option selected>-- Selecciona un género --</option>
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                        <option value="O">Otro</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label" for="profesion">Profesión</label>
                    <select class="form-select form-select-sm"  name="profesion" id="profesion">
                        <% for (Profesion profesion : listaProfesiones) {%>
                        <option value="<%=profesion.getProfesionId()%>"><%=profesion.getDescripcion()%>
                        </option>
                        <% }%>
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
