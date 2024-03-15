package com.backend.Odontologo.service;

import com.backend.Odontologo.dto.entrada.PacienteEntradaDto;
import com.backend.Odontologo.dto.salida.PacienteSalidaDto;

import java.awt.*;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(int id);

    PacienteSalidaDto buscarPacientePorId(Long id);
}
