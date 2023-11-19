<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="persona" type="com.example.lab8.Beans.Personas" scope="request"/>
<html>
<head>
    <title>Editar persona</title>
    <!-- icono de la página -->
    <link rel="icon" href="../images/icono.png" />
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="emp"/>
    </jsp:include>
    <div class="row mb-4">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Editar persona</h1>
            <hr>
            <form method="POST" action="EmployeeServlet">
                <input type="hidden" name="employee_id" value="<%= persona.getIdpersonas()%>"/>
                <div class="mb-3">
                    <label class="form-label" for="first_name">Nombre</label>
                    <input type="text" class="form-control form-control-sm" id="first_name" name="first_name"
                           value="<%= persona.getNombre() == null ? "" : persona.getNombre()%>">
                </div>

                <a href="<%= request.getContextPath()%>/PersonasServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-succes"/>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>
