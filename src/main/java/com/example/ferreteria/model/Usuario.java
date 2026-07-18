package com.example.ferreteria.model;

public class Usuario extends EntidadBase {
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;

    public Usuario(){
    }

    public Usuario(int id, String nombre, String correo, String contrasena, String rol) {
        super(id);
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}
