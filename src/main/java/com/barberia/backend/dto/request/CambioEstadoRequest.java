package com.barberia.backend.dto.request;

import jakarta.validation.constraints.NotNull;

public class CambioEstadoRequest {

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}