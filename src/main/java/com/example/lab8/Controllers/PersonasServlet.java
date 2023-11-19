package com.example.lab8.Controllers;

import com.example.lab8.Beans.Personas;
import com.example.lab8.Beans.Usuario;
import com.example.lab8.Daos.PersonaDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "PersonasServlet", value = "/PersonasServlet")
public class PersonasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        PersonaDao personaDao = new PersonaDao();

        switch (action) {
            case "lista":
                /* Tener el Dao listarPersonas */
                request.setAttribute("listaPersonas", personaDao.listarPersonas());
                view = request.getRequestDispatcher("personas/GestionPersonas.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                /*Revisar jeje*/


                view = request.getRequestDispatcher("personas/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                HttpSession httpSession = request.getSession();
                Usuario usuario = (Usuario) httpSession.getAttribute("usuarioLogueado");

                if(usuario != null && usuario.getUsuarioId() > 0) {
                    if (request.getParameter("id") != null) {
                        String personaIdString = request.getParameter("id");
                        int personaId = 0;
                        try {
                            personaId = Integer.parseInt(personaIdString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect("PersonasServlet");
                        }

                        Personas per = personaDao.obtenerPersonas(personaId);

                        if (per != null) {
                            request.setAttribute("persona", per);

                            view = request.getRequestDispatcher("personas/formularioEditar.jsp");
                            view.forward(request, response);
                        } else {
                            response.sendRedirect("PersonasServlet");
                        }

                    } else {
                        response.sendRedirect("PersonasServlet");
                    }
                } else {
                    response.sendRedirect("PersonasServlet");
                }
                break;

            case "borrar":
                if (request.getParameter("id") != null) {
                    String personaIdString = request.getParameter("id");
                    int personaId = 0;
                    try {
                        personaId = Integer.parseInt(personaIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("PersonasServlet?err=Error al exiliar a la persona");
                    }

                    /* Crear el método obtenerPersonas */
                    Personas per = personaDao.obtenerPersona(personaId);

                    if (per != null) {
                        try {
                            /* Hacer el Dao */
                            personaDao.borrarPersona(personaId);
                            response.sendRedirect("PersonasServlet?msg=Persona exiliada exitosamente");
                        } catch (SQLException e) {
                            response.sendRedirect("PersonasServlet?err=Error al exiliar a la persona");
                        }
                    }
                } else {
                    response.sendRedirect("PersonasServlet?err=Error al borrar el empleado");
                }
                break;
            default:
                response.sendRedirect("PersonasServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        PersonaDao personaDao = new PersonaDao();

        switch (action) {
            case "guardar":
                Personas p = new Personas();

                break;

        }
    }
}

