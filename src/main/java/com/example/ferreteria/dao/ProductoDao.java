package com.example.ferreteria.dao;

import com.example.ferreteria.db.Conexion;
import com.example.ferreteria.model.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao implements ICRUD {

    @Override
    public boolean registrar(Producto p) throws Exception {
        String sql = "INSERT INTO productos_ferreteria (codigo_barra, nombre, marca, precio, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, p.getCodigoBarra());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getMarca());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean modificar(Producto p) throws Exception {
        String sql = "UPDATE productos_ferreteria SET codigo_barra=?, nombre=?, marca=?, precio=?, stock=? WHERE id=?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setString(1, p.getCodigoBarra());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getMarca());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(Producto p) throws Exception {
        String sqlVentas = "DELETE FROM ventas_ferreteria WHERE producto_id = ?";
        try (PreparedStatement psVentas =
                     Conexion.getConexion().prepareStatement(sqlVentas)) {
            psVentas.setInt(1, p.getId());
            psVentas.executeUpdate();
        }
        String sqlProducto = "DELETE FROM productos_ferreteria WHERE id = ?";
        try (PreparedStatement psProducto =
                     Conexion.getConexion().prepareStatement(sqlProducto)) {
            psProducto.setInt(1, p.getId());
            return psProducto.executeUpdate() > 0;
        }
    }

    @Override
    public List<Producto> listarTodo() throws Exception {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos_ferreteria WHERE activo = TRUE ORDER BY id";
        try (Statement st = Conexion.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("codigo_barra"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }
        }
        return lista;
    }

    public boolean actualizarStock(int idProducto, int nuevoStock) throws Exception {
        String sql = "UPDATE productos_ferreteria SET stock=? WHERE id=?";
        try (PreparedStatement ps = Conexion.getConexion().prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setInt(2, idProducto);
            return ps.executeUpdate() > 0;
        }
    }
}
