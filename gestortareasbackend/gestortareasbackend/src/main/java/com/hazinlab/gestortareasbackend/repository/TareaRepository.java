package com.hazinlab.gestortareasbackend.repository;

import com.hazinlab.gestortareasbackend.model.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TareaRepository extends MongoRepository<Tarea, String> {}
