package com.example.ferreteria.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instancia;
    private Connection connection;

    private final String URL = "jdbc:postgresql://localhost:5432/ferreteria_db";
    private final String usuario= "postgres";
    private final String contrasenia = "123456";

    private Conexion() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, usuario, contrasenia);
            System.out.println("Conexion a PostgreSQL establecida con exito");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
    public static Connection getConexion() {
        try {
            if (instancia == null || instancia.connection.isClosed()) {
                instancia = new Conexion();
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el estado de la conexion: " + e.getMessage());
        }
        return instancia.connection;
    }

    public static void cerrarConexion() {
        if (instancia != null && instancia.connection != null) {
            try {
                instancia.connection.close();
                System.out.println("Conexion a PostgreSQL cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
