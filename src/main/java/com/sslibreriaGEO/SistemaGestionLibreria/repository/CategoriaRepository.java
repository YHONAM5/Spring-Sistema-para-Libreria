package com.sslibreriaGEO.SistemaGestionLibreria.repository;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
