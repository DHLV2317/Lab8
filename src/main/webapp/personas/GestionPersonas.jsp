<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab8.Beans.Usuario" %>
<%@ page import="com.example.lab8.Beans.Personas" %>
<jsp:useBean id="listaPersonas" type="java.util.ArrayList<com.example.lab8.Beans.Personas>" scope="request"/>
<jsp:useBean id="usuarioLogueado" class="com.example.lab8.Beans.Usuario" type="Usuario" scope="session" />
<html>
<head>
    <title>Gestión Personas</title>
    <!-- icono de la página -->
    <link rel="icon" href="../images/icono.png" />
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="job"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Personas de la civilización</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/PersonasServlet?action=agregar" class="btn btn-succes">Crear
                nueva persona</a>
        </div>
    </div>
    <!-- Revisar esta parte -->
    <% if (request.getParameter("msg") != null) {%>
    <div class="alert alert-success" role="alert"><%=request.getParameter("msg")%>
    </div>
    <% } %>
    <% if (request.getParameter("err") != null) {%>
    <div class="alert alert-danger" role="alert"><%=request.getParameter("err")%>
    </div>
    <% } %>

    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Género</th>
            <th>Consumo alimento</th>
            <th>Moral</th>
            <th>Tiempo en colonia</th>
            <th>Fuerza</th>
            <th>Producción</th>
            <% if(usuarioLogueado != null && usuarioLogueado.getUsuarioId() > 0) {%>
            <th></th>
            <th></th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Personas p : listaPersonas) {
        %>
        <tr>
            <td><%= i%>
            </td>
            <td><%= p.getNombre()%>
            </td>
            <td><%= p.getGenero()%>
            </td>
            <td><%= p.getAlimentacion()%>
            </td>
            <td><%= p.getMoral()%>
            </td>
            <td><%= p.getTiempo()%>
            </td>
            <td><%= p.getFuerza()%>
            </td>
            <td><%= p.getProduccion()%>
            </td>
            <% if(usuarioLogueado != null && usuarioLogueado.getUsuarioId() > 0) {%>
            <td>
                <a href="<%=request.getContextPath()%>/PersonasServlet?action=editar&id=<%= p.getPersonaId()%>"
                   type="button" class="btn btn-primary">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a onclick="return confirm('¿Estas seguro de borrar?');"
                   href="<%=request.getContextPath()%>/PersonasServlet?action=editar&id=<%= p.getPersonaId()%>"
                   type="button" class="btn btn-danger">
                    <i class="bi bi-trash"></i>
                </a>
            </td>
            <% } %>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>

</div>

</body>
</html>
