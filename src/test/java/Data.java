import com.uduran.models.Examen;

import java.util.Arrays;
import java.util.List;

public class Data {
    public final static List<Examen> EXAMEN = Arrays.asList(new Examen(1L, "Historia"),
            new Examen(2L, "Matemáticas"),
            new Examen(3L, "Lenguaje"),
            new Examen(4L, "Filosofía"),
            new Examen(5L, "Tecnología"));
    public final static List<String> PREGUNTAS = Arrays.asList("Aritmetica","Integrales","Derivadas","Trigonometria","geometria");
}
