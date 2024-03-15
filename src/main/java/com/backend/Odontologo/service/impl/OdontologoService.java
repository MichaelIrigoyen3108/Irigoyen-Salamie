
package com.backend.Odontologo.service.impl;


import com.backend.Odontologo.entity.Odontologo;
import com.backend.Odontologo.repository.OdontologoRepository;
import com.backend.Odontologo.repository.PacienteRepository;
import com.backend.Odontologo.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
e
import org.slf4j.LoggerFactory;

import java.util.List;

public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;


    private ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return null;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        return null;
    }



}



