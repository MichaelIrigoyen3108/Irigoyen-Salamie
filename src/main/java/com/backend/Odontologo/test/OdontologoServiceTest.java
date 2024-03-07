package com.backend.Odontologo.test;
import com.backend.Odontologo.dao.implement.OdontologoDaoH2;
import com.backend.Odontologo.entity.Odontologo;
import com.backend.Odontologo.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertTrue;


public class OdontologoServiceTest {
    private OdontologoService odontologoService;

    @Test
    public void debeInsertarYRetornarLosOdontologos(){
        // Arrange
          odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo("Kevin", "Irigoyen", 12345);
        // Act
        Odontologo registrado = odontologoService.registrar(odontologo);
        // Assert
        assertTrue(registrado.getId()!=0);

    }
}

