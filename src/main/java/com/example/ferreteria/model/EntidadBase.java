package com.example.ferreteria.model;

public class EntidadBase {
    protected int id;

    protected EntidadBase() {
    }

    protected EntidadBase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
