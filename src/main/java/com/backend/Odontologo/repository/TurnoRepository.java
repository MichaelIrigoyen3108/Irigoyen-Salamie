package com.backend.Odontologo.repository;

import com.backend.Odontologo.entity.Odontologo;
import com.backend.Odontologo.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
