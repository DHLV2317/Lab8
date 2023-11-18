<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="css/styleLogin.css">
    <title>Inicio Sesión</title>
    <!-- icono de la página -->
    <link rel="icon" href="images/icono.png" />
</head>
<body>

<div class="container" id="container">
    <div class="form-container sign-up">
        <form>
            <h1>Crea una cuenta</h1>
            <span>Ingrese sus datos personales</span>
            <input type="text" placeholder="Nombre">
            <input type="text" placeholder="Edad">
            <input type="email" placeholder="Email">
            <input type="text" placeholder="Usuario">
            <input type="password" placeholder="Contraseña">
            <button>Registrar</button>
        </form>
    </div>
    <div class="form-container sign-in">
        <form method="post" action="<%=request.getContextPath()%>/LoginServlet">
            <h1>Inicia Sesión</h1>
            <span>Ingresa tu usuario y contraseña</span>
            <% if (request.getAttribute("err") != null) {%>
            <div class="alert alert-danger" role="alert"><%=request.getAttribute("err")%>
            </div>
            <% } %>
            <input type="text" placeholder="Usuario" name="username">
            <input type="password" placeholder="Contraseña" name="password">
            <button>Ingresar</button>
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
