package com.sslibreriaGEO.SistemaGestionLibreria.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "detalles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_detalles")
public class Detalles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalles")
	private int id_detalles;
	
	
    @Column(length = 50)
	private int montoLibro;
    
    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;
	
    
    @JsonManagedReference(value = "detalles-libro")
    @OneToMany(mappedBy = "detalles", cascade = CascadeType.ALL)
    private List<Libro> libro;
    
    @JsonManagedReference(value = "detalles-autor")
    @OneToMany(mappedBy = "detalles", cascade = CascadeType.ALL)
    private List<Autor> autor;

	public Detalles(int id_detalles, int montoLibro, LocalDate fechaCompra, List<Libro> libro, List<Autor> autor) {
		super();
		this.id_detalles = id_detalles;
		this.montoLibro = montoLibro;
		this.fechaCompra = fechaCompra;
		this.libro = libro;
		this.autor = autor;
	}

	public Detalles() {
		super();
	}

	public void setId_detalles(int id_detalles) {
		this.id_detalles = id_detalles;
	}

	public void setMontoLibro(int montoLibro) {
		this.montoLibro = montoLibro;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}

	public void setAutor(List<Autor> autor) {
		this.autor = autor;
	}
    
    
    
    
    
}
