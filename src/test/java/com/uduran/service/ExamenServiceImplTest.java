package com.uduran.service;

import com.uduran.models.Examen;
import com.uduran.repositories.ExamenRepositorio;
import com.uduran.repositories.ExamenRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImplTest {

    @Test
    void findExamenPorNombre() {
        ExamenRepositorio repositorio = new ExamenRepositoryImpl();
        ExamenService service = new ExamenServiceImpl(repositorio);
        Examen examen = service.findExamenPorNombre("Matematicas");
        assertNotNull(examen);
        assertEquals(5L, examen.getId());
        assertEquals("Matematicas", examen.getNombre());
    }
}