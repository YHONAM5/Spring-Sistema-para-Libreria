package com.sslibreriaGEO.SistemaGestionLibreria.model;

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
    
    
    @JsonBackReference(value = "detalles-autor")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalles", nullable = false)
    private VerDetalles detalles;
}
