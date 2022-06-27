import modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class EventoTestCase {

    @BeforeEach
    public void setUp() {
        System.out.println("Teste Unitário - Eventos");
    }

    @Test
    public void testCreateEvento() {
        var evento = new Evento(Distrito.LEIRIA, "Feira de Maio", new Data(26,6,2022), new Data(26,6,2022));
        assertEquals("Feira de Maio", evento.getNome());
        assertEquals( new Data(26,6,2022), evento.getDataInicio());
        assertEquals(new Data(26,6,2022), evento.getDataFim());
    }

    @Test
    public void testCreateEventoLocal() {
        var evento = new Evento(Distrito.AVEIRO, "Feira de Maio", new Data(26,6,2022), new Data(26,6,2022));
        assertEquals(Distrito.AVEIRO, evento.getDistrito());
    }

    @Test
    public void testCreateEventoNotEquals() {
        var evento = new Evento(Distrito.LEIRIA, "Feira de Maio", new Data(26,6,2022), new Data(26,6,2022));
        assertNotEquals("Feira Óbidos", evento.getNome());
        assertNotEquals( new Data(14,5,2021), evento.getDataInicio());
        assertNotEquals(new Data(16,5,2021), evento.getDataFim());
    }

    @Test
    public void testCreateEventoEstabelecimento() {
        var evento = new Evento(Distrito.LEIRIA, "Exposição da sede", new Data(26,6,2022), new Data(26,6,2022), new Sede(Distrito.LISBOA, 4500));
        assertEquals("Exposição da sede", evento.getNome());
        assertEquals( new Data(26,6,2022), evento.getDataInicio());
        assertEquals(new Data(26,6,2022), evento.getDataFim());
        assertEquals(new Sede(Distrito.LISBOA, 4500), evento.getEstabelecimento());
    }

    @Test
    public void testCreateEventoEstabelecimentoNotEquals() {
        var evento = new Evento(Distrito.LEIRIA, "Exposição da sede", new Data(26,6,2022), new Data(26,6,2022), new Sede(Distrito.LISBOA, 4500));
        assertNotEquals("Feira de Maio", evento.getNome());
        assertNotEquals( new Data(21,5,2021), evento.getDataInicio());
        assertNotEquals(new Data(26,5,2021), evento.getDataFim());
        assertNotEquals(new Sede(Distrito.LEIRIA, 5500), evento.getEstabelecimento());
    }

}
