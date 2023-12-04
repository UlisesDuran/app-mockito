package com.uduran.repositories;

import java.util.List;

public interface PreguntasRepository {
    List<String> findPreguntasPorExamenID(Long id);
}
