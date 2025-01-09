
# **Coche MVC RA5**

Este proyecto es una aplicación de ejemplo basada en Spring Boot que implementa un sistema CRUD para gestionar coches utilizando el patrón MVC. La aplicación incluye vistas con Thymeleaf y utiliza pruebas unitarias para garantizar la calidad del desarrollo.

---

## **Características del Proyecto**

- **Backend**:
  - Implementado con Spring Boot.
  - Uso de JPA para la gestión de datos.
  - Integración con H2 como base de datos en memoria.

- **Frontend**:
  - Utilización de Thymeleaf para las vistas dinámicas.

- **Pruebas Unitarias**:
  - Tests para garantizar el correcto funcionamiento de cada componente, incluyendo controladores y servicios.

---

## **Estructura del Proyecto**

El proyecto está organizado en diferentes ramas que representan pasos en el desarrollo:

1. **Paso 1 - Modelo**:
   - Creación de la clase `Coche` como entidad JPA.

2. **Paso 2 - Configuración de la Base de Datos**:
   - Configuración de H2 como base de datos en memoria.

3. **Paso 3 - Servicio**:
   - Implementación de la capa de servicio para gestionar la lógica de negocio.

4. **Paso 4 - Controlador**:
   - Desarrollo de controladores para manejar las solicitudes y las respuestas.

5. **Paso 5 - Vistas Thymeleaf**:
   - Creación de vistas dinámicas para la interacción del usuario.

---

## **Importancia de las Pruebas Unitarias**

### **¿Qué son las pruebas unitarias?**
Las pruebas unitarias son tests automáticos que verifican el comportamiento de las unidades más pequeñas del software (como métodos y clases) de manera aislada.

### **Beneficios para la Industria**
- **Calidad Garantizada**:
  - Detectan errores en etapas tempranas del desarrollo, reduciendo costos y riesgos.
- **Mantenimiento Seguro**:
  - Permiten realizar cambios en el código con confianza, asegurando que no se rompa la funcionalidad existente.
- **Automatización en CI/CD**:
  - Integradas en pipelines, garantizan la estabilidad del código antes de ser desplegado.

### **Pruebas Implementadas**
En este proyecto, las pruebas unitarias se centran en los controladores, verificando que:
- Las vistas correctas son devueltas.
- Los modelos incluyen los datos esperados.
- Los métodos de servicios son llamados con los parámetros correctos.

#### Ejemplo:
```java
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
```

---

## **Instrucciones para Ejecutar el Proyecto**

1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/profeInformatica101/coche_mvc_ra5.git
   cd coche_mvc_ra5
   ```

2. **Ejecutar la Aplicación**:
   - Con Maven:
     ```bash
     ./mvnw spring-boot:run
     ```

3. **Acceder a la Aplicación**:
   - Visita: `http://localhost:8080/coches`

4. **Ejecutar las Pruebas**:
   ```bash
   ./mvnw test
   ```

---

## **Contribuciones**

Sigue los pasos establecidos en cada rama para contribuir de manera estructurada. Asegúrate de agregar o actualizar pruebas al realizar cambios.

---
