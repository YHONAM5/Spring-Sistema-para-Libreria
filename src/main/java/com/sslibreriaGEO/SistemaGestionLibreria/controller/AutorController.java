package com.sslibreriaGEO.SistemaGestionLibreria.controller;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Autor;
import com.sslibreriaGEO.SistemaGestionLibreria.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {
    private final AutorService autorService;
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<Autor> getAllAutor(){
        return autorService.getAllAutor();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id){
        return ResponseEntity.ok(autorService.getAutorById(id));
    }

    @PostMapping
    public Autor createAutor(@RequestBody Autor autor){
        return autorService.createAutor(autor);
    }

    @PutMapping("/{id}")
    public Autor updateAutor(@RequestBody Autor autor, @PathVariable Long id){
        return autorService.updateAutor(autor, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAutor(@PathVariable Long id){
        autorService.deleteAutor(id);
    }
}
