package com.backend.Odontologo.controller;

import com.backend.Odontologo.dto.entrada.PacienteEntradaDto;
import com.backend.Odontologo.dto.salida.PacienteSalidaDto;
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

    @GetMapping()
    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes()
    {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable Long id)
    {
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente)
    {
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto paciente)
    {
        return null;
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarPaciente(@RequestParam Long id){
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
