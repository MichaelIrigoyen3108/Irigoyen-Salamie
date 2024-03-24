package com.backend.Odontologo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class VistaController {

    @GetMapping("/inicio")
    public String mostrarPaginaInicio() {
        return "index";
    }
}
