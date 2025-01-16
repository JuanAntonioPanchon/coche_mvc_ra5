package com.hlc.coches.controlador;

import com.hlc.coches.entidad.Coche;
import com.hlc.coches.servicio.CocheServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CocheControlador.class)
public class CocheControladorTest {
	
    // Inyecta automáticamente un objeto MockMvc para realizar pruebas de controladores de Spring MVC
    @Autowired
    private MockMvc mockMvc;
    
    // Crea un mock de la clase CocheServicio para simular su comportamiento en las pruebas
    @MockitoBean
    private CocheServicio cocheServicio;

    // Configuración inicial para cada prueba
    @BeforeEach
    void setUp() {
        reset(cocheServicio);
    }

    // Prueba que el controlador llama al servicio para obtener una lista de coches y renderiza la vista 'lista'.
    @Test
    @DisplayName("Debe listar todos los coches y mostrar la vista correcta")
    void testListarCoches() throws Exception {
        when(cocheServicio.obtenerTodosLosCoches()).thenReturn(
                Arrays.asList(
                        new Coche("Toyota", "1234ABC", "Rojo"),
                        new Coche("Ford", "5678DEF", "Azul")
                )
        );

        mockMvc.perform(get("/coches"))
                .andExpect(status().isOk())
                .andExpect(view().name("coches/lista"))
                .andExpect(model().attributeExists("coches"))
                .andExpect(model().attribute("coches", hasSize(2)));

        verify(cocheServicio, times(1)).obtenerTodosLosCoches();
    }

    // Prueba que se renderiza la vista 'formulario' con un nuevo objeto coche en el modelo.
    @Test
    @DisplayName("Debe mostrar el formulario para agregar un nuevo coche con el objeto correcto")
    void testMostrarFormularioNuevoCoche() throws Exception {
        mockMvc.perform(get("/coches/nuevo"))
                .andExpect(status().isOk())
                .andExpect(view().name("coches/formulario"))
                .andExpect(model().attributeExists("coche"))
                .andExpect(model().attribute("coche", instanceOf(Coche.class))); // Verifica que el atributo es una instancia de Coche
    }

    // Prueba que el controlador guarda un coche a través del servicio y redirige correctamente.
    @Test
    @DisplayName("Debe guardar un coche y redirigir a la lista de coches")
    void testGuardarCoche() throws Exception {
        mockMvc.perform(post("/coches")
                .param("marca", "Toyota")
                .param("matricula", "1234ABC")
                .param("color", "rojo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coches"));

        verify(cocheServicio, times(1)).guardarCoche(any(Coche.class));
    }

    // Prueba que el controlador muestra el formulario con los datos del coche existente para edición.
    @Test
    @DisplayName("Debe mostrar el formulario para editar un coche existente con el objeto correcto")
    void testMostrarFormularioEditarCoche() throws Exception {
        Coche coche = new Coche("Toyota", "1234ABC", "Rojo");
        coche.setId(1L);
        when(cocheServicio.obtenerCochePorId(1L)).thenReturn(coche);

        mockMvc.perform(get("/coches/editar/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("coches/formulario"))
                .andExpect(model().attributeExists("coche"))
                .andExpect(model().attribute("coche", coche));
    }

    // Prueba que el controlador actualiza un coche a través del servicio y redirige correctamente.
    @Test
    @DisplayName("Debe actualizar un coche existente y redirigir a la lista de coches")
    void testActualizarCoche() throws Exception {
        mockMvc.perform(post("/coches/1")
                .param("marca", "Toyota")
                .param("matricula", "1234ABC")
                .param("color", "Rojo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coches"));

        verify(cocheServicio, times(1)).guardarCoche(any(Coche.class));
    }

    // Prueba que el controlador elimina un coche por su ID a través del servicio y redirige correctamente.
    @Test
    @DisplayName("Debe eliminar un coche y redirigir a la lista de coches")
    void testEliminarCoche() throws Exception {
        mockMvc.perform(get("/coches/eliminar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coches"));

        verify(cocheServicio, times(1)).eliminarCoche(1L);
    }
}

