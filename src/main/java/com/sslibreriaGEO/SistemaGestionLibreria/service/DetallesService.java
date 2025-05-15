package com.sslibreriaGEO.SistemaGestionLibreria.service;

import java.util.List;  // Use java.util.List instead of java.awt.List

import com.sslibreriaGEO.SistemaGestionLibreria.model.Autor;
import com.sslibreriaGEO.SistemaGestionLibreria.model.Detalles;
import com.sslibreriaGEO.SistemaGestionLibreria.repository.DetalleRepository;
import com.sslibreriaGEO.SistemaGestionLibreria.repository.AutorRepository;  // Assuming you need an AutorRepository

public class DetallesService {

    private final DetalleRepository detalleRepository;

    // Constructor for injecting both repositories
    public DetallesService(DetalleRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }

    // Method to get all Detalles
    public List<Detalles> getAllDetalle() {
        return detalleRepository.findAll();
    }

    // Method to create a new Autor
    public Autor createDetalles(Detalles detalles) {
        List<Detalles> detallesExitente = detalleRepository.findAll();  // Use autorRepository, not detalleRepository
        for (Detalles existingdetalle : detallesExitente) {
            if (existingdetalle.get().equalsIgnoreCase(autor.getNombres())) {
                throw new RuntimeException("Ya existe un autor con el mismo nombre");
            }
        }
        return autorRepository.save(autor);  // Save the new autor using autorRepository
    }
}
