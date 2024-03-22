package com.backend.Odontologo.controller;

import com.backend.Odontologo.dto.entrada.OdontologoEntradaDto;
import com.backend.Odontologo.dto.entrada.PacienteEntradaDto;
import com.backend.Odontologo.dto.salida.OdontologoSalidaDto;
import com.backend.Odontologo.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/odontologos")

public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService)
    {
        this.odontologoService = odontologoService;
    }

    @GetMapping()
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologo()
    {
        return new ResponseEntity<>(odontologoService.listarOdontologo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id)
    {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologo)
    {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }

   // @PutMapping("/actualizar/{id}")
    //public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@RequestBody @Valid PacienteEntradaDto odontologo)
    //{
     //   return null;
    //}

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOdontologo(@RequestParam Long id) {
        return new ResponseEntity<>("Odontólogo eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}
