package com.hlc.coches.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlc.coches.entidad.Coche;


@Repository // --> JpaRepository  incluye todos los métodos de CrudRepository y PagingAndSortingRepository
public interface CocheRepositorio  extends JpaRepository<Coche, Long>{

}
