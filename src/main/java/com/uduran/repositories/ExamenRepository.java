package com.uduran.repositories;

import com.uduran.models.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> findAll();
}