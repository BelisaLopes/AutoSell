import modelo.Oficina;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class OficinaTestCase {

    @Test public void testCreateOficina() {
        var oficina = new Oficina();
        assertNotNull(oficina);
    }

}