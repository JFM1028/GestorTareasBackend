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
  private TareaService tareaService;

  @InjectMocks
  private TareaController tareaController;

  private Tarea tarea;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    tarea = new Tarea("Tarea de prueba", false);
    tarea.setId("1");
  }

  @Test
  public void testObtenerTareas() {
    when(tareaService.obtenerTareas()).thenReturn(Arrays.asList(tarea));

    List<Tarea> tareas = tareaController.obtenerTareas();

    assertEquals(1, tareas.size());
    assertEquals("Tarea de prueba", tareas.get(0).getDescripcion());
  }

  @Test
  public void testAgregarTarea() {
    when(tareaService.agregarTarea(any(Tarea.class))).thenReturn(tarea);

    Tarea nuevaTarea = tareaController.agregarTarea(tarea);

    assertNotNull(nuevaTarea);
    assertEquals("Tarea de prueba", nuevaTarea.getDescripcion());
  }

  @Test
  public void testEliminarTarea() {
    doNothing().when(tareaService).eliminarTarea(tarea.getId());

    tareaController.eliminarTarea(tarea.getId());

    verify(tareaService, times(1)).eliminarTarea(tarea.getId());
  }

  @Test
  public void testMarcarCompletada() {
    // se busca donde esta la tarea competada
    tarea.setCompletada(true); // Marca la tarea como completada
    when(tareaService.marcarCompletada(tarea.getId())).thenReturn(tarea);

    // Ejecuta el método del controlador
    Tarea tareaCompletada = tareaController.marcarCompletada(tarea.getId());

    // Aqui uno se asegura que la tarea está marcada como completada
    assertTrue(
      tareaCompletada.isCompletada(),
      "La tarea debería estar marcada como completada"
    );

    // Verifica que el método en el servicio fue llamado una vez
    verify(tareaService, times(1)).marcarCompletada(tarea.getId());
  }
}
