package com.sslibreriaGEO.SistemaGestionLibreria.controller;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Prestamo;
import com.sslibreriaGEO.SistemaGestionLibreria.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> getAllPrestamo() {
        return prestamoService.getAllPrestamo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.getPrestamoById(id));
    }

    @PostMapping
    public Prestamo createPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.createPrestamo(prestamo);
    }

    @PutMapping("/{id}")
    public Prestamo updatePrestamo(@RequestBody Prestamo prestamo, @PathVariable Long id) {
        return prestamoService.updatePrestamo(prestamo, id);
    }

    @DeleteMapping("/{id}")
    public void deletePrestamo(@PathVariable Long id) {
        prestamoService.deletePrestamo(id);
    }
}
