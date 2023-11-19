<%@ page import="com.example.lab8.Beans.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String currentPage = request.getParameter("currentPage"); %>
<jsp:useBean id="usuarioLogueado" scope="session" type="Usuario" class="com.example.lab8.Beans.Usuario" />

<nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>">Roots Game</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link <%=currentPage.equals("per") ? "active" : ""%>"
                       href="<%=request.getContextPath()%>/PersonasServlet">
                       Personas
                    </a>
                </li>
                <!-- Luego colocar las demás opciones -->
                <li class="nav-item">
                    <% if(usuarioLogueado.getUsuarioId() != 0){ %>
                    <a class="nav-link disabled"><%=usuarioLogueado.getUsername()%></a>
                    <% }%>

                </li>
                <% if(usuarioLogueado.getUsuarioId() != 0){ %>
                <li class="nav-item">
                    <a class="nav-link" style="text-decoration: underline;color: #0d6efd;" href="<%=request.getContextPath()%>/LoginServlet?a=lo">(Cerrar sesión)</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
