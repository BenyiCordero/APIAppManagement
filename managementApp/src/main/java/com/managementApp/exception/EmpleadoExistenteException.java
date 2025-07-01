package com.managementApp.exception;

public class EmpleadoExistenteException extends RuntimeException {
    public EmpleadoExistenteException() {
        super("El empleado/email ingresado ya existe");
    }
}
