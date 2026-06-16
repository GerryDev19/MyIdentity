package classes.Anagrafe;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Classe di test per ElencoPersone.
 */
public class ElencoPersoneTest {
    private ElencoPersone elenco;

    // Prima di ogni test, ottiene l'istanza singleton ElencoPersone, pulisce l'elenco e resetta il contatore ID
    @Before
    public void setUp() {
        elenco = ElencoPersone.getInstance();
        // Pulisce l'elenco per ogni test
        while (elenco.totale() > 0) {
            elenco.elimina(elenco.getElemento(0).getID());
        }
        PersonaBuilder.contatoreID = 0;
    }

    // Verifica che l'unica istanza venga restituita correttamente.
    @Test
    public void testGetInstance() {
        ElencoPersone elenco1 = ElencoPersone.getInstance();
        ElencoPersone elenco2 = ElencoPersone.getInstance();
        assertSame("Il metodo getInstance() deve ritornare la stessa istanza", elenco1, elenco2);
    }

    // Verifica che una nuova persona venga registrata correttamente nell'elenco.
    @Test
    public void testRegistra() throws Exception {
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Mario");
        builder.setCognome("Rossi");
        Date expectedDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1985");
        builder.setDataNascita(expectedDate);
        builder.setCodiceFiscale("RSSMRA85M01H501Z");
        builder.setSesso('M');
        builder.setIndirizzo("Via Roma 10");
        builder.setCAP("00100");
        builder.setStatoCivile("Coniugato");
        builder.setTelefono("3451234567");
        
        Persona p = builder.getResult();
        assertNotNull("Il metodo getResult() deve ritornare una Persona", p);
        assertEquals("Il nome deve essere Mario", "Mario", p.getNome());
        assertEquals("Il cognome deve essere Rossi", "Rossi", p.getCognome());
        assertNotNull("La data di nascita non deve essere nulla", p.getDataNascita());
        assertEquals("La data di nascita deve corrispondere", expectedDate.getTime(), p.getDataNascita().getTime());
        assertEquals("Il codice fiscale deve corrispondere", "RSSMRA85M01H501Z", p.getCodiceFiscale());
        assertEquals("Il sesso deve essere M", 'M', p.getSesso());
        assertEquals("L'indirizzo deve corrispondere", "Via Roma 10", p.getIndirizzo());
        assertEquals("Il CAP deve essere 00100", "00100", p.getCAP());
        assertEquals("Lo stato civile deve corrispondere", "Coniugato", p.getStatoCivile());
        assertEquals("Il telefono deve corrispondere", "3451234567", p.getTelefono());
    }

    // Verifica che il metodo visualizza() ritorni correttamente i dati anagrafici delle persone registrate.
    @Test
    public void testVisualizza() throws Exception {
        PersonaBuilder builder1 = new PersonaBuilder();
        builder1.setNome("Alice");
        builder1.setCognome("Bianchi");
        builder1.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/04/1990"));
        builder1.setCodiceFiscale("BNCHCL90D15H501Z");
        builder1.setSesso('F');
        builder1.setIndirizzo("Via Milano 5");
        builder1.setCAP("20100");
        builder1.setStatoCivile("Single");
        builder1.setTelefono("3459876543");
        builder1.getResult();

        PersonaBuilder builder2 = new PersonaBuilder();
        builder2.setNome("Bob");
        builder2.setCognome("Verdi");
        builder2.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("12/03/1988"));
        builder2.setCodiceFiscale("VRDBRB88C12L001H");
        builder2.setSesso('M');
        builder2.setIndirizzo("Via Napoli 20");
        builder2.setCAP("80100");
        builder2.setStatoCivile("Coniugato");
        builder2.setTelefono("3441234567");
        builder2.getResult();

