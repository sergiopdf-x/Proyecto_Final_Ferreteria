package com.example.ferreteria.dao;

import com.example.ferreteria.db.Conexion;
import com.example.ferreteria.model.Venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VentaDao {

    public boolean registrar(Venta v) throws Exception {
        String sql = "INSERT INTO ventas_ferreteria (usuario_id, producto_id, cantidad, total) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setInt(1, v.getUsuarioId());
            ps.setInt(2, v.getProductoId());
            ps.setInt(3, v.getCantidad());
            ps.setDouble(4, v.getTotal());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Venta> listarTodo() throws Exception {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas_ferreteria ORDER BY fecha_venta DESC";
        try (Statement st = Conexion.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Venta(
                        rs.getInt("id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad"),
                        rs.getDouble("total"),
                        rs.getTimestamp("fecha_venta").toLocalDateTime()
                ));
            }
        }
        return lista;
    }

    public double totalVendido() throws Exception {
        String sql = "SELECT COALESCE(SUM(total),0) AS total FROM ventas_ferreteria";
        try (Statement st = Conexion.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble("total");
        }
        return 0;
    }
}
