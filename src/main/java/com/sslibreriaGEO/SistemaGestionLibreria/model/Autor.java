package com.sslibreriaGEO.SistemaGestionLibreria.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "autores")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAutor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long idAutor;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(length = 500)
    private String biografia;

    @Column(length = 50)
    private String nacionalidad;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "autor-libro")
    private List<Libro> libros;
    
    @JsonBackReference(value = "autor-detalles")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalles", nullable = false)
    private Detalles detalles;
}
