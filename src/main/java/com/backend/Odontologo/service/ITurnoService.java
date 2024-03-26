package com.backend.Odontologo.service;


import com.backend.Odontologo.dto.entrada.TurnoEntradaDto;
import com.backend.Odontologo.dto.salida.TurnoSalidaDto;
import com.backend.Odontologo.exceptions.BadRequestException;
import com.backend.Odontologo.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException, ResourceNotFoundException;

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) ;

   // TurnoSalidaDto modificarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws ResourceNotFoundException;
}
