package com.barberia.backend.repository;

import com.barberia.backend.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository
        extends JpaRepository<Sucursal, Long> {
    boolean existsByCodigo(String codigo);
}