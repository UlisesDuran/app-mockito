import com.uduran.models.Examen;
import com.uduran.repositories.ExamenRepository;
import com.uduran.repositories.ExamenRepositoryImpl;
import com.uduran.repositories.PreguntasRepository;
import com.uduran.service.ExamenService;
import com.uduran.service.ExamenServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
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
            Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");
            //assertTrue(examen.isPresent());
            assertEquals(5L, examen.orElseThrow().getId());
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

    }
}