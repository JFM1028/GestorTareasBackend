package com.hazinlab.gestortareasbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tareas")
public class Tarea {

  @Id
  private String id;

  private String descripcion;
  private boolean completada;

  // Constructor vacío (necesario para deserialización)
  public Tarea() {}

  // Constructor con parámetros
  public Tarea(String descripcion, boolean completada) {
    this.descripcion = descripcion;
    this.completada = completada;
  }

  // Getters y Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public boolean isCompletada() {
    return completada;
  }

  public void setCompletada(boolean completada) {
    this.completada = completada;
  }
}
