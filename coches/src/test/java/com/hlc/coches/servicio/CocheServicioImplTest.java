package com.hlc.coches.servicio;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.hlc.coches.entidad.Coche;
import com.hlc.coches.repositorio.CocheRepositorio;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CocheServicioImplTest {
    // Simulamos el repositorio como una dependencia
    @Mock
    private CocheRepositorio cocheRepositorio;

    // Creamos una instancia real de CocheServicioImpl y
    // se inyecta automáticamente el mock de cocheRepositorio en esta clase.
    @InjectMocks
    private CocheServicioImpl cocheServicio;

    // Inicializa los mocks antes de cada prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Activa @Mock y @InjectMocks
    }

    @Test
    void testObtenerTodosLosCoches() {
        // Simular el comportamiento del repositorio
        List<Coche> coches = Arrays.asList(
                new Coche("Toyota", "1234ABC", "Rojo"),
                new Coche("Ford", "5678DEF", "Azul")
        );
        when(cocheRepositorio.findAll()).thenReturn(coches);

        // Llamar al servicio
        List<Coche> resultado = cocheServicio.obtenerTodosLosCoches();

        // Verificar resultados
        assertEquals(2, resultado.size(), "Debe devolver 2 coches");
        verify(cocheRepositorio, times(1)).findAll(); // Asegura que findAll fue llamado una vez
    }
    
    @Test
    void testObtenerCochePorId() {
        // Configuración del mock
        Coche coche = new Coche("Toyota", "1234ABC", "Rojo");
        when(cocheRepositorio.findById(1L)).thenReturn(Optional.of(coche));

        // Llamada al servicio
        Coche resultado = cocheServicio.obtenerCochePorId(1L);

        // Verificaciones
        assertNotNull(resultado, "El coche no debe ser null");
        assertEquals("Toyota", resultado.getMarca(), "La marca debe ser 'Toyota'");
        verify(cocheRepositorio, times(1)).findById(1L); // Verifica que se llamó a findById una vez
    }

    @Test
    void testObtenerCochePorIdNotFound() {
        // Configuración del mock
        when(cocheRepositorio.findById(1L)).thenReturn(Optional.empty());

        // Llamada al servicio y validación de excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cocheServicio.obtenerCochePorId(1L);
        });
        assertEquals("Coche no encontrado con ID: 1", exception.getMessage());
    }

    @Test
    void testGuardarCoche() {
        // Configuración del mock
        Coche coche = new Coche("Toyota", "1234ABC", "Rojo");
        when(cocheRepositorio.save(coche)).thenReturn(coche);

        // Llamada al servicio
        Coche resultado = cocheServicio.guardarCoche(coche);

        // Verificaciones
        assertNotNull(resultado, "El coche guardado no debe ser null");
        assertEquals("Toyota", resultado.getMarca(), "La marca debe ser 'Toyota'");
        verify(cocheRepositorio, times(1)).save(coche); // Verifica que se llamó a save una vez
    }

    @Test
    void testEliminarCoche() {
        // Llamada al servicio
        cocheServicio.eliminarCoche(1L);

        // Verificaciones
        verify(cocheRepositorio, times(1)).deleteById(1L); // Verifica que se llamó a deleteById una vez
    }

}
