package com.backend.Odontologo.dao;


import java.util.List;

public interface IDao<T> {
    T registrar (T t);
    List<T> listarTodos();
    T BuscarPorId(int id);




}
