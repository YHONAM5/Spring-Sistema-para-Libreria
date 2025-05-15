package com.sslibreriaGEO.SistemaGestionLibreria.service;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Prestamo;
import com.sslibreriaGEO.SistemaGestionLibreria.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {
    private final PrestamoRepository repository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.repository = prestamoRepository;
    }

    //busca todos los prestamo registrados en la base de datos
    public List<Prestamo> getAllPrestamo() {
        return repository.findAll();
    }

    //busca un prestamo por su id
    public Prestamo getPrestamoById(Long id) {
        Optional<Prestamo> prestamoOptional = repository.findById(id);
        if (prestamoOptional.isPresent()) {
            return prestamoOptional.get();
        } else {
            throw new RuntimeException("Prestamo no encontrado");
        }
    }

    //crea un prestamo en la base de datos
    public Prestamo createPrestamo(Prestamo prestamo) {
        return repository.save(prestamo);
    }

    //actualiza un prestamo existente en la base de datos
    public Prestamo updatePrestamo(Prestamo prestamo, Long id) {
        Optional<Prestamo> prestamoOptional = repository.findById(id);
        if (prestamoOptional.isEmpty()) {
            throw new RuntimeException("Prestamo no encontrado");
        }

        Prestamo prestamoExistente = prestamoOptional.get();
        prestamoExistente.setFechaPrestamo(prestamo.getFechaPrestamo());
        prestamoExistente.setFechaDevolucion(prestamo.getFechaDevolucion());
        prestamoExistente.setCliente(prestamo.getCliente());
        prestamoExistente.setLibro(prestamo.getLibro());
        prestamoExistente.setEstado(prestamo.getEstado());

        return repository.save(prestamoExistente);
    }

    //elimina un prestamo de la base de datos
    public void deletePrestamo(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Prestamo no encontrado");
        }
        repository.deleteById(id);
    }
}
