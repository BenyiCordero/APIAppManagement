package com.managementApp.exception;

public class TelefonoRepetidoException extends RuntimeException {
    public TelefonoRepetidoException() {
        super("Telefono existente");
    }
}
