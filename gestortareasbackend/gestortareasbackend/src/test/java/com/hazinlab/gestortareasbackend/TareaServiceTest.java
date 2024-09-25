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
  private TareaRepository tareaRepository; // Se crea un mock de TareaRepository.

  @InjectMocks
  private TareaService tareaService; // Se inyecta el mock en TareaService.

  private Tarea tarea; // Se define un objeto Tarea para usar en las pruebas.

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this); // Inicializa los mocks.
    tarea = new Tarea("Tarea de prueba", false); // Crea una nueva tarea de prueba.
    tarea.setId("1"); // Establece un ID para la tarea.
  }

  @Test
  public void testObtenerTareas() {
    // Simula que el repositorio devuelve una lista con la tarea de prueba.
    when(tareaRepository.findAll()).thenReturn(Arrays.asList(tarea));

    // Llama al método del servicio que se está probando.
    List<Tarea> tareas = tareaService.obtenerTareas();

    // Verifica que la lista devuelta tenga el tamaño correcto y la descripción esperada.
    assertEquals(1, tareas.size()); // Comprueba que hay 1 tarea.
    assertEquals("Tarea de prueba", tareas.get(0).getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testAgregarTarea() {
    // Simula que el repositorio guarda una tarea y devuelve la tarea de prueba.
    when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

    // Llama al método del servicio que se está probando.
    Tarea nuevaTarea = tareaService.agregarTarea(tarea);

    // Verifica que la tarea agregada no sea nula y que tenga la descripción correcta.
    assertNotNull(nuevaTarea); // Asegura que la nueva tarea no es nula.
    assertEquals("Tarea de prueba", nuevaTarea.getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testEliminarTarea() {
    // Simula que el repositorio elimina una tarea sin lanzar excepciones.
    doNothing().when(tareaRepository).deleteById(tarea.getId());

    // Llama al método del servicio que se está probando.
    tareaService.eliminarTarea(tarea.getId());

    // Verifica que el método del repositorio fue llamado exactamente una vez.
    verify(tareaRepository, times(1)).deleteById(tarea.getId());
  }

  @Test
  public void testMarcarCompletada() {
    // Simula que el repositorio encuentra una tarea por ID.
    when(tareaRepository.findById(tarea.getId()))
      .thenReturn(Optional.of(tarea));
    // Simula que el repositorio guarda la tarea y devuelve la tarea actualizada.
    when(tareaRepository.save(tarea)).thenReturn(tarea);

    // Llama al método del servicio que se está probando.
    Tarea tareaCompletada = tareaService.marcarCompletada(tarea.getId());

    // Verifica que la tarea esté marcada como completada.
    assertTrue(tareaCompletada.isCompletada()); // Asegura que la tarea esté completada.
    // Verifica que el método del repositorio fue llamado exactamente una vez.
    verify(tareaRepository, times(1)).save(tarea);
  }
}
