package com.example.ferreteria.model;

/**
 * Guarda el usuario que inicio sesion para que otras pantallas
 * (Dashboard, Ventas, Reportes) sepan quien esta operando y con que rol.
 */
public class Sesion {
    private static Usuario usuarioActual;

    private Sesion() { }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }
}
