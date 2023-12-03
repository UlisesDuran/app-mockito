package com.uduran.service;

import com.uduran.models.Examen;
import com.uduran.repositories.ExamenRepositorio;

import java.util.Optional;

public class ExamenServiceImpl implements ExamenService{
    private ExamenRepositorio examenRepositorio;

    public ExamenServiceImpl(ExamenRepositorio examenRepositorio) {
        this.examenRepositorio = examenRepositorio;
    }
    @Override
    public Examen findExamenPorNombre(String nombre) {
        Optional<Examen> examenOptional = examenRepositorio.findAll().stream().filter(e -> e.getNombre().equals(nombre)).findFirst();
        Examen examen = null;
        if (examenOptional.isPresent()){
            examen = examenOptional.orElseThrow();
        }
        return examen;
    }
}
