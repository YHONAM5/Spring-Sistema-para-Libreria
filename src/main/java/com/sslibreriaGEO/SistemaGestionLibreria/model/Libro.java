package com.sslibreriaGEO.SistemaGestionLibreria.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "libros")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idLibro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(precision = 10, scale = 2,nullable = false)
    private BigDecimal precio;

    @Column(columnDefinition = "integer default 0")
    private Integer stock;

    @JsonBackReference(value = "autor-libro")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    @JsonBackReference(value = "categoria-libro")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @JsonManagedReference(value = "prestamo-libro")
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos;
    
    @JsonBackReference(value = "detalles-libro")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalles", nullable = false)
    private VerDetalles detalles;
    
    
}
