package com.barberia.backend.service;

import com.barberia.backend.dto.request.SucursalRequest;
import com.barberia.backend.dto.response.SucursalResponse;
import com.barberia.backend.entity.Sucursal;
import com.barberia.backend.exception.SucursalDuplicadaException;
import com.barberia.backend.repository.SucursalRepository;
import org.springframework.stereotype.Service;
import com.barberia.backend.exception.SucursalNoEncontradaException;
import com.barberia.backend.dto.request.CambioEstadoRequest;
import java.util.List;

@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public SucursalResponse crearSucursal(SucursalRequest request) {

        if (sucursalRepository.existsByCodigo(request.getCodigo())) {
            throw new SucursalDuplicadaException(
                    "El código de sucursal ya está registrado"
            );
        }

        Sucursal sucursal = new Sucursal();

        sucursal.setCodigo(request.getCodigo());
        sucursal.setNombre(request.getNombre());
        sucursal.setDireccion(request.getDireccion());
        sucursal.setTelefono(request.getTelefono());
        sucursal.setEmail(request.getEmail());
        sucursal.setEstado(true);

        Sucursal sucursalGuardada = sucursalRepository.save(sucursal);

        return convertirAResponse(sucursalGuardada);
    }

    public List<SucursalResponse> listarSucursales() {

        List<Sucursal> sucursales = sucursalRepository.findAll();

        return sucursales.stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private SucursalResponse convertirAResponse(Sucursal sucursal) {

        SucursalResponse response = new SucursalResponse();

        response.setId(sucursal.getId());
        response.setCodigo(sucursal.getCodigo());
        response.setNombre(sucursal.getNombre());
        response.setDireccion(sucursal.getDireccion());
        response.setTelefono(sucursal.getTelefono());
        response.setEmail(sucursal.getEmail());
        response.setEstado(sucursal.getEstado());
        response.setCreatedAt(sucursal.getCreatedAt());
        response.setUpdatedAt(sucursal.getUpdatedAt());

        return response;
    }
    public SucursalResponse obtenerPorId(Long id) {

        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() ->
                        new SucursalNoEncontradaException(
                                "La sucursal con ID " + id + " no existe"
                        )
                );
        return convertirAResponse(sucursal);
    }

    public SucursalResponse actualizarSucursal(Long id, SucursalRequest request) {

        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() ->
                        new SucursalNoEncontradaException(
                                "La sucursal con ID " + id + " no existe"
                        )
                );
        sucursal.setCodigo(request.getCodigo());
        sucursal.setNombre(request.getNombre());
        sucursal.setDireccion(request.getDireccion());
        sucursal.setTelefono(request.getTelefono());
        sucursal.setEmail(request.getEmail());
        Sucursal sucursalActualizada = sucursalRepository.save(sucursal);

        return convertirAResponse(sucursalActualizada);
    }

    public SucursalResponse cambiarEstado(
            Long id,
            CambioEstadoRequest request
    ) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() ->
                        new SucursalNoEncontradaException(
                                "La sucursal con ID " + id + " no existe"
                        )
                );
        sucursal.setEstado(request.getEstado());
        Sucursal sucursalActualizada = sucursalRepository.save(sucursal);
        return convertirAResponse(sucursalActualizada);

    }

}