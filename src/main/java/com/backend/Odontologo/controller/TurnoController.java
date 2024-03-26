package com.backend.Odontologo.controller;

import com.backend.Odontologo.dto.entrada.TurnoEntradaDto;
import com.backend.Odontologo.dto.salida.TurnoSalidaDto;
import com.backend.Odontologo.exceptions.BadRequestException;
import com.backend.Odontologo.exceptions.ResourceNotFoundException;
import com.backend.Odontologo.service.ITurnoService;
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
        public ResponseEntity<TurnoSalidaDto> registrarPaciente(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto) throws BadRequestException, ResourceNotFoundException {
            return new ResponseEntity<>(turnoService.registrarTurno(turnoEntradaDto), HttpStatus.CREATED);
        }
        @GetMapping("/{id}")
        public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
        }

        @GetMapping()
        public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
            return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
        }

        // @PutMapping("/actualizar/{id}")
        //public ResponseEntity<TurnoSalidaDto> modificarTurno(@RequestBody @Valid TurnoEntradaDto turno, @PathVariable Long id) {
         //   TurnoSalidaDto turnoModificado = turnoService.modificarTurno(turno, id);
           // return new ResponseEntity<>(turnoModificado, HttpStatus.OK);
        //}

        @DeleteMapping("/eliminar")
        public ResponseEntity<?> eliminarTurno(@RequestParam Long id) throws ResourceNotFoundException {
            turnoService.eliminarTurno(id);
            return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
        }


}
