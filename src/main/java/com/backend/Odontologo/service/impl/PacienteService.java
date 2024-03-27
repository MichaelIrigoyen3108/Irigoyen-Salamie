package com.backend.Odontologo.service.impl;

import com.backend.Odontologo.dto.entrada.PacienteEntradaDto;
import com.backend.Odontologo.dto.salida.DomicilioSalidaDto;
import com.backend.Odontologo.dto.salida.PacienteSalidaDto;
import com.backend.Odontologo.entity.Paciente;
import com.backend.Odontologo.repository.PacienteRepository;
import com.backend.Odontologo.service.IPacienteService;
import com.backend.Odontologo.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class  PacienteService implements IPacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;
    private ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        LOGGER.info("PacienteEntradaDto: {}", JsonPrinter.toString(paciente));
        Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
        Paciente pacienteEntidaConId = pacienteRepository.save(pacienteEntidad);
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteEntidaConId, PacienteSalidaDto.class);
        LOGGER.info("PacienteSalidaDto: {}",  JsonPrinter.toString(pacienteSalidaDto));
        return pacienteSalidaDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<PacienteSalidaDto> pacientesSalidaDto =  pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class))
                .toList();

        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientesSalidaDto));
        return pacientesSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteEncontrado = null;

        if(pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else {
            LOGGER.error("El id no se encuentra registrado en la base de datos");
        }

        return pacienteEncontrado;
    }

    @Override
    public PacienteSalidaDto actualizarPacientePorId(PacienteEntradaDto pacienteEntradaDto, Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if (pacienteOptional.isPresent()) {
            Paciente pacienteExistente = pacienteOptional.get();
            pacienteExistente.setNombre(pacienteEntradaDto.getNombre());
            pacienteExistente.setApellido(pacienteEntradaDto.getApellido());


            pacienteExistente.getDomicilio().setCalle(pacienteEntradaDto.getDomicilioEntradaDto().getCalle());
            pacienteExistente.getDomicilio().setNumero(pacienteEntradaDto.getDomicilioEntradaDto().getNumero());
            pacienteExistente.getDomicilio().setLocalidad(pacienteEntradaDto.getDomicilioEntradaDto().getLocalidad());
            pacienteExistente.getDomicilio().setProvincia(pacienteEntradaDto.getDomicilioEntradaDto().getProvincia());

            pacienteRepository.save(pacienteExistente);
            return convertirAOutputDto(pacienteExistente);
        } else {
            throw new NoSuchElementException("El paciente con el ID " + id + " no existe.");
        }
    }


    private PacienteSalidaDto convertirAOutputDto(Paciente paciente) {
        PacienteSalidaDto salidaPacienteDto = new PacienteSalidaDto();
        salidaPacienteDto.setNombre(paciente.getNombre());
        salidaPacienteDto.setApellido(paciente.getApellido());
        salidaPacienteDto.setDni(paciente.getDni());
        salidaPacienteDto.setFechaIngreso(paciente.getFechaIngreso());


        DomicilioSalidaDto domicilioSalidaDto = new DomicilioSalidaDto();
        domicilioSalidaDto.setCalle(paciente.getDomicilio().getCalle());
        domicilioSalidaDto.setNumero(paciente.getDomicilio().getNumero());
        domicilioSalidaDto.setLocalidad(paciente.getDomicilio().getLocalidad());
        domicilioSalidaDto.setProvincia(paciente.getDomicilio().getProvincia());


        salidaPacienteDto.setDomicilioSalidaDto(domicilioSalidaDto);

        return salidaPacienteDto;
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));

        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }
}
