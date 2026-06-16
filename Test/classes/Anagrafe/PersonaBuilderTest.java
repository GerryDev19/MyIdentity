package classes.Anagrafe;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Classe di test per PersonaBuilder.
 */
public class PersonaBuilderTest {
    private PersonaBuilder builder;
    private ElencoPersone elenco;

    /*
     * Metodo che inizializza un oggetto PersonaBuilder e pulisce l'elenco delle persone
     * prima di ogni test.
     */
    @Before
    public void setUp() {
        builder = new PersonaBuilder();
        elenco = ElencoPersone.getInstance();
        // Pulisce l'elenco per ogni test
        while (elenco.totale() > 0) {
            elenco.elimina(elenco.getElemento(0).getID());
        }
        PersonaBuilder.contatoreID = 0;
    }

    // Test del metodo setNome() con vari casi di input.
    @Test
    public void testSetNome() {
        boolean result = builder.setNome("Mario");
        assertTrue("Il metodo setNome() con nome valido deve ritornare true", result);
        assertEquals("Il nome deve essere Mario", "Mario", builder.getNome());
    }

    @Test
    public void testSetNomeNull() {
        boolean result = builder.setNome(null);
        assertFalse("Il metodo setNome() con null deve ritornare false", result);
        assertNull("Il nome deve rimanere null", builder.getNome());
    }

    @Test
    public void testSetNomeConNumeri() {
        boolean result = builder.setNome("Mario123");
        assertFalse("Il metodo setNome() con numeri deve ritornare false", result);
        assertNull("Il nome deve rimanere null", builder.getNome());
    }

    @Test
    public void testSetNomeConSpazi() {
        boolean result = builder.setNome("Maria Rosa");
        assertTrue("Il metodo setNome() con spazi tra nomi deve ritornare true", result);
        assertEquals("Il nome deve essere 'Maria Rosa'", "Maria Rosa", builder.getNome());
    }

    // Test del metodo setCognome() con vari casi di input.
    @Test
    public void testSetCognome() {
        boolean result = builder.setCognome("Rossi");
        assertTrue("Il metodo setCognome() con cognome valido deve ritornare true", result);
        assertEquals("Il cognome deve essere Rossi", "Rossi", builder.getCognome());
    }

    @Test
    public void testSetCognomeConNumeri() {
        boolean result = builder.setCognome("Rossi123");
        assertFalse("Il metodo setCognome() con numeri deve ritornare false", result);
        assertNull("Il cognome deve rimanere null", builder.getCognome());
    }

    // Verifica del metodo setDataNascita() con data valida.
    @Test
    public void testSetDataNascita() throws Exception {
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1985");
        boolean result = builder.setDataNascita(data);
        assertTrue("Il metodo setDataNascita() con data valida deve ritornare true", result);
        assertNotNull("La data di nascita non deve essere nulla", builder.getDataNascita());
        assertEquals("La data deve corrispondere", data.getTime(), builder.getDataNascita().getTime());
    }

    // Verifica del metodo setSesso() con valori validi.
    @Test
    public void testSetSesso() {
        boolean resultM = builder.setSesso('M');
        assertTrue("Il metodo setSesso('M') deve ritornare true", resultM);
        assertEquals("Il sesso deve essere M", 'M', builder.getSesso());

        builder = new PersonaBuilder();
        boolean resultF = builder.setSesso('F');
        assertTrue("Il metodo setSesso('F') deve ritornare true", resultF);
        assertEquals("Il sesso deve essere F", 'F', builder.getSesso());
    }

    // Verifica del metodo setCodiceFiscale() con vari casi di input.
    @Test
    public void testSetCodiceFiscale() {
        boolean result = builder.setCodiceFiscale("RSSMRA85M01H501Z");
        assertTrue("Il metodo setCodiceFiscale() con codice valido deve ritornare true", result);
        assertEquals("Il codice fiscale deve essere RSSMRA85M01H501Z", "RSSMRA85M01H501Z", builder.getCodiceFiscale());
    }

