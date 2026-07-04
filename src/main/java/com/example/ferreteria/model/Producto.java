package com.example.ferreteria.model;

public class Producto {
    private int id;
    private String nombreProducto;
    private int stock;
    private double precioVenta;

    public Producto(){

    }

    public Producto(int id, String nombreProducto, int stock, double precioVenta) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precioVenta = precioVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}
