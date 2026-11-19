package com.barberia.backend.exception;

public class SucursalDuplicadaException extends RuntimeException {

    public SucursalDuplicadaException(String mensaje) {
        super(mensaje);
    }
}