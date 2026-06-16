package classes.StruttureDati;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;

/*
 * Classe di test per Elenco.
 */
public class ElencoTest {
    /*  
     *  Classe di esempio concreta che estende Elenco per testare i metodi astratti.
     */
    private static class ElencoStringEsempio extends Elenco<String> {
        // Implementazione del metodo astratto visualizza().
        public List<String> visualizza() {
            return getElenco();
        }
    }

    private Elenco<String> elenco;

    /*
     * Metodo che inizializza un oggetto ElencoStringEsempio prima di ogni test.
     */
    @Before
    public void setUp() {
        elenco = new ElencoStringEsempio();
    }

    // Verifica che un elemento venga aggiunto correttamente all'elenco.
    @Test
    public void testAggiungiElemento() {
        assertTrue("Deve aggiungere un elemento", elenco.aggiungi("Mario"));
        assertEquals("Totale deve essere 1", 1, elenco.totale());
    }

    // Verifica che il metodo totale() restituisca il numero corretto di elementi nell'elenco.
    @Test
    public void testTotale() {
        for (int i = 0; i < 5; i++) {
            elenco.aggiungi("Elemento" + i);
        }
        assertEquals("Totale deve essere 5", 5, elenco.totale());
    }

    // Verifica che il metodo getElenco() restituisca una copia corretta dell'elenco degli elementi.
    @Test
    public void testGetElenco() {
        elenco.aggiungi("Alice");
        elenco.aggiungi("Bob");
        List<String> lista = elenco.getElenco();
        
        assertTrue("Deve contenere Alice", lista.contains("Alice"));
        assertTrue("Deve contenere Bob", lista.contains("Bob"));
        assertEquals("Deve avere 2 elementi", 2, lista.size());
    }

    // Verifica che il metodo getElemento() restituisca l'elemento corretto in base all'indice.
    @Test
    public void testGetElemento() {
        elenco.aggiungi("Primo");
        elenco.aggiungi("Secondo");
        elenco.aggiungi("Terzo");
        
        assertEquals("Index 0 deve ritornare Primo", "Primo", elenco.getElemento(0));
        assertEquals("Index 1 deve ritornare Secondo", "Secondo", elenco.getElemento(1));
        assertEquals("Index 2 deve ritornare Terzo", "Terzo", elenco.getElemento(2));
    }
}
