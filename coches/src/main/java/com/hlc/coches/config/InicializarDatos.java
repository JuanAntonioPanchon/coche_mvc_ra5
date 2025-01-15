package com.hlc.coches.config;

import org.springframework.stereotype.Component;

import com.hlc.coches.entidad.Coche;
import com.hlc.coches.repositorio.CocheRepositorio;

import jakarta.annotation.PostConstruct;

@Component
public class InicializarDatos {
	
	
	private CocheRepositorio cocheRepositorio;

	public InicializarDatos(CocheRepositorio cocheRepositorio) {
		super();
		this.cocheRepositorio = cocheRepositorio;
	}
	

	 @PostConstruct
	  public void init() {
		 
	     Coche coche1 = new Coche("Toyota", "1234ABC", "rojo");
	     Coche coche2 = new Coche("Ford", "5678DEF", "azul");
	     Coche coche3 = new Coche("Honda", "9101GHI", "amarillo");
	     
	     
		 cocheRepositorio.save(coche1);
		 cocheRepositorio.save(coche2);
		 cocheRepositorio.save(coche3);
	  }
}
