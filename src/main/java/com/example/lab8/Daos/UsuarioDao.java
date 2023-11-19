package com.example.lab8.Daos;

import com.example.lab8.Beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao extends DaoBase{

    private void setUsuarioParams(PreparedStatement pstmt, Usuario usuario) throws SQLException {
        pstmt.setString(1, usuario.getNombre());
        pstmt.setInt(2, usuario.getEdad());
        pstmt.setString(3, usuario.getCorreo());
        pstmt.setString(4, usuario.getUsername());


    }


    private void fetchUsuarioData(Usuario usuario, ResultSet rs) throws SQLException {
        usuario.setUsuarioId(rs.getInt(1));
        usuario.setNombre(rs.getString(2));
        usuario.setEdad(rs.getInt(3));
        usuario.setCorreo(rs.getString(4));
        usuario.setUsername(rs.getString(5));

    }

    public boolean validarUsuarioPassword(String username, String password){

        String sql = "SELECT * FROM usuario_credenciales where username = ? and password = ?";

        boolean exito = false;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }

    public boolean validarUsuarioPasswordHashed(String username, String password){

        String sql = "SELECT * FROM usuario_credenciales where username = ? and password_hashed = sha2(?,256)";

        boolean exito = false;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }

    public Usuario obtenerUsuario(String username) {

        Usuario usuario = null;

        /*corregir select*/
        String sql = "SELECT * FROM usuario u \n"
                + "WHERE u.username = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    usuario = new Usuario();
                    fetchUsuarioData(usuario, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuario;
    }

}





