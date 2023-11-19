package com.example.lab8.Daos;

import com.example.lab8.Beans.Usuario;
import com.example.lab8.Beans.UsuarioCredenciales;
import com.example.lab8.Daos.DaoBase;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao extends DaoBase {

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

    public boolean validarUsuarioPassword(String username, String password) {

        String sql = "SELECT * FROM usuario_credenciales where username = ? and password = ?";

        boolean exito = false;

        try (Connection connection = getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }

    public boolean validarUsuarioPasswordHashed(String username, String password) {

        String sql = "SELECT * FROM usuario_credenciales where username = ? and password_hashed = sha2(?,256)";

        boolean exito = false;

        try (Connection connection = getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
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

        String sql = "SELECT * FROM usuario WHERE username = ? ORDER BY id_usuario DESC LIMIT 1";

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

    public void guardarUsuario(Usuario usuario) {

        String sql = "INSERT INTO `rootsgame`.`usuario` (`nombre`, `edad`, `correo`, `username`)\n" +
                "VALUES (?,?,?,?);";

        try (Connection conn = this.getConection();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

             pstmt.setString(1, usuario.getNombre());
             pstmt.setInt(2,usuario.getEdad());
             pstmt.setString(3,usuario.getCorreo());
             pstmt.setString(4,usuario.getUsername());

             pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void guardarContrasena(String contrasena, String username, Usuario usuario3){


        String sql = "INSERT INTO usuario_credenciales(`id_usuario`, `username`, `password_hashed`)\n" +
                "VALUES (?,?,SHA2(?,256));";

        try (Connection conn = this.getConection();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuario3.getUsuarioId());
            pstmt.setString(2,username);
            pstmt.setString(3,contrasena);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public boolean isUsernameUnique(String username) {
        String sql = "SELECT 1 FROM usuario WHERE username = ? LIMIT 1";

        try (Connection connection = getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                return !rs.next(); // Return true if there is no matching username
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}










