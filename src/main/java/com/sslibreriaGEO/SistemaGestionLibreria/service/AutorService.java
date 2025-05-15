package com.sslibreriaGEO.SistemaGestionLibreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Autor;
import com.sslibreriaGEO.SistemaGestionLibreria.repository.AutorRepository;

@Service
public class AutorService {
    private final AutorRepository repository;
    public AutorService(AutorRepository autorRepository) {
        this.repository = autorRepository;
    }

    //busca todos los autores registrados en la base de datos
    public List<Autor> getAllAutor(){
        return repository.findAll();
    }

    //busca un autor por su id
    public Autor getAutorById(Long id) {
        Optional<Autor> autorOptional = repository.findById(id);
        if (autorOptional.isPresent()) {
            return autorOptional.get();
        } else {
            throw new RuntimeException("Autor no encontrado");
        }
    }

    //crea un autor en la base de datos
    public Autor createAutor(Autor autor) {
        List<Autor> autorExitente = repository.findAll();
        for (Autor existingAutor : autorExitente) {
            if (existingAutor.getNombres().equalsIgnoreCase(autor.getNombres())) {
                throw new RuntimeException("Ya existe un autor con el mismo nombre");
            }
        }
        return repository.save(autor);
    }
    //actualiza un autor existente en la base de datos
    public Autor updateAutor(Autor autor, Long id) {
        Optional<Autor> autorOptional = repository.findById(id);
        Autor autorExistente;
        if (autorOptional.isPresent()) {
            autorExistente = autorOptional.get();
        }else {
            throw new RuntimeException("Autor no encontrado");
        }
        // Si el nombre ha cambiado, verificar que no exista otro autor con el nuevo nombre
        if (!autorExistente.getNombres().equalsIgnoreCase(autor.getNombres())) {
            List<Autor> autores = repository.findAll();
            for (Autor existingAutor : autores) {
                if (existingAutor.getNombres().equalsIgnoreCase(autor.getNombres()) && !existingAutor.getIdAutor().equals(id)) {
                    throw new RuntimeException("Ya existe otro autor con el mismo nombre");
                }
            }
        }

        autorExistente.setNombres(autor.getNombres());
        autorExistente.setBiografia(autor.getBiografia());
        autorExistente.setNacionalidad(autor.getNacionalidad());

        return repository.save(autorExistente);
    }

    //elimina un autor de la base de datos
    public void deleteAutor(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Autor no encontrado");
        }
        repository.deleteById(id);
    }
}

