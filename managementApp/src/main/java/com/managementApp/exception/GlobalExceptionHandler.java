package com.managementApp.exception;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TelefonoRepetidoException.class)
    public ResponseEntity<Object> handleTelefonoRepetido(TelefonoRepetidoException e){
        return ResponseEntity.status(409).body(Map.of("error: ", e.getMessage()));
    }

    @ExceptionHandler(EmpleadoExistenteException.class)
    public ResponseEntity<Object> handleEmpleadoExistente(EmpleadoExistenteException e){
        return ResponseEntity.status(409).body(Map.of("error ", e.getMessage()));
    }
}
