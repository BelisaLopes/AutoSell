import modelo.Categoria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CategoriaTestCase {

    @Test
    public void testCreateCategoria() {
        var categoria = new Categoria("Parafusos");
        assertEquals("Parafusos", categoria.getNome());
    }

    @Test
    public void testCreateCategoriaNotEquals() {
        var categoria = new Categoria("Parafusos");
        assertNotEquals("Motores", categoria.getNome());
    }

}