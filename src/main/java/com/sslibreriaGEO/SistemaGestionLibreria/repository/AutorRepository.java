package com.sslibreriaGEO.SistemaGestionLibreria.repository;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
