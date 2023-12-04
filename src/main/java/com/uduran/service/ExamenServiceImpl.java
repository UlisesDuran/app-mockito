package com.uduran.service;

import com.uduran.models.Examen;
import com.uduran.repositories.ExamenRepository;
import com.uduran.repositories.PreguntasRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements ExamenService{
    private ExamenRepository examenRepositorio;
    private PreguntasRepository preguntasRepository;

    public ExamenServiceImpl(ExamenRepository examenRepositorio, PreguntasRepository preguntasRepository) {
        this.examenRepositorio = examenRepositorio;
        this.preguntasRepository = preguntasRepository;
    }
    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
        return examenRepositorio.findAll().stream().filter(e -> e.getNombre().equals(nombre)).findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> examenOptional = findExamenPorNombre(nombre);
        Examen examen = null;
        if (examenOptional.isPresent()){
            examen = examenOptional.orElseThrow();
            List<String> preguntas = preguntasRepository.findPreguntasPorExamenID(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }
}