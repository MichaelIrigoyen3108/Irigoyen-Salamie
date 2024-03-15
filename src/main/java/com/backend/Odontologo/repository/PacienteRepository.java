package com.backend.Odontologo.repository;

import com.backend.Odontologo.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
