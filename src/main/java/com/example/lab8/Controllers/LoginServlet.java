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
                Usuario usuario = parseUsuario(request);
                String username2 = request.getParameter("usuarioNuevo");
                String contra = request.getParameter("contra");
                usuarioDao.guardarUsuario(usuario);
                Usuario usuario3 =usuarioDao.obtenerUsuario(username2);
                usuarioDao.guardarContrasena(contra,username2,usuario3);
                response.sendRedirect(request.getContextPath()+ "/LoginServlet");

        }
    }

    public Usuario parseUsuario(HttpServletRequest request) {

        Usuario usuario = new Usuario();


        String nombre = request.getParameter("nombre");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String correo = request.getParameter("email");
        String username = request.getParameter("usuarioNuevo");

        try {

            usuario.setNombre(nombre);
            usuario.setEdad(edad);
            usuario.setCorreo(correo);
            usuario.setUsername(username);

        } catch (NumberFormatException e) {

        }
        return usuario;
    }























}

