import com.uduran.models.Examen;
import com.uduran.repositories.ExamenRepository;
import com.uduran.repositories.ExamenRepositoryImpl;
import com.uduran.repositories.PreguntasRepository;
import com.uduran.service.ExamenService;
import com.uduran.service.ExamenServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImplTest {

    ExamenRepository repository;
    ExamenService service;
    PreguntasRepository preguntasRepository;

    @BeforeEach
    void setUp(){
        repository = mock(ExamenRepository.class);
        preguntasRepository = mock(PreguntasRepository.class);
        ExamenService service = new ExamenServiceImpl(repository, preguntasRepository);
    }

    @Tag("Junit5")
    @Nested
    class Junit5Tests{
        @Test
        void findExamenPorNombre() {
            Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");
            assertTrue(examen.isPresent());
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
            assertEquals(5L, examen.orElseThrow().getId());
            assertEquals("Matemáticas", examen.orElseThrow().getNombre());
        }

        @Test
        void testPreguntaExamen(){
            when(repository.findAll()).thenReturn(Data.EXAMEN);
            when(preguntasRepository.findPreguntasPorExamenID(5L)).thenReturn(Data.PREGUNTAS);
            Examen examen = service.findExamenPorNombreConPreguntas("Historia");
            assertEquals(4,  examen.getPreguntas().size());
        }
    }
}