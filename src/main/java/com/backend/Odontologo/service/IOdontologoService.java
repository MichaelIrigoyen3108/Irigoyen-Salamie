package com.backend.Odontologo.service;

import com.backend.Odontologo.dto.entrada.OdontologoEntradaDto;
import com.backend.Odontologo.dto.salida.OdontologoSalidaDto;
import com.backend.Odontologo.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

    List<OdontologoSalidaDto> listarOdontologo();

    OdontologoSalidaDto buscarOdontologoPorId(Long id);


}
