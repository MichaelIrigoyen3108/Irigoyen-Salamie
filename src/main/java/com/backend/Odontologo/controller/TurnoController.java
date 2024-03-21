package com.backend.Odontologo.controller;

import com.backend.Odontologo.dto.entrada.PacienteEntradaDto;
import com.backend.Odontologo.dto.entrada.TurnoEntradaDto;
import com.backend.Odontologo.dto.salida.PacienteSalidaDto;
import com.backend.Odontologo.dto.salida.TurnoSalidaDto;
import com.backend.Odontologo.exceptions.BadRequestException;
import com.backend.Odontologo.exceptions.ResourceNotFoundException;
import com.backend.Odontologo.service.ITurnoService;
import com.backend.Odontologo.service.impl.OdontologoService;
import com.backend.Odontologo.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto) throws BadRequestException, ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoEntradaDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos()
    {
        return new ResponseEntity<>(TurnoService.listarTurnos(), HttpStatus.OK);
    }

}
