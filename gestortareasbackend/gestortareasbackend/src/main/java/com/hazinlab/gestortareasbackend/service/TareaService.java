package com.hazinlab.gestortareasbackend.service;

import com.hazinlab.gestortareasbackend.model.Tarea;
import com.hazinlab.gestortareasbackend.repository.TareaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

  @Autowired
  private TareaRepository tareaRepository;

  public List<Tarea> obtenerTareas() {
    return tareaRepository.findAll();
  }

  public Tarea agregarTarea(Tarea tarea) {
    return tareaRepository.save(tarea);
  }

  public void eliminarTarea(String id) {
    tareaRepository.deleteById(id);
  }

  public Tarea marcarCompletada(String id) {
    Tarea tarea = tareaRepository.findById(id).orElseThrow();
    tarea.setCompletada(true);
    return tareaRepository.save(tarea);
  }
}
