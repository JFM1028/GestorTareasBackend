package com.hazinlab.gestortareasbackend.controller;

import com.hazinlab.gestortareasbackend.model.Tarea;
import com.hazinlab.gestortareasbackend.service.TareaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

  @Autowired
  private TareaService tareaService;

  @GetMapping
  public List<Tarea> obtenerTareas() {
    return tareaService.obtenerTareas();
  }

  @PostMapping
  public Tarea agregarTarea(@RequestBody Tarea tarea) {
    return tareaService.agregarTarea(tarea);
  }

  @PutMapping("/{id}/completar")
  public Tarea marcarCompletada(@PathVariable String id) {
    return tareaService.marcarCompletada(id);
  }

  @DeleteMapping("/{id}")
  public void eliminarTarea(@PathVariable String id) {
    tareaService.eliminarTarea(id);
  }
}
