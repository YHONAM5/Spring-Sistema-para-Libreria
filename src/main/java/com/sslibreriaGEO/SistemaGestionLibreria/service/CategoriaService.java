package com.sslibreriaGEO.SistemaGestionLibreria.service;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Categoria;
import com.sslibreriaGEO.SistemaGestionLibreria.repository.CategoriaRepository;
import jakarta.transaction.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.repository = categoriaRepository;
    }

    //busca todas las categorias registradas en la base de datos
    public List<Categoria> getAllCategoria() {
        return repository.findAll();
    }

    //busca una categoria por su id
    public Categoria getCategoriaById(Long id) {
        Optional<Categoria> categoriaOptional = repository.findById(id);
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }
    }

    //crea una categoria en la base de datos
    public Categoria createCategoria(Categoria categoria) {
        List<Categoria> categoriaExistente = repository.findAll();
        for (Categoria existingCategoria : categoriaExistente) {
            if (existingCategoria.getNombre().equalsIgnoreCase(categoria.getNombre())) {
                throw new RuntimeException("Ya existe una categoria con el mismo nombre");
            }
        }
        return repository.save(categoria);
    }

    //actualiza una categoria existente en la base de datos
    public Categoria updateCategoria(Categoria categoria, Long id) {
        Optional<Categoria> categoriaOptional = repository.findById(id);
        Categoria categoriaExistente;
        // Si el nombre ha cambiado, verificar que no exista otra categoria con el nuevo nombre
        if (categoriaOptional.isPresent()) {
            categoriaExistente = categoriaOptional.get();
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }

        // Si el nombre ha cambiado, verificar que no exista otra categoria con el nuevo nombre
        if (!categoriaExistente.getNombre().equalsIgnoreCase(categoria.getNombre())) {
            List<Categoria> categorias = repository.findAll();
            for (Categoria existingCategoria : categorias) {
                if (existingCategoria.getNombre().equalsIgnoreCase(categoria.getNombre()) && !existingCategoria.getIdCategoria().equals(id)) {
                    throw new RuntimeException("Ya existe otra categoria con el mismo nombre");
                }
            }
        }

        categoriaExistente.setNombre(categoria.getNombre());
        categoriaExistente.setDescripcion(categoria.getDescripcion());

        return repository.save(categoriaExistente);
    }

    public void deleteCategoria(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada");
        }
        repository.deleteById(id);
    }
}
