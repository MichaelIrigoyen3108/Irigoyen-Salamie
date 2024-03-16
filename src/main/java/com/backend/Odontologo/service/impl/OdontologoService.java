
package com.backend.Odontologo.service.impl;


import com.backend.Odontologo.entity.Odontologo;
import com.backend.Odontologo.repository.OdontologoRepository;
import com.backend.Odontologo.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class OdontologoService implements IOdontologoService {
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

    public Odontologo buscarOdontologoPorId(Long id) {
        return odontologoRepository.findById(id).orElse(null);
    }

    public List<Odontologo> listarOdontologo() {
        return odontologoRepository.findAll();
    }




}



