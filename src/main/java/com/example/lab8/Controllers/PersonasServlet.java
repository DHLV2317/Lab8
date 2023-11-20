package com.example.lab8.Controllers;

import com.example.lab8.Beans.Personas;
import com.example.lab8.Beans.Profesion;
import com.example.lab8.Beans.Usuario;
import com.example.lab8.Daos.CivilizacionDao;
import com.example.lab8.Daos.PersonaDao;
import com.example.lab8.Daos.ProfesionDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
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

                request.setAttribute("listaPersonas", personaDao.listarPersonas());
                view = request.getRequestDispatcher("personas/GestionPersonas.jsp");
                view.forward(request, response);
                break;
            case "agregar":

                view = request.getRequestDispatcher("personas/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                HttpSession httpSession = request.getSession();
                Usuario usuario = (Usuario) httpSession.getAttribute("usuarioLogueado");

                if(usuario != null && usuario.getUsuarioId() > 0) {
                    if (request.getParameter("id") != null) {
                        String idpersonasString = request.getParameter("id");
                        int idpersonas = 0;
                        try {
                            idpersonas = Integer.parseInt(idpersonasString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect("PersonasServlet");
                        }
                        Personas per = personaDao.obtenerPersona(idpersonas);

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
                    String idpersonasString = request.getParameter("id");
                    int idpersonas = 0;
                    try {
                        idpersonas = Integer.parseInt(idpersonasString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("PersonasServlet?err=Error al exiliar a la persona");
                    }

                    Personas per = personaDao.obtenerPersona(idpersonas);

                    if (per != null) {
                        try {
                           personaDao.borrarPersona(idpersonas);
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
        ProfesionDao profesionDao = new ProfesionDao();
        CivilizacionDao civilizacionDao = new CivilizacionDao();

        switch (action) {
            case "crear":
                Personas personaCreada = new Personas();
                personaCreada.setNombre(request.getParameter("nombre"));
                personaCreada.setGenero(request.getParameter("apellido"));
                personaCreada.setProfesion(profesionDao.obtenerProfesion("4"));
                personaCreada.setAlimentacion(Integer.parseInt(request.getParameter("50")));
                personaCreada.setMoral(Integer.parseInt(request.getParameter("50")));
                personaCreada.setTiempo(request.getParameter("tiempo"));
                personaCreada.setProduccion(request.getParameter("produccion"));
                personaCreada.setCivilizacion(civilizacionDao.obtenerCivilizacion("3")); // se le asigna el estado de pendiente (luego será revisado por el delegado general)

                personaDao.crearPersona(personaCreada); //crear el alumno en la base de datos

                response.sendRedirect(request.getContextPath() + "/PersonasServlet");

                break;

        }
    }
}

