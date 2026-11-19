package com.barberia.backend.controller;

import com.barberia.backend.dto.request.SucursalRequest;
import com.barberia.backend.dto.response.SucursalResponse;
import com.barberia.backend.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import com.barberia.backend.dto.request.CambioEstadoRequest;
@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SucursalResponse crear(@Valid @RequestBody SucursalRequest request) {
        return sucursalService.crearSucursal(request);
    }
    @GetMapping
    public List<SucursalResponse> listar() {
        return sucursalService.listarSucursales();
    }

    @GetMapping("/{id}")
    public SucursalResponse obtenerPorId(@PathVariable Long id) {
        return sucursalService.obtenerPorId(id);
    }
    @PutMapping("/{id}/estado")
    public SucursalResponse cambiarEstado(
            @PathVariable Long id,
            @Valid @RequestBody CambioEstadoRequest request
    ) {
        return sucursalService.cambiarEstado(id, request);
    }
    @PutMapping("/{id}")
    public SucursalResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SucursalRequest request
    ) {
        return sucursalService.actualizarSucursal(id, request);
    }
}