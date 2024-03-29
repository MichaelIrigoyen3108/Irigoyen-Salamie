package com.backend.Odontologo.service.impl;

import com.backend.Odontologo.dto.entrada.TurnoEntradaDto;
import com.backend.Odontologo.dto.salida.OdontologoSalidaDto;
import com.backend.Odontologo.dto.salida.PacienteSalidaDto;
import com.backend.Odontologo.dto.salida.TurnoSalidaDto;
import com.backend.Odontologo.entity.Odontologo;
import com.backend.Odontologo.entity.Paciente;
import com.backend.Odontologo.entity.Turno;
import com.backend.Odontologo.exceptions.BadRequestException;
import com.backend.Odontologo.exceptions.ResourceNotFoundException;
import com.backend.Odontologo.repository.TurnoRepository;
import com.backend.Odontologo.service.ITurnoService;
import com.backend.Odontologo.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper,
                        PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }


    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException, ResourceNotFoundException {
        TurnoSalidaDto turnoSalidaDto;
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());

        String pacienteNoEnBdd = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBdd = "El odontologo no se encuentra en nuestra base de datos";
        String ambosNulos = "El paciente y el odontologo no se encuentran en nuestra base de datos";

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.error(ambosNulos);
                throw new BadRequestException(ambosNulos);
            } else if (paciente == null) {
                LOGGER.error(pacienteNoEnBdd);
                throw new BadRequestException(pacienteNoEnBdd);
            } else {
                LOGGER.error(odontologoNoEnBdd);
                throw new BadRequestException(odontologoNoEnBdd);
            }
        } else {
            Turno turnoNuevo = new Turno(modelMapper.map(odontologo, Odontologo.class),modelMapper.map(paciente, Paciente.class), turnoEntradaDto.getFechaYHora());
            turnoNuevo = turnoRepository.save(turnoNuevo);
            turnoSalidaDto = entidadADtoSalida(turnoNuevo, paciente, odontologo);
            LOGGER.info("Nuevo turno registrado con exito: {}", turnoSalidaDto);
        }
        return turnoSalidaDto;
    }



    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnoSalidaDtos =  turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();

        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnoSalidaDtos));
        return turnoSalidaDtos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if(turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else {
            LOGGER.error("El id no se encuentra registrado en la base de datos");
        }

        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

  //metodo para modificar turno


    private TurnoSalidaDto entidadADtoSalida(Turno turno, PacienteSalidaDto pacienteSalidaDto, OdontologoSalidaDto odontologoSalidaDto){
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteSalidaDto(pacienteSalidaDto);
        turnoSalidaDto.setOdontologoSalidaDto(odontologoSalidaDto);
        return turnoSalidaDto;
    }


}
