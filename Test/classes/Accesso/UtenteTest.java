package classes.Accesso;

import org.junit.*;
import static org.junit.Assert.*;

import classes.Anagrafe.Persona;
import classes.Anagrafe.PersonaBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Classe di test per Utente.
 */
public class UtenteTest {
    private Persona persona;
    private Utente utente;

    /*
     * Metodo che inizializza un oggetto Utente resettando il contatore della classe e
     * creando un'istanza di prova di tipo Persona prima di ogni test.
     */
    @Before
    public void setUp() throws Exception {
        Account.contatoreID = 0;
        PersonaBuilder b = new PersonaBuilder();
        b.setNome("Luca");
        b.setCognome("Rossi");
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1990");
        b.setDataNascita(data);
        b.setCodiceFiscale("RSSLUC90A01H501Z");
        b.setSesso('M');
        b.setIndirizzo("Via Roma 1");
        b.setCAP("00100");
        b.setStatoCivile("Single");
        b.setTelefono("3450001111");
        this.persona = b.getResult();

        this.utente = new Utente("user", "pwd", this.persona);
    }

    /*
     * Verifica che i dati anagrafici e lo username siano correttamente
     * restituiti dai rispettivi metodi datiAnagrafici() e getUsername().
     */
    @Test
    public void testDatiAnagrafici() {
        assertSame(persona, utente.datiAnagrafici());
        assertEquals("user", utente.getUsername());
    }

    /*
     * Verifica che il metodo setUsername aggiorni correttamente lo username
     * rimuovendo gli spazi iniziali e finali.
     */
    @Test
    public void testSetUsername() {
        boolean nuovoValore = utente.setUsername("  newuser  ");
        assertTrue(nuovoValore);
        assertEquals("newuser", utente.getUsername());
    }

    /*
     * Verifica che il metodo setPassword aggiorni correttamente la password
     * e che la nuova password sia utilizzabile per l'autenticazione.
     */
    @Test
    public void testSetPassword() {
        boolean nuovoValore = utente.setPassword("newpwd");
        assertTrue(nuovoValore);
        assertTrue(utente.autentica("newpwd"));
    }

    /*
     * Verifica che il metodo autentica funzioni correttamente
     * per password valide e non valide.
     */
    @Test
    public void testAutentica() {
        assertTrue(utente.autentica("pwd"));
        assertFalse(utente.autentica("prova"));
        assertFalse(utente.autentica(null));
    }

}
