package com.sslibreriaGEO.SistemaGestionLibreria.controller;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Libro;
import com.sslibreriaGEO.SistemaGestionLibreria.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> getAllLibro() {
        return libroService.getAllLibro();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.getLibroById(id));
    }

    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroService.createLibro(libro);
    }

    @PutMapping("/{id}")
    public Libro updateLibro(@RequestBody Libro libro, @PathVariable Long id) {
        return libroService.updateLibro(libro, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        libroService.deleteLibro(id);
    }
}
