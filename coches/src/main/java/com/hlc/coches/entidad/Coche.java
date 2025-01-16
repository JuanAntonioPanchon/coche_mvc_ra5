package com.hlc.coches.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity //--> Define Entidad JPA para mapear la BBDD
public class Coche {
	
	@Id  // --> Clave primaria
	@GeneratedValue(strategy = GenerationType.AUTO)  // --> El id se generá automáticamente
	private Long id;
	
	private String marca;
	private String matricula;
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