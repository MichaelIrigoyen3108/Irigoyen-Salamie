
package com.backend.Odontologo.service.impl;


import com.backend.Odontologo.dto.entrada.OdontologoEntradaDto;
import com.backend.Odontologo.dto.entrada.PacienteEntradaDto;
import com.backend.Odontologo.dto.salida.OdontologoSalidaDto;
import com.backend.Odontologo.dto.salida.PacienteSalidaDto;
import com.backend.Odontologo.entity.Odontologo;
import com.backend.Odontologo.entity.Paciente;
import com.backend.Odontologo.repository.OdontologoRepository;
import com.backend.Odontologo.repository.PacienteRepository;
import com.backend.Odontologo.service.IOdontologoService;
import com.backend.Odontologo.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;
    private ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        LOGGER.info("OdontologoEntradaDto: {}", JsonPrinter.toString(odontologo));
        Odontologo OdontologoEntidad = modelMapper.map(odontologo, Odontologo.class);
        Odontologo odontologoEntidadConId = odontologoRepository.save(OdontologoEntidad);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoEntidadConId, OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: {}",  JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologo() {
        List<OdontologoSalidaDto> odontologosSalidaDto =  odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();

        LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologosSalidaDto));
        return odontologosSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if(odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else {
            LOGGER.error("El id no se encuentra registrado en la base de datos");
        }

        return odontologoEncontrado;
    }
    @Override
    public void eliminarOdontologoPorId(Long id) {
        odontologoRepository.deleteById(id);
    }

    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));

        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }


}



