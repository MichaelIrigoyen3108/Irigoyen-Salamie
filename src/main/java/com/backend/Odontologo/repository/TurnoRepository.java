package com.backend.Odontologo.repository;

import com.backend.Odontologo.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Odontologo, Long> {
}
