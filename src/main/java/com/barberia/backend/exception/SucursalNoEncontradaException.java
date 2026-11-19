package com.barberia.backend.exception;

public class SucursalNoEncontradaException extends RuntimeException {

    public SucursalNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}