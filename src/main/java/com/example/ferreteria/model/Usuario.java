package com.example.ferreteria.model;

public class Usuario extends Persona{
    private String correo;
    private String contrania;
    private String rol;

    public Usuario(){
        super(); //llama al constructor persona
    }

    public Usuario(int id, String nombre, String telefono, String correo, String contrania, String rol) {
        super(id, nombre, telefono);
        this.correo = correo;
        this.contrania = contrania;
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrania() {
        return contrania;
    }

    public void setContrania(String contrania) {
        this.contrania = contrania;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
