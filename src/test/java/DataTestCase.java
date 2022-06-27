import modelo.Data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class DataTestCase {

    @Test
    public void testCreateData() {
        var data = new Data(26,6,2022);
        assertEquals(26, data.getDia());
        assertEquals(6, data.getMes());
        assertEquals(2022, data.getAno());
    }

}