package com.hlc.coches.entidad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CocheTest {

    private Coche coche;

    @BeforeEach
    void setUp() {
        coche = new Coche();
        System.out.println("TEST");
    }
    
    @Test
    void testConstructorWithParameters() {
        String marca = "Toyota";
        String matricula = "1234ABC";
        String color = "Rojo";

        Coche coche = new Coche(marca, matricula, color);

        assertEquals(marca, coche.getMarca(), "La marca debería coincidir con el valor pasado al constructor");
        assertEquals(matricula, coche.getMatricula(), "La matrícula debería coincidir con el valor pasado al constructor");
        assertEquals(color, coche.getColor(), "El color debería coincidir con el valor pasado al constructor");
    }
    
    @Test
    void testSetAndGetId() {
        Long id = 1L;
        coche.setId(id);
        assertEquals(id, coche.getId(), "El ID debe ser igual al valor configurado");
    }

    @Test
    void testSetAndGetMarca() {
        String marca = "Toyota";
        coche.setMarca(marca);
        assertEquals(marca, coche.getMarca(), "La marca debe ser igual al valor configurado");
    }

    @Test
    void testSetAndGetMatricula() {
        String matricula = "1234ABC";
        coche.setMatricula(matricula);
        assertEquals(matricula, coche.getMatricula(), "La matrícula debe ser igual al valor configurado");
    }

    @Test
    void testSetAndGetColor() {
        String color = "Rojo";
        coche.setColor(color);
        assertEquals(color, coche.getColor(), "El color debe ser igual al valor configurado");
    }
}