package com.backend.Odontologo.service.impl;

import com.backend.Odontologo.dto.entrada.TurnoEntradaDto;
import com.backend.Odontologo.dto.salida.OdontologoSalidaDto;
import com.backend.Odontologo.dto.salida.PacienteSalidaDto;
import com.backend.Odontologo.dto.salida.TurnoSalidaDto;
import com.backend.Odontologo.entity.Turno;
import com.backend.Odontologo.exceptions.BadRequestException;
import com.backend.Odontologo.repository.TurnoRepository;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    private TurnoRepository turnoRepository = mock(TurnoRepository.class);
    private PacienteService pacienteService = mock(PacienteService.class);
    private OdontologoService odontologoService = mock(OdontologoService.class);
    private ModelMapper modelMapper = mock(ModelMapper.class);

    @Test
    @Order(1)
    void deberiaRegistrarTurno() throws BadRequestException {
        // Arrange
        TurnoService turnoService = new TurnoService(turnoRepository, modelMapper, pacienteService, odontologoService);
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L, 1L, LocalDateTime.now());
        PacienteSalidaDto pacienteSalidaDto = new PacienteSalidaDto();
        OdontologoSalidaDto odontologoSalidaDto = new OdontologoSalidaDto();
        Turno turnoRegistrado = new Turno();

        when(pacienteService.buscarPacientePorId(1L)).thenReturn(pacienteSalidaDto);
        when(odontologoService.buscarOdontologoPorId(1L)).thenReturn(odontologoSalidaDto);
        when(modelMapper.map(any(Turno.class), eq(TurnoSalidaDto.class))).thenReturn(new TurnoSalidaDto());
        when(turnoRepository.save(any(Turno.class))).thenReturn(turnoRegistrado);

        // Act
        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        // Assert
        assertNotNull(turnoSalidaDto);
        verify(turnoRepository, times(1)).save(any(Turno.class));
    }

    @Test
    @Order(2)
    void deberiaListarTurnos() {
        // Arrange
        TurnoService turnoService = new TurnoService(turnoRepository, modelMapper, pacienteService, odontologoService);
        when(turnoRepository.findAll()).thenReturn(List.of(new Turno()));

        // Act
        List<TurnoSalidaDto> turnos = turnoService.listarTurnos();

        // Assert
        assertNotNull(turnos);
        assertFalse(turnos.isEmpty());
        verify(turnoRepository, times(1)).findAll();
    }

    @Test
    @Order(3)
    void deberiaBuscarTurnoPorId() {
        // Arrange
        TurnoService turnoService = new TurnoService(turnoRepository, modelMapper, pacienteService, odontologoService);
        Long id = 1L;
        Turno turno = new Turno();
        when(turnoRepository.findById(id)).thenReturn(Optional.of(turno));
        when(modelMapper.map(any(Turno.class), eq(TurnoSalidaDto.class))).thenReturn(new TurnoSalidaDto());

        // Act
        TurnoSalidaDto turnoEncontrado = turnoService.buscarTurnoPorId(id);

        // Assert
        assertNotNull(turnoEncontrado);
        verify(turnoRepository, times(1)).findById(id);
    }

    @Test
    @Order(4)
    void deberiaEliminarTurno() {
        // Arrange
        TurnoService turnoService = new TurnoService(turnoRepository, modelMapper, pacienteService, odontologoService);
        Long id = 1L;

        // Act
        assertDoesNotThrow(() -> turnoService.eliminarTurno(id));

        // Assert
        verify(turnoRepository, times(1)).deleteById(id);
    }
}
