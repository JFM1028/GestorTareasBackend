package com.hazinlab.gestortareasbackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.hazinlab.gestortareasbackend.controller.TareaController;
import com.hazinlab.gestortareasbackend.model.Tarea;
import com.hazinlab.gestortareasbackend.service.TareaService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TareaControllerTest {

  @Mock
  private TareaService tareaService; // Se crea un mock de TareaService.

  @InjectMocks
  private TareaController tareaController; // Se inyecta el mock en TareaController.

  private Tarea tarea; // Se define un objeto Tarea para usar en las pruebas.

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this); // Inicializa los mocks.
    tarea = new Tarea("tarea prueba", "Tarea de prueba", false); // Crea una nueva tarea de prueba.
    tarea.setId("1"); // Establece un ID para la tarea.
  }

  @Test
  public void testObtenerTareas() {
    // Simula que el servicio devuelve una lista con la tarea de prueba.
    when(tareaService.obtenerTareas()).thenReturn(Arrays.asList(tarea));

    // Llama al método del controlador que se está probando.
    List<Tarea> tareas = tareaController.obtenerTareas();

    // Verifica que la lista devuelta tenga el tamaño correcto y la descripción esperada.
    assertEquals(1, tareas.size()); // Comprueba que hay 1 tarea.
    assertEquals("Tarea de prueba", tareas.get(0).getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testAgregarTarea() {
    // Simula que el servicio agrega una tarea y devuelve la tarea de prueba.
    when(tareaService.agregarTarea(any(Tarea.class))).thenReturn(tarea);

    // Llama al método del controlador que se está probando.
    Tarea nuevaTarea = tareaController.agregarTarea(tarea);

    // Verifica que la tarea agregada no sea nula y que tenga la descripción correcta.
    assertNotNull(nuevaTarea); // Asegura que la nueva tarea no es nula.
    assertEquals("Tarea de prueba", nuevaTarea.getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testEliminarTarea() {
    // Simula que el servicio elimina una tarea sin lanzar excepciones.
    doNothing().when(tareaService).eliminarTarea(tarea.getId());

    // Llama al método del controlador que se está probando.
    tareaController.eliminarTarea(tarea.getId());

    // Verifica que el método del servicio fue llamado exactamente una vez.
    verify(tareaService, times(1)).eliminarTarea(tarea.getId());
  }

  @Test
  public void testMarcarCompletada() {
    // Marca la tarea como completada.
    tarea.setCompletada(true);
    // Simula que el servicio devuelve la tarea completada.
    when(tareaService.marcarCompletada(tarea.getId())).thenReturn(tarea);

    // Llama al método del controlador que se está probando.
    Tarea tareaCompletada = tareaController.marcarCompletada(tarea.getId());

    // Verifica que la tarea esté marcada como completada.
    assertTrue(
      tareaCompletada.isCompletada(),
      "La tarea debería estar marcada como completada"
    );

    // Verifica que el método del servicio fue llamado exactamente una vez.
    verify(tareaService, times(1)).marcarCompletada(tarea.getId());
  }
}
