package com.hlc.coches.servicio;

import java.util.List;

import com.hlc.coches.entidad.Coche;

public interface CocheServicio {
	List<Coche> obtenerTodosLosCoches();
    Coche obtenerCochePorId(Long id);
    Coche guardarCoche(Coche coche);
    void eliminarCoche(Long id);
}
