package com.example.ferreteria.dao;

import com.example.ferreteria.db.Conexion;
import com.example.ferreteria.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    public Usuario autenticar(String correo, String contrasena) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("contrasena"),
                            rs.getString("rol")
                    );
                }
            }
        }
        return null;
    }
}
