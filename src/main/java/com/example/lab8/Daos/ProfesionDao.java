package com.example.lab8.Daos;

import com.example.lab8.Beans.EstadoCivilizacion;
import com.example.lab8.Beans.Profesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfesionDao extends DaoBase{
    public Profesion obtenerProfesion(String idProfesion){

        Profesion pr = new Profesion();

        String sql = "select * from profesion pr where pr.idprofesion = ?;";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,idProfesion);

            try(ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    pr.setProfesionId(rs.getInt("pr.idprofesion"));;
                    pr.setDescripcion(rs.getString("pr.descripcion"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pr;
    }
}
