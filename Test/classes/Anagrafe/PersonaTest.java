package classes.Anagrafe;

import org.junit.*;
import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Classe di test per Persona.
 */
public class PersonaTest {
    /*
     * Metodo che pulisce l'elenco delle persone e resetta il contatore degli ID
     * prima di ogni test.
     */
    @Before
    public void setUp() {
        // pulizia elenco
        ElencoPersone e = ElencoPersone.getInstance();
        while (e.totale() > 0) {
            e.elimina(e.getElemento(0).getID());
        }
        PersonaBuilder.contatoreID = 0;
    }

    /*
     * Verifica che i dati impostati nel builder vengano correttamente
     * restituiti dai metodi getter della classe Persona.
     */
    @Test
    public void testGet() throws Exception {
        PersonaBuilder b = new PersonaBuilder();
        b.setNome("Anna");
        b.setCognome("Bianchi");
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("15/05/1990");
        b.setDataNascita(data);
        b.setCodiceFiscale("BNCHNN90D15H501Z");
        b.setSesso('F');
        b.setIndirizzo("Via Prova 5");
        b.setCAP("50100");
        b.setStatoCivile("Single");
        b.setTelefono("3454444444");

        Persona p = b.getResult();
        assertEquals("Anna", p.getNome());
        assertEquals("Bianchi", p.getCognome());
        assertEquals(data.getTime(), p.getDataNascita().getTime());
        assertEquals('F', p.getSesso());
        assertEquals("BNCHNN90D15H501Z", p.getCodiceFiscale());
        assertEquals("Via Prova 5", p.getIndirizzo());
        assertEquals("50100", p.getCAP());
        assertEquals("Single", p.getStatoCivile());
        assertEquals("3454444444", p.getTelefono());
        assertTrue(p.getID() > 0);
    }
}
