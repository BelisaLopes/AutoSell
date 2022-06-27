import modelo.Categoria;
import modelo.Peca;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PecaTestCase {

    @Test
    public void testCreatePeca() {
        var peca = new Peca("Parafuso", "Del", "Parafuso de aço", "1,5", 10.0, new Categoria("Parafusos"));
        assertEquals("Parafuso", peca.getNome());
        assertEquals("Del", peca.getMarca());
        assertEquals("Parafuso de aço", peca.getModelo());
        assertEquals("1,5", peca.getDimensao());
        assertEquals(10.0, peca.getPreco());
        assertEquals("Parafusos", peca.getCategoria().getNome());
    }

    @Test
    public void testCreatePecaNotEquals() {
        var peca = new Peca("Parafuso", "Parafusos", "Parafuso de aço", "1,5", 10.0, new Categoria("Parafusos"));
        assertNotEquals("Chave de fendas", peca.getNome());
        assertNotEquals("Abc", peca.getMarca());
        assertNotEquals("Chaves", peca.getModelo());
        assertNotEquals("15", peca.getDimensao());
        assertNotEquals(15.0, peca.getPreco());
        assertNotEquals("Motores", peca.getCategoria().getNome());
    }

}