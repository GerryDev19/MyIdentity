package classes.Accesso;

import org.junit.*;
import static org.junit.Assert.*;

import classes.Anagrafe.ElencoPersone;

/*
 * Classe di test per Amministratore.
 */
public class AmministratoreTest {
    private Amministratore a;
    private ElencoPersone elenco;

    /*
     * Metodo che inizializza un oggetto Amministratore e svuota l'elenco delle persone
     * prima di ogni test.
     */
    @Before
    public void setUp() {
        elenco = ElencoPersone.getInstance();
        while (elenco.totale() > 0) {
            elenco.elimina(elenco.getElemento(0).getID());
        }
        a = new Amministratore("admin","pwd");
    }

    /*
     * Verifica che lo username sia correttamente validato, impostato e restituito dal metodo getUsername().
     */
    @Test
    public void testSetUsername() {
        assertFalse(a.setUsername(null));
        assertFalse(a.setUsername("   "));
        assertTrue(a.setUsername("nuovoutente"));
        assertEquals("nuovoutente", a.getUsername());
    }

    // Verifica che la password sia correttamente validata, impostata e autenticata.
    @Test
    public void testAutentica() {
        assertFalse(a.setPassword(null));
        assertFalse(a.autentica(null));
        assertTrue(a.autentica("pwd"));
        assertTrue(a.setPassword("newpass"));
        assertTrue(a.autentica("newpass"));
    }

    /* 
     * Verifica che una nuova istanza di tipo Persona venga creata e aggiunta
     *correttamente nell'elenco delle persone.
     */
    @Test
    public void testNuovaPersona() {
        String dati = "Mario,Rossi,01/05/1985,RSSMRA85M01H501Z,M,Via Roma 10,00100,Coniugato,3451234567";
        int prima = elenco.totale();
        assertTrue(a.nuovaPersona(dati));
        assertEquals(prima + 1, elenco.totale());
    }

    /*
     * Verifica che il metodo nuovaPersona lanci un'eccezione
     * quando i dati forniti non sono validi.
     */
    @Test
    public void testNuovaPersonaException() {
        // IllegalArgumentException
        String dati = "Mario,Rossi,01/05/1985";
        /*
        *   () -> a.nuovaPersona(dati): "lambda expression", cioè funzione anonima
        *   "()" → nessun parametro; "->" → "esegui"
        */
        assertThrows(Exception.class, () -> a.nuovaPersona(dati));
    }
}
