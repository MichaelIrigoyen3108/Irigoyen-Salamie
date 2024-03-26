package com.backend.Odontologo.controller;

import com.backend.Odontologo.dto.entrada.OdontologoEntradaDto;
import com.backend.Odontologo.dto.entrada.PacienteEntradaDto;
import com.backend.Odontologo.dto.salida.OdontologoSalidaDto;
import com.backend.Odontologo.dto.salida.PacienteSalidaDto;
import com.backend.Odontologo.exceptions.ResourceNotFoundException;
import com.backend.Odontologo.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;



@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService)
    {
        this.pacienteService = pacienteService;
    }




    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente)
    {
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable Long id)
    {
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes()
    {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteSalidaDto> actualizarPacientePorId(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto, @PathVariable Long id)
    {
        return new ResponseEntity<>(pacienteService.actualizarPacientePorId(pacienteEntradaDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarPaciente(@RequestParam Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
