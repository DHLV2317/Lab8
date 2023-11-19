package com.example.lab8.Daos;

import com.example.lab8.Beans.Civilizacion;
import com.example.lab8.Beans.EstadoCivilizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

public class EstadoCivilizacionDao extends DaoBase{
    public EstadoCivilizacion obtenerEstadoCivilizacion(String idestadoCivilizacion){

        EstadoCivilizacion eC = new EstadoCivilizacion();

        String sql = "select * from estadocivilizacion ec where ec.idestadoCivilizacion = ?;";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,idestadoCivilizacion);

            try(ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    eC.setEstadoCivilizacionId(rs.getInt("eC.idestadoCivilizacion"));;
                    eC.setDescripcion(rs.getString("eC.descripcion"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eC;
    }
}
