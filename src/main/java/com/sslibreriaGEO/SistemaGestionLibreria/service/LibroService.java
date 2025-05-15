package com.sslibreriaGEO.SistemaGestionLibreria.service;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Libro;
import com.sslibreriaGEO.SistemaGestionLibreria.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LibroService {
    private final LibroRepository repository;

    public LibroService(LibroRepository libroRepository) {
        this.repository = libroRepository;
    }

    //busca todos los libros registrados en la base de datos
    public List<Libro> getAllLibro() {
        return repository.findAll();
    }

    //busca un libro por su id
    public Libro getLibroById(Long id) {
        Optional<Libro> libroOptional = repository.findById(id);
        if (libroOptional.isPresent()) {
            return libroOptional.get();
        } else {
            throw new RuntimeException("Libro no encontrado");
        }
    }

    //crea un libro en la base de datos
    public Libro createLibro(Libro libro) {
        List<Libro> libroExistente = repository.findAll();
        for (Libro existingLibro : libroExistente) {
            if (existingLibro.getTitulo().equalsIgnoreCase(libro.getTitulo())) {
                throw new RuntimeException("Ya existe un libro con el mismo título");
            }
        }
        return repository.save(libro);
    }

    /**
     * Actualiza un libro existente en la base de datos con los detalles proporcionados.
     * Si el libro con el ID proporcionado no se encuentra, se lanza una RuntimeException.
     * Asegura que no exista otro libro con el mismo título antes de actualizar.
     *
     * @param libro el objeto book con los nuevos detalles a actualizar
     * @param id el ID del libro que se va a actualizar
     * @return el objeto libro actualizado después de guardar los cambios en la base de datos
     * @throws RuntimeException si no se encuentra el libro con el ID proporcionado
     * o si ya existe otro libro con el mismo título
     */
    public Libro updateLibro(Libro libro, Long id) {
        Optional<Libro> libroOptional = repository.findById(id);
        Libro libroExistente;
        if (libroOptional.isPresent()) {
            libroExistente = libroOptional.get();
        } else {
            throw new RuntimeException("Libro no encontrado");
        }

        if (!libroExistente.getTitulo().equalsIgnoreCase(libro.getTitulo())) {
            List<Libro> libros = repository.findAll();
            for (Libro existingLibro : libros) {
                if (existingLibro.getTitulo().equalsIgnoreCase(libro.getTitulo()) && !existingLibro.getIdLibro().equals(id)) {
                    throw new RuntimeException("Ya existe otro libro con el mismo título");
                }
            }
        }

        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setFechaPublicacion(libro.getFechaPublicacion());
        libroExistente.setPrecio(libro.getPrecio());
        libroExistente.setStock(libro.getStock());
        libroExistente.setAutor(libro.getAutor());
        libroExistente.setCategoria(libro.getCategoria());

        return repository.save(libroExistente);
    }

    //elimina un libro de la base de datos
    public void deleteLibro(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado");
        }
        repository.deleteById(id);
    }
}
