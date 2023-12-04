package com.uduran.repositories;

import com.uduran.models.Examen;

import java.util.Arrays;
import java.util.List;

public class ExamenRepositoryImpl implements ExamenRepository {
    @Override
    public List<Examen> findAll() {
        return Arrays.asList(new Examen(1L, "Historia"),
                new Examen(2L, "Matemáticas"),
                new Examen(3L, "Lenguaje"),
                new Examen(4L, "Filosofía"),
                new Examen(5L, "Tecnología"));
    }
}