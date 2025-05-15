package com.sslibreriaGEO.SistemaGestionLibreria.repository;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
