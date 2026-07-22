package com.example.ferreteria.model;

import java.time.LocalDateTime;

public class Venta extends EntidadBase {
    private int usuarioId;
    private int productoId;
    private int cantidad;
    private double total;
    private LocalDateTime fechaVenta;
    private String clienteNombre;
    private String clienteCedula;

    public Venta(){

    }

    public Venta(int id, int usuarioId, int productoId, int cantidad, double total, LocalDateTime fechaVenta) {
        super(id);
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.total = total;
        this.fechaVenta = fechaVenta;
    }

    public Venta(int id, int usuarioId, int productoId, int cantidad, double total, LocalDateTime fechaVenta,
                 String clienteNombre, String clienteCedula) {
        super(id);
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.total = total;
        this.fechaVenta = fechaVenta;
        this.clienteNombre = clienteNombre;
        this.clienteCedula = clienteCedula;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(String clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
