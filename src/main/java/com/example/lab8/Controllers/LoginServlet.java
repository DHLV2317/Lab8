package com.example.lab8.Controllers;

import com.example.lab8.Beans.Usuario;
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username: " + username + " | password: " + password);
        UsuarioDao usuarioDao = new UsuarioDao();

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
    }
}