    @Test
    public void testSetCodiceFiscaleLunghezzaNonValida() {
        boolean result = builder.setCodiceFiscale("RSSMRA85M01H50");
        assertFalse("Il metodo setCodiceFiscale() con lunghezza < 16 deve ritornare false", result);
        assertNull("Il codice fiscale deve rimanere null", builder.getCodiceFiscale());
    }

    @Test
    public void testSetCodiceFiscaleCaratteriNonValidi() {
        boolean result = builder.setCodiceFiscale("RSSMRA85M01H50-Z");
        assertFalse("Il metodo setCodiceFiscale() con caratteri invalidi deve ritornare false", result);
        assertNull("Il codice fiscale deve rimanere null", builder.getCodiceFiscale());
    }

    // Verifica del metodo setCAP() con vari casi di input.
    @Test
    public void testSetCAP() {
        boolean result = builder.setCAP("00100");
        assertTrue("Il metodo setCAP() con CAP valido deve ritornare true", result);
        assertEquals("Il CAP deve essere 00100", "00100", builder.getCAP());
    }

    @Test
    public void testSetCAPLunghezzaNonValida() {
        boolean result = builder.setCAP("001");
        assertFalse("Il metodo setCAP() con lunghezza < 5 deve ritornare false", result);
        assertNull("Il CAP deve rimanere null", builder.getCAP());
    }

    @Test
    public void testSetCAPConLettere() {
        boolean result = builder.setCAP("0010A");
        assertFalse("Il metodo setCAP() con caratteri non numerici deve ritornare false", result);
        assertNull("Il CAP deve rimanere null", builder.getCAP());
    }

    // Verifica del metodo setStatoCivile() con input valido.
    @Test
    public void testSetStatoCivile() {
        boolean result = builder.setStatoCivile("Coniugato");
        assertTrue("Il metodo setStatoCivile() con valore valido deve ritornare true", result);
        assertEquals("Lo stato civile deve essere Coniugato", "Coniugato", builder.getStatoCivile());
    }

    // Verifica del metodo setTelefono() con vari casi di input.
    @Test
    public void testSetTelefono() {
        boolean result = builder.setTelefono("3451234567");
        assertTrue("Il metodo setTelefono() con telefono valido deve ritornare true", result);
        assertEquals("Il telefono deve essere 3451234567", "3451234567", builder.getTelefono());
    }

    @Test
    public void testSetTelefonoLunghezzaNonValida() {
        boolean result = builder.setTelefono("345");
        assertFalse("Il metodo setTelefono() con lunghezza < 6 deve ritornare false", result);
        assertNull("Il telefono deve rimanere null", builder.getTelefono());
    }

    @Test
    public void testSetTelefonoConLettere() {
        boolean result = builder.setTelefono("345ABC1234");
        assertFalse("Il metodo setTelefono() con lettere deve ritornare false", result);
        assertNull("Il telefono deve rimanere null", builder.getTelefono());
    }

    // Verifica del metodo reset().
    @Test
    public void testReset() throws Exception {
        builder.setNome("Mario");
        builder.setCognome("Rossi");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1985"));
        builder.setCodiceFiscale("RSSMRA85M01H501Z");
        builder.setSesso('M');
        builder.setIndirizzo("Via Roma 10");
        builder.setCAP("00100");
        builder.setStatoCivile("Coniugato");
        builder.setTelefono("3451234567");

        boolean result = builder.reset();
        assertTrue("Il metodo reset() deve ritornare true", result);
        assertNull("Il nome deve essere null dopo reset", builder.getNome());
        assertNull("Il cognome deve essere null dopo reset", builder.getCognome());
        assertNull("La data di nascita deve essere null dopo reset", builder.getDataNascita());
        assertNull("Il codice fiscale deve essere null dopo reset", builder.getCodiceFiscale());
        assertEquals("Il sesso deve essere null dopo reset", '\0', builder.getSesso());
        assertNull("L'indirizzo deve essere null dopo reset", builder.getIndirizzo());
        assertNull("Il CAP deve essere null dopo reset", builder.getCAP());
        assertNull("Lo stato civile deve essere null dopo reset", builder.getStatoCivile());
        assertNull("Il telefono deve essere null dopo reset", builder.getTelefono());
    }

