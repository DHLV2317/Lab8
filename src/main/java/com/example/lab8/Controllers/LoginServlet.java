package com.example.lab8.Controllers;

import com.example.lab8.Beans.Usuario;
import com.example.lab8.Beans.UsuarioCredenciales;
import com.example.lab8.Daos.UsuarioDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/LoginServlet", ""})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Usuario usuarioLogueado = (Usuario) httpSession.getAttribute("usuarioLogueado");

        if(usuarioLogueado != null && usuarioLogueado.getUsuarioId() > 0){

            if(request.getParameter("a") != null){//logout
                httpSession.invalidate();
            }
            response.sendRedirect(request.getContextPath());
        }else{
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "sesion" : request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username: " + username + " | password: " + password);
        UsuarioDao usuarioDao = new UsuarioDao();
        switch (action) {
            case "sesion":
                if(usuarioDao.validarUsuarioPasswordHashed(username,password)){
                    System.out.println("usuario y password válidos");
                    Usuario usuario = usuarioDao.obtenerUsuario(username);
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("usuarioLogueado",usuario);
                    response.sendRedirect(request.getContextPath() + "/PersonasServlet");
                }else{
                    System.out.println("usuario o password incorrectos");
                    request.setAttribute("err","Usuario o password incorrectos");
                    request.getRequestDispatcher("Login.jsp").forward(request,response);
                }

                break;
            case "registrar":
                try {
                    Usuario usuario = parseUsuario(request);
                    String username2 = request.getParameter("usuarioNuevo");
                    String contra = request.getParameter("contra");

                    if (!usuarioDao.isUsernameUnique(username2)) {
                        throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
                    }

                    if (!isPasswordValid(contra)) {
                        throw new IllegalArgumentException("La contraseña debe tener al menos una mayúscula, un número y un carácter especial.");
                    }

                    usuarioDao.guardarUsuario(usuario);
                    Usuario usuario3 = usuarioDao.obtenerUsuario(username2);
                    usuarioDao.guardarContrasena(contra, username2, usuario3);

                    response.sendRedirect(request.getContextPath() + "/LoginServlet");

                } catch (IllegalArgumentException e) {
                    // Handle validation errors
                    request.setAttribute("err", e.getMessage());
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
                break;

        }
    }


    private boolean isPasswordValid(String password) {
        // Password should have at least one uppercase letter, one digit, and one special character
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z\\d@#$%^&+=!]).{8,}$";
        return password.matches(passwordRegex);
    }

    public Usuario parseUsuario(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("usuarioNuevo");
        String correo = request.getParameter("email");
        String edadStr = request.getParameter("edad");

        try {
            int edad = Integer.parseInt(edadStr);


            if (!Character.isLetter(nombre.charAt(0)) || !Character.isLetter(username.charAt(0))) {
                throw new IllegalArgumentException("El nombre y el usuario deben empezar con letras.");
            }

            if (edad <= 12) {
                throw new IllegalArgumentException("El usuario debe ser mayor de 12 años.");
            }

            usuario.setNombre(nombre);
            usuario.setUsername(username);
            usuario.setCorreo(correo);
            usuario.setEdad(edad);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La edad debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return usuario;
    }
























}

