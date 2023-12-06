package com.uduran.service;

import com.uduran.models.Examen;
import com.uduran.repositories.ExamenRepository;
import com.uduran.repositories.PreguntasRepository;
import com.uduran.service.Data;
import com.uduran.service.ExamenServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {
    @Mock
    ExamenRepository repository;
    @Mock
    PreguntasRepository preguntasRepository;

    @InjectMocks
    ExamenServiceImpl service;

    @BeforeEach
    void setUp(){
        // A esto le llamamos inyeccion de dependencias.
        MockitoAnnotations.openMocks(this);
    }

    @Tag("Junit5")
    @Nested
    class Junit5Tests{
        @Test
        void findExamenPorNombre() {
            Optional<Examen> examen = Data.EXAMEN.stream().filter(e -> e.getNombre().equals("Matemáticas")).findFirst();
            assertTrue(examen.isPresent());
            assertEquals(2L, examen.orElseThrow().getId());
            assertEquals("Matemáticas", examen.orElseThrow().getNombre());
        }
    }

    @Tag("Mockito")
    @Nested
    class MockitoTests{
        @Test
        void findExamenPorNombre() {
            when(repository.findAll()).thenReturn(Data.EXAMEN);
            Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");
            assertTrue(examen.isPresent());
            assertEquals(2L, examen.orElseThrow().getId());
            assertEquals("Matemáticas", examen.orElseThrow().getNombre());
        }

        @Test
        void testPreguntaExamen(){
            when(repository.findAll()).thenReturn(Data.EXAMEN);
            when(preguntasRepository.findPreguntasPorExamenID(anyLong())).thenReturn(Data.PREGUNTAS);
            Examen examen = service.findExamenPorNombreConPreguntas("Historia");
            assertEquals(5,  examen.getPreguntas().size());
            assertTrue(examen.getPreguntas().contains("Aritmetica"));
        }

        @Test
        void testPreguntaExamenVerify(){
            when(repository.findAll()).thenReturn(Data.EXAMEN);
            when(preguntasRepository.findPreguntasPorExamenID(anyLong())).thenReturn(Data.PREGUNTAS);
            Examen examen = service.findExamenPorNombreConPreguntas("Historia");
            assertEquals(5,  examen.getPreguntas().size());
            assertTrue(examen.getPreguntas().contains("Aritmetica"));
            // En este caso el . va fuera de los parentesis.
            verify(repository).findAll();
            verify(preguntasRepository).findPreguntasPorExamenID(anyLong());
        }

        @Test
        void testNoExisteExamenVerify(){
            when(repository.findAll()).thenReturn(Data.EXAMEN);
            when(preguntasRepository.findPreguntasPorExamenID(anyLong())).thenReturn(Data.PREGUNTAS);
            Examen examen = service.findExamenPorNombreConPreguntas("Historia");
            assertNotNull(examen);
            // En este caso el . va fuera de los parentesis.
            verify(repository).findAll();
            verify(preguntasRepository).findPreguntasPorExamenID(anyLong());
        }

        @Test
        void testGuardarExamen() {

            //Given
            Examen newExamen = Data.EXAM;
            newExamen.setPreguntas(Data.PREGUNTAS);

            // Con esto ya vamos incrementando el ID de los examenes emulando un id autoincremental para cuando trabajemos con bases de datos
            // De esta manera no tocamos los ids de las bases de datos.
            when(repository.guardar(any(Examen.class))).then(new Answer<Examen>() {
                Long secuencia = 8L;
                @Override
                public Examen answer(InvocationOnMock invocationOnMock) throws Throwable {
                    Examen examen = invocationOnMock.getArgument(0);
                    examen.setId(secuencia++);
                    return examen;
                }
            });

            //When
            Examen examen = service.guardar(newExamen);

            //Then
            assertNotNull(examen.getId());
            assertEquals(8L, examen.getId());
            assertEquals("Física", examen.getNombre());

            verify(repository).guardar(any(Examen.class));
            verify(preguntasRepository).guardarVarias(anyList());
        }
    }
}