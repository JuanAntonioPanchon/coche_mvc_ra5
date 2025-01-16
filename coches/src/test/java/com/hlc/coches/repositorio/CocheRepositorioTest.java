package com.hlc.coches.repositorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hlc.coches.entidad.Coche;

@DataJpaTest
public class CocheRepositorioTest {

    @Autowired
    private CocheRepositorio cocheRepositorio;

    @Test
    void testSaveAndFindAll() {
        // Crear y guardar coches
        Coche coche1 = new Coche("Toyota", "1234ABC", "rojo");
        Coche coche2 = new Coche("Ford", "5678DEF", "azul");

        cocheRepositorio.save(coche1);
        cocheRepositorio.save(coche2);

        // Recuperar todos los coches
        List<Coche> coches = cocheRepositorio.findAll();

        assertEquals(2, coches.size(), "Debería haber 2 coches en la base de datos");
        assertTrue(coches.stream().anyMatch(c -> c.getMarca().equals("Toyota")), "Debería contener un coche Toyota");
        assertTrue(coches.stream().anyMatch(c -> c.getMarca().equals("Ford")), "Debería contener un coche Ford");
    }
}