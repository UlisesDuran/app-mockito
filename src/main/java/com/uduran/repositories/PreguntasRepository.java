package com.uduran.repositories;

import java.util.List;

public interface PreguntasRepository {

    void guardarVarias(List<String> preguntas);
    List<String> findPreguntasPorExamenID(Long id);
}