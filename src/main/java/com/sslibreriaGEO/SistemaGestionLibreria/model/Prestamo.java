package com.sslibreriaGEO.SistemaGestionLibreria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "prestamos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPrestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private Long idPrestamo;

    @CreationTimestamp
    @Column(name = "fecha_prestamo",nullable = false, updatable = false,columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    @Column(length = 50, nullable = false)
    private String estado;

    @JsonBackReference(value = "cliente-prestamo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @JsonBackReference(value = "prestamo-libro")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;
}
