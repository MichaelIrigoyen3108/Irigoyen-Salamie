package com.backend.Odontologo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejarResourceNotFound(ResourceNotFoundException resourceNotFoundException){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no encontrado: " + resourceNotFoundException.getMessage());
        return mensaje;
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarBadRequest(BadRequestException badRequestException){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Solicitud incorrecta: " + badRequestException.getMessage());
        return mensaje;
    }






}
