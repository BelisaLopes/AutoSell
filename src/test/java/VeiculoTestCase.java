import modelo.Cliente;
import modelo.Data;
import modelo.TipoCombustivel;
import modelo.Veiculo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VeiculoTestCase {

    @Test public void testCreateVeiculo() {

        var veiculo = new Veiculo("Opel", "Corsa", 2001, "AA-00-AA", "Branco",
                3, TipoCombustivel.GASOLINA, 100000,1, "Bom",
                10000);
        assertEquals("Opel", veiculo.getMarca());
        assertEquals("Corsa", veiculo.getModelo());
        assertEquals(2001, veiculo.getAno());
        assertEquals("AA-00-AA", veiculo.getMatricula());
        assertEquals("Branco", veiculo.getCor());
        assertEquals(3, veiculo.getNumeroPortas());
        assertEquals(TipoCombustivel.GASOLINA, veiculo.getCombustivel());
        assertEquals(100000, veiculo.getQuilometros());
        assertEquals(1, veiculo.getNumeroDonos());
        assertEquals("Bom", veiculo.getCondicaoVeiculo());
        assertEquals(10000, veiculo.getValorVeiculo());
    }

    @Test public void testCreateVeiculoNotEquals() {
        var veiculo = new Veiculo("Opel", "Corsa", 2001, "AA-00-AA", "Branco",
                3, TipoCombustivel.GASOLINA, 100000,1, "Bom",
                10000);
        assertNotEquals("Suzuki", veiculo.getMarca());
        assertNotEquals("Swift", veiculo.getModelo());
        assertNotEquals(2010, veiculo.getAno());
        assertNotEquals("AA-00-CC", veiculo.getMatricula());
        assertNotEquals("Azul", veiculo.getCor());
        assertNotEquals(5, veiculo.getNumeroPortas());
        assertNotEquals(TipoCombustivel.GASOLEO, veiculo.getCombustivel());
        assertNotEquals(150000, veiculo.getQuilometros());
        assertNotEquals(3, veiculo.getNumeroDonos());
        assertNotEquals("Usado", veiculo.getCondicaoVeiculo());
        assertNotEquals(15000, veiculo.getValorVeiculo());
    }
}
