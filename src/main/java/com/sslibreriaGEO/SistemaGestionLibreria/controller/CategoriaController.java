package com.sslibreriaGEO.SistemaGestionLibreria.controller;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Categoria;
import com.sslibreriaGEO.SistemaGestionLibreria.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> getAllCategoria() {
        return categoriaService.getAllCategoria();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id));
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaService.createCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria updateCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
        return categoriaService.updateCategoria(categoria, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
    }
}