        List<Persona> lista = elenco.visualizza();
        assertEquals("visualizza() deve contenere 2 elementi", 2, lista.size());
    }

    // Verifica che il metodo cerca() ritorni correttamente le persone in base al nome cercato.
    @Test
    public void testCercaPerNome() throws Exception{
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Giulia");
        builder.setCognome("Rossi");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/03/1995"));
        builder.setCodiceFiscale("RSSGLL95C15H501Z");
        builder.setSesso('F');
        builder.setIndirizzo("Via Firenze 15");
        builder.setCAP("50100");
        builder.setStatoCivile("Single");
        builder.setTelefono("3451111111");
        builder.getResult();

        List<Persona> risultati = elenco.cerca("Giulia");
        assertEquals("Deve trovare 1 persona", 1, risultati.size());
        assertEquals("Deve trovare Giulia", "Giulia", risultati.get(0).getNome());
    }

    // Verifica che il metodo cerca() ritorni correttamente le persone in base al cognome cercato.
    @Test
    public void testCercaPerCognome() throws Exception {
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Luca");
        builder.setCognome("Ferrari");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1992"));
        builder.setCodiceFiscale("FRRLLC92A01H501Z");
        builder.setSesso('M');
        builder.setIndirizzo("Via Torino 25");
        builder.setCAP("10100");
        builder.setStatoCivile("Single");
        builder.setTelefono("3452222222");
        builder.getResult();

        List<Persona> risultati = elenco.cerca("Ferrari");
        assertEquals("Deve trovare 1 persona", 1, risultati.size());
        assertEquals("Deve trovare Ferrari", "Ferrari", risultati.get(0).getCognome());
    }

    // Verifica che il metodo cerca() sia case-insensitive (non faccia distinzione tra maiuscole e minuscole).
    @Test
    public void testCercaCaseInsensitive() throws Exception {
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Marco");
        builder.setCognome("Rossi");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("12/07/1988"));
        builder.setCodiceFiscale("RSSMRC88C12L001H");
        builder.setSesso('M');
        builder.setIndirizzo("Via Genova 8");
        builder.setCAP("16100");
        builder.setStatoCivile("Coniugato");
        builder.setTelefono("3453333333");
        builder.getResult();

        List<Persona> risultati = elenco.cerca("MARCO");
        assertEquals("Ricerca case-insensitive deve trovare Marco", 1, risultati.size());
    }

    // Verifica che il metodo cerca() ritorni una lista vuota quando non ci sono corrispondenze con la ricerca.
    @Test
    public void testCercaVuoto() throws Exception {
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Anna");
        builder.setCognome("Bianchi");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/05/1990"));
        builder.setCodiceFiscale("BNCHNN90D15H501Z");
        builder.setSesso('F');
        builder.setIndirizzo("Via Palermo 3");
        builder.setCAP("90100");
        builder.setStatoCivile("Single");
        builder.setTelefono("3454444444");
        builder.getResult();

        List<Persona> risultati = elenco.cerca("Zzzzz");
        assertTrue("La ricerca con valore non presente nell'anagrafe deve ritornare lista vuota", risultati.isEmpty());
    }

    // Verifica che il metodo verificaCodiceFiscale() identifichi correttamente i codici fiscali duplicati.
    @Test
    public void testVerificaCodiceFiscale() throws Exception {
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Sofia");
        builder.setCognome("Martini");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("01/02/1991"));
        builder.setCodiceFiscale("MRTSFN91B01H501Z");
        builder.setSesso('F');
        builder.setIndirizzo("Via Ancona 12");
        builder.setCAP("60100");
        builder.setStatoCivile("Single");
        builder.setTelefono("3456666666");
        builder.getResult();

        assertFalse("Non possono esistere nell'anagrafe due persone con lo stesso codice fiscale", elenco.verificaCodiceFiscale("MRTSFN91B01H501Z"));
    }

    // Verifica che il metodo verificaTelefono() identifichi correttamente i numeri di telefono duplicati.
    @Test
    public void testVerificaTelefono() throws Exception {
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Elena");
        builder.setCognome("Rossi");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/08/1988"));
        builder.setCodiceFiscale("RSSELNN88B15H501Z");
        builder.setSesso('F');
        builder.setIndirizzo("Via Brescia 6");
        builder.setCAP("25100");
        builder.setStatoCivile("Single");
        builder.setTelefono("3459999999");
        builder.getResult();

        assertFalse("Non possono esistere nell'anagrafe due persone con lo stesso numero di telefono", elenco.verificaTelefono("3459999999"));
    }

    // Verifica che una persona venga eliminata correttamente dall'elenco.
    @Test
    public void testElimina() throws Exception {
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Francesca");
        builder.setCognome("Bianchi");
        builder.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/06/1990"));
        builder.setCodiceFiscale("BNCHFRN90D15H501Z");
        builder.setSesso('F');
        builder.setIndirizzo("Via Verona 9");
        builder.setCAP("37100");
        builder.setStatoCivile("Single");
        builder.setTelefono("3461111111");
        Persona p = builder.getResult();

        int prima_di_eliminazione = elenco.totale();
        boolean eliminato = elenco.elimina(p.getID());
        
        assertTrue("Elimina deve ritornare true", eliminato);
        assertEquals("Totale deve diminuire di 1", prima_di_eliminazione - 1, elenco.totale());
    }

    // Verifica che il metodo getPersona() ritorni correttamente una persona in base al suo ID, testando anche i vari campi.
    @Test
    public void testGetPersona() throws Exception {
        PersonaBuilder builder = new PersonaBuilder();
        builder.setNome("Valeria");
        builder.setCognome("Rossi");
        Date expectedDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/11/1992");
        builder.setDataNascita(expectedDate);
        builder.setCodiceFiscale("RSSVLR92B01H501Z");
        builder.setSesso('F');
        builder.setIndirizzo("Via Padova 11");
        builder.setCAP("35100");
        builder.setStatoCivile("Single");
        builder.setTelefono("3463333333");
        Persona p = builder.getResult();

        Persona recuperata = elenco.getPersona(p.getID());
        assertNotNull("Il metodo getPersona() deve ritornare un'istanza di tipo Persona", recuperata);
        assertEquals("Il nome deve corrispondere", "Valeria", recuperata.getNome());
        assertEquals("L'ID deve corrispondere", p.getID(), recuperata.getID());
        assertEquals("Il cognome deve corrispondere", "Rossi", recuperata.getCognome());
        assertNotNull("La data di nascita non deve essere nulla", recuperata.getDataNascita());
        assertEquals("La data di nascita deve corrispondere", expectedDate.getTime(), recuperata.getDataNascita().getTime());
        assertEquals("Il codice fiscale deve corrispondere", "RSSVLR92B01H501Z", recuperata.getCodiceFiscale());
        assertEquals("Il sesso deve corrispondere", 'F', recuperata.getSesso());
        assertEquals("L'indirizzo deve corrispondere", "Via Padova 11", recuperata.getIndirizzo());
        assertEquals("Il CAP deve corrispondere", "35100", recuperata.getCAP());
        assertEquals("Lo stato civile deve corrispondere", "Single", recuperata.getStatoCivile());
        assertEquals("Il telefono deve corrispondere", "3463333333", recuperata.getTelefono());
    }

    // Verifica che il metodo modifica() aggiorni correttamente i dati di una persona esistente.
    @Test
    public void testModifica() throws Exception {
        PersonaBuilder builder1 = new PersonaBuilder();
        builder1.setNome("Simone");
        builder1.setCognome("Ferrari");
        builder1.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/09/1988"));
        builder1.setCodiceFiscale("FRRSNN88A15H501Z");
        builder1.setSesso('M');
        builder1.setIndirizzo("Via Modena 14");
        builder1.setCAP("41100");
        builder1.setStatoCivile("Single");
        builder1.setTelefono("3464444444");
        Persona p = builder1.getResult();

        PersonaBuilder builder2 = new PersonaBuilder();
        builder2.setNome("Simone");
        builder2.setCognome("Bianchi");
        builder2.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/09/1988"));
        builder2.setCodiceFiscale("BNCHSNN88A15H501Z");
        builder2.setSesso('M');
        builder2.setIndirizzo("Via Perugia 7");
        builder2.setCAP("06100");
        builder2.setStatoCivile("Coniugato");
        builder2.setTelefono("3465555555");

        Persona modificata = elenco.modifica(p.getID(), builder2);
        assertNotNull("Il metodo modificaPersona deve ritornare l'istanza di tipo Persona aggiornato", modificata);
        assertEquals("Il cognome deve essere aggiornato", "Bianchi", modificata.getCognome());
    }

    // Verifica che il metodo ordina() ordini correttamente le persone in base a diversi criteri.
    @Test
    public void testOrdina() throws Exception {
        PersonaBuilder builder1 = new PersonaBuilder();
        builder1.setNome("Zoe");
        builder1.setCognome("Rossi");
        builder1.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/12/1995"));
        builder1.setCodiceFiscale("RSSZOE95C15H501Z");
        builder1.setSesso('F');
        builder1.setIndirizzo("Via Udine 2");
        builder1.setCAP("33100");
        builder1.setStatoCivile("Single");
        builder1.setTelefono("3470001111");
        builder1.getResult();

        PersonaBuilder builder2 = new PersonaBuilder();
        builder2.setNome("Alice");
        builder2.setCognome("Bianchi");
        builder2.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("15/04/1990"));
        builder2.setCodiceFiscale("BNCHCL90D15H501Z");
        builder2.setSesso('F');
        builder2.setIndirizzo("Via Venezia 4");
        builder2.setCAP("30100");
        builder2.setStatoCivile("Single");
        builder2.setTelefono("3470002222");
        builder2.getResult();

        PersonaBuilder builder3 = new PersonaBuilder();
        builder3.setNome("Mario");
        builder3.setCognome("Verdi");
        builder3.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("12/07/1988"));
        builder3.setCodiceFiscale("VRDMRA88C12L001H");
        builder3.setSesso('M');
        builder3.setIndirizzo("Via Bologna 18");
        builder3.setCAP("40100");
        builder3.setStatoCivile("Coniugato");
        builder3.setTelefono("3470003333");
        builder3.getResult();

        // Test ordinamento per nome
        List<Persona> ordinaPerNome = elenco.ordina("nome");
        assertEquals("Prima deve essere Alice", "Alice", ordinaPerNome.get(0).getNome());
        assertEquals("Poi Mario", "Mario", ordinaPerNome.get(1).getNome());
        assertEquals("Infine Zoe", "Zoe", ordinaPerNome.get(2).getNome());

        // Test ordinamento per cognome
        List<Persona> ordinaPerCognome = elenco.ordina("cognome");
        assertEquals("Prima Bianchi", "Bianchi", ordinaPerCognome.get(0).getCognome());
        assertEquals("Poi Rossi", "Rossi", ordinaPerCognome.get(1).getCognome());
        assertEquals("Infine Verdi", "Verdi", ordinaPerCognome.get(2).getCognome());
    }
}
