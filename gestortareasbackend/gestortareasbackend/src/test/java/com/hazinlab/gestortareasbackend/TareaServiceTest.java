package com.hazinlab.gestortareasbackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hazinlab.gestortareasbackend.model.Tarea;
import com.hazinlab.gestortareasbackend.repository.TareaRepository;
import com.hazinlab.gestortareasbackend.service.TareaService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TareaServiceTest {

  @Mock
  private TareaRepository tareaRepository;

  @InjectMocks
  private TareaService tareaService;

  private Tarea tarea;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    tarea = new Tarea("Tarea de prueba", false);
    tarea.setId("1");
  }

  @Test
  public void testObtenerTareas() {
    when(tareaRepository.findAll()).thenReturn(Arrays.asList(tarea));

    List<Tarea> tareas = tareaService.obtenerTareas();

    assertEquals(1, tareas.size());
    assertEquals("Tarea de prueba", tareas.get(0).getDescripcion());
  }

  @Test
  public void testAgregarTarea() {
    when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

    Tarea nuevaTarea = tareaService.agregarTarea(tarea);

    assertNotNull(nuevaTarea);
    assertEquals("Tarea de prueba", nuevaTarea.getDescripcion());
  }

  @Test
  public void testEliminarTarea() {
    doNothing().when(tareaRepository).deleteById(tarea.getId());

    tareaService.eliminarTarea(tarea.getId());

    verify(tareaRepository, times(1)).deleteById(tarea.getId());
  }

  @Test
  public void testMarcarCompletada() {
    when(tareaRepository.findById(tarea.getId()))
      .thenReturn(Optional.of(tarea));
    when(tareaRepository.save(tarea)).thenReturn(tarea);

    Tarea tareaCompletada = tareaService.marcarCompletada(tarea.getId());

    assertTrue(tareaCompletada.isCompletada());
    verify(tareaRepository, times(1)).save(tarea);
  }
}
