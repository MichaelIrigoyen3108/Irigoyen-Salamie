package com.backend.Odontologo.service.impl;

import com.backend.Odontologo.dto.entrada.OdontologoEntradaDto;
import com.backend.Odontologo.dto.salida.OdontologoSalidaDto;
import com.backend.Odontologo.entity.Odontologo;
import com.backend.Odontologo.repository.OdontologoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologo_yRetornarSuId() {
        // Arrange
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123445, "Michael", "Irigoyen");

        // Act
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        // Assert
        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Michael", odontologoSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaListarOdontologos() {
        // Arrange
        odontologoRepository.save(new Odontologo("Juan", "Perez", 123456));

        // Act
        List<OdontologoSalidaDto> odontologos = odontologoService.listarOdontologo();

        // Assert
        assertFalse(odontologos.isEmpty());
        assertEquals(1, odontologos.size());
    }

    @Test
    @Order(3)
    void deberiaEliminarOdontologo() {
        // Arrange
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123456, "Michael","Irigoyen" );
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        Long odontologoId = Long.valueOf(odontologoSalidaDto.getId());

        // Act
        odontologoService.eliminarOdontologoPorId(odontologoId);
        List<OdontologoSalidaDto> odontologos = odontologoService.listarOdontologo();

        // Assert
        assertTrue(odontologos.isEmpty());
    }
}
