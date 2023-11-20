<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="css/styleLogin.css">
    <title>Inicio Sesión</title>
    <!-- icono de la página -->
    <link rel="icon" href="images/icono.png" />
</head>
<body>

<div class="container" id="container">
        <div class="form-container sign-up">
            <form id="registroForm" method="post" action="<%=request.getContextPath()%>/LoginServlet?action=registrar">
                <h1>Nueva cuenta</h1>
                <span>Ingrese sus datos personales</span>
                <input type="text" placeholder="Nombre" name="nombre">
                <input type="text" placeholder="Edad" name="edad">
                <input type="email" placeholder="Email" name="email">
                <input type="text" placeholder="Usuario" name="usuarioNuevo">
                <input type="password" placeholder="Contraseña" name="contra">
                <button type="submit">Registrar</button>
            </form>
        </div>
        <div class="form-container sign-in">
            <form id="loginForm" method="post" action="<%=request.getContextPath()%>/LoginServlet">
                <h1>Inicia Sesión</h1>
                <span>Ingresa tu usuario y contraseña</span>
                <% if (request.getAttribute("err") != null) { %>
                <div class="alert alert-danger" role="alert"><%=request.getAttribute("err")%></div>
                <% } %>
                <input type="text" placeholder="Usuario" name="username">
                <input type="password" placeholder="Contraseña" name="password">
                <button type="submit">Ingresar</button>
            </form>
        </div>
    <div class="toggle-container">
        <div class="toggle">
            <div class="toggle-panel toggle-left">
                <h1>¡Bienvenido de nuevo!</h1>
                <p>¿Ya tienes una cuenta? Inicia sesión para entrar al mundo de aventura</p>
                <button class="hidden" id="login">Ingresar</button>
            </div>
            <div class="toggle-panel toggle-right">
                <h1>Bienvenido</h1>
                <p style="text-align:justify">¿Te imaginas cómo sería tener tu propia civilización, dirigirla y dominar el mundo?     </p>
                <p style="margin-top: 0px">Hazlo realidad, organiza la población, administra los recursos y batalla por el dominio del mundo. Sé el cambio que quieres ver en el mundo. Tu visión, tu pasión y tu acción son las claves para crear una civilización próspera y fuerte.</p>
                <button class="hidden" id="register">Regístrate</button>
            </div>
        </div>
    </div>
</div>

<script src="js/jsLogin.js"></script>
</body>
</html>
