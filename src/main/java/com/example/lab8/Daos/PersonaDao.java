package com.example.lab8.Daos;

import com.example.lab8.Beans.Personas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonaDao extends DaoBase{
    public Personas obtenerPersona(int idpersonas){

        Personas p = new Personas();
        ProfesionDao profesionDao = new ProfesionDao();
        CivilizacionDao civilizacionDao = new CivilizacionDao();

        String sql = "select * from personas p where p.idpersonas = ?;";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,idpersonas);

            try(ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    p.setIdpersonas(rs.getInt("p.idPersona"));
                    p.setNombre(rs.getString("p.nombre"));
                    p.setGenero(rs.getString("p.genero"));
                    p.setAlimentacion(rs.getInt("p.alimentacion"));
                    p.setMoral(rs.getInt("p.moral"));
                    p.setTiempo(rs.getString("p.tiempo"));
                    p.setFuerza(rs.getString("p.fuerza"));
                    p.setProduccion(rs.getString("a.produccion"));
                    p.setProfesion(profesionDao.obtenerProfesion(rs.getString("p.Profesion_idProfesion")));
                    p.setCivilizacion(civilizacionDao.obtenerCivilizacion(rs.getString("p.civilizacion_idCivilizacion")));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
    public void borrarPersona(int idPersona) throws SQLException {



        String sql = "delete from personas where idpersonas = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,idPersona);
            pstmt.executeUpdate();

        }
    }
    public ArrayList<Personas> listarPersonas(){

        ArrayList<Personas> lista = new ArrayList<>();
        CivilizacionDao cDao = new CivilizacionDao();
        ProfesionDao prDao = new ProfesionDao();


        String sql = "select * from personas";


        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Personas personas = new Personas();
                    personas.setCivilizacion(cDao.obtenerCivilizacion(rs.getString("Civilizacion_idCivilizacion")));
                    personas.setIdpersonas(rs.getInt("idpersonas"));
                    personas.setNombre(rs.getString("nombre"));
                    personas.setGenero(rs.getString("genero"));
                    personas.setAlimentacion(rs.getInt("alimentacion"));
                    personas.setMoral(rs.getInt("moral"));
                    personas.setTiempo(rs.getString("tiempo"));
                    personas.setFuerza(rs.getString("fuerza"));
                    personas.setProduccion(rs.getString("produccion"));
                    personas.setProfesion(prDao.obtenerProfesion("profesion"));

                    lista.add(personas);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public void crearPersona(Personas persona){

        String sql = "insert into personas (nombre, genero, profesion_idprofesion, alimentacion, moral, tiempo, fuerza, produccion, civilizacion_idcivilizacion) values (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,persona.getNombre());
            pstmt.setString(2,persona.getGenero());
            pstmt.setInt(3,persona.getProfesion().getProfesionId());
            pstmt.setInt(4,persona.getAlimentacion());
            pstmt.setInt(5,persona.getMoral());
            pstmt.setString(6,persona.getTiempo());
            pstmt.setString(7,persona.getProduccion());
            pstmt.setInt(8,persona.getCivilizacion().getCivilizacionId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