    // Verifica del metodo getResult().
    @Test
    public void testGetResult() throws Exception {
        builder.setNome("Mario");
        builder.setCognome("Rossi");
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1985");
        builder.setDataNascita(data);
        builder.setCodiceFiscale("RSSMRA85M01H501Z");
        builder.setSesso('M');
        builder.setIndirizzo("Via Roma 10");
        builder.setCAP("00100");
        builder.setStatoCivile("Coniugato");
        builder.setTelefono("3451234567");

        Persona p = builder.getResult();
        assertNotNull("Il metodo getResult() deve ritornare una Persona non null", p);
        assertEquals("Il nome deve essere Mario", "Mario", p.getNome());
        assertEquals("Il cognome deve essere Rossi", "Rossi", p.getCognome());
        assertEquals("La data deve corrispondere", data.getTime(), p.getDataNascita().getTime());
        assertEquals("Il codice fiscale deve essere RSSMRA85M01H501Z", "RSSMRA85M01H501Z", p.getCodiceFiscale());
        assertEquals("Il sesso deve essere M", 'M', p.getSesso());
        assertEquals("L'indirizzo deve essere Via Roma 10", "Via Roma 10", p.getIndirizzo());
        assertEquals("Il CAP deve essere 00100", "00100", p.getCAP());
        assertEquals("Lo stato civile deve essere Coniugato", "Coniugato", p.getStatoCivile());
        assertEquals("Il telefono deve essere 3451234567", "3451234567", p.getTelefono());
        assertTrue("L'ID deve essere assegnato e > 0", p.getID() > 0);
    }

    // Verifica che la Persona creata venga registrata correttamente in ElencoPersone.
    @Test
    public void testGetResultRegistraInElenco() throws Exception {
        builder.setNome("Mario");
        builder.setCognome("Rossi");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1985"));
        builder.setCodiceFiscale("RSSMRA85M01H501Z");
        builder.setSesso('M');
        builder.setIndirizzo("Via Roma 10");
        builder.setCAP("00100");
        builder.setStatoCivile("Coniugato");
        builder.setTelefono("3451234567");

        int totalePrima = elenco.totale();
        Persona p = builder.getResult();
        int totaleDopoCreazione = elenco.totale();

        assertEquals("Il totale deve aumentare di 1", totalePrima + 1, totaleDopoCreazione);
        Persona recuperata = elenco.getPersona(p.getID());
        assertNotNull("La Persona deve essere registrata in ElencoPersone", recuperata);
        assertEquals("La Persona recuperata deve avere lo stesso ID", p.getID(), recuperata.getID());
    }

    // Verifica che il contatore degli ID venga incrementato correttamente.
    @Test
    public void testContatoreIDIncremento() throws Exception {
        builder.setNome("Mario");
        builder.setCognome("Rossi");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1985"));
        builder.setCodiceFiscale("RSSMRA85M01H501Z");
        builder.setSesso('M');
        builder.setIndirizzo("Via Roma 10");
        builder.setCAP("00100");
        builder.setStatoCivile("Coniugato");
        builder.setTelefono("3451234567");

        Persona p1 = builder.getResult();
        int id1 = p1.getID();

        PersonaBuilder builder2 = new PersonaBuilder();
        builder2.setNome("Luigi");
        builder2.setCognome("Verdi");
        builder2.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("12/03/1988"));
        builder2.setCodiceFiscale("VRDLGU88C12L001H");
        builder2.setSesso('M');
        builder2.setIndirizzo("Via Napoli 20");
        builder2.setCAP("80100");
        builder2.setStatoCivile("Single");
        builder2.setTelefono("3441234567");

        Persona p2 = builder2.getResult();
        int id2 = p2.getID();

        assertTrue("L'ID della seconda Persona deve essere maggiore della prima", id2 > id1);
    }
}
