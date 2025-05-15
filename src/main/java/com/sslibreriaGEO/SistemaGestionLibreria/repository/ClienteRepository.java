package com.sslibreriaGEO.SistemaGestionLibreria.repository;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
