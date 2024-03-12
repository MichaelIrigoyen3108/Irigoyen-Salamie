package com.backend.Odontologo.service;

import com.backend.Odontologo.entity.Odontologo;

import java.util.List;

public class IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(int id);
}
