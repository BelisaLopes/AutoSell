import modelo.Cliente;
import modelo.DadosAplicacao;
import modelo.Data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTestCase {

    @Test
    public void testCreateCliente() {
        var cliente = new Cliente("Joana", "Rua do verde", new Data(1,1,2002), "122222222", "912345678");
        assertEquals("Joana", cliente.getNome());
        assertEquals("Rua do verde", cliente.getMorada());
        assertEquals(new Data(1,1,2002), cliente.getDataNascimento());
        assertEquals("122222222", cliente.getNIF());
        assertEquals("912345678", cliente.getContacto());
    }

    @Test public void testCreateClienteNotEquals() {
        var cliente = new Cliente("Joana", "Rua do verde", new Data(1,1,2002), "122222222", "912345678");
        assertNotEquals("Alice", cliente.getNome());
        assertNotEquals("Rua do pinho", cliente.getMorada());
        assertNotEquals(new Data(17,8,2000), cliente.getDataNascimento());
        assertNotEquals("144444444", cliente.getNIF());
        assertNotEquals("918967415", cliente.getContacto());
    }

    @Test public void testAdicionarCliente() {
        var cliente = new Cliente("Joana", "Rua do verde", new Data(1,1,2002), "122222222", "912345678");
        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        dados.adicionarCliente(cliente);
        assertEquals(3, dados.size());
    }
}
