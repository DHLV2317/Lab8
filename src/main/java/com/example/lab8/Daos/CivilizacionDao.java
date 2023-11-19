package com.example.lab8.Daos;

import com.example.lab8.Beans.Civilizacion;
import com.example.lab8.Beans.Personas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CivilizacionDao extends DaoBase{
    public Civilizacion obtenerCivilizacion(String idCivilizacion){

        Civilizacion c = new Civilizacion();
        UsuarioDao usuarioDao = new UsuarioDao();
        EstadoCivilizacionDao estadoCivilizacionDao = new EstadoCivilizacionDao();

        String sql = "select * from civilizacion c where c.idcivilizacion = ?;";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,idCivilizacion);

            try(ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    c.setCivilizacionId(rs.getInt("c.idCivilizacion"));
                    c.setEstado(rs.getString("c.estado"));
                    c.setGuerraGanada(rs.getString("c.guerraGanada"));
                    c.setGuerraPerdida(rs.getString("c.guerraPerdida"));
                    c.setUsuario(usuarioDao.obtenerUsuario(rs.getString("p.Usuario_idUsuario")));
                    c.setEstadoCivilizacion(estadoCivilizacionDao.obtenerEstadoCivilizacion(rs.getString("p.estadoCivilizacion_idEstadoCivilizacion")));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
}
