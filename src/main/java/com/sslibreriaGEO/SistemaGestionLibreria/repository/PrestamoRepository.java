package com.sslibreriaGEO.SistemaGestionLibreria.repository;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
