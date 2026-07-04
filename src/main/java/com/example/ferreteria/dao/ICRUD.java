package com.example.ferreteria.dao;

import com.example.ferreteria.model.Producto;

import java.util.List;

public interface ICRUD {
    boolean registrar(Producto p) throws Exception;
    boolean modificar(Producto p) throws Exception;
    boolean eliminar(Producto p) throws Exception;
    List<Producto> listarTodo() throws Exception;
}
