package com.hlc.coches.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size; 
@Entity
public class Coche {
	
	@Id  // --> Clave primaria
	@GeneratedValue(strategy = GenerationType.AUTO)  // --> El id se generá automáticamente
	private Long id;
	
	@NotBlank(message = "La marca no puede estar vacía")
	@Size(min=2, max=50, message="La marca debe tener entre 2 y 50 carácteres")
	private String marca;
	
	@Pattern(regexp = "^\\d{4}[A-Za-z]{3}$", message = "La matrícula debe tener 4 dígitos seguidos de 3 letras (ej: 5251MHX).")
	private String matricula;
	
	@Pattern(regexp = "^(rojo|azul|verde|amarillo)$", message = "El color debe ser uno de: rojo, azul, verde o amarillo.")
	private String color;
	
	public Coche() { //--> Constructor sin parametros es necesario para que JPA pueda instanciar la clase
		super();
	}
	
	public Coche(String marca, String matricula, String color) {
	    this.marca = marca;
	    this.matricula = matricula;
	    this.color = color;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}