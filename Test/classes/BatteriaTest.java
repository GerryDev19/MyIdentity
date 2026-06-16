package classes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * Classe che raggruppa tutti i test in un'unica suite eseguibile.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    classes.Accesso.UtenteTest.class,
    classes.Accesso.AmministratoreTest.class,
    classes.StruttureDati.ElencoTest.class,
    classes.Anagrafe.ElencoPersoneTest.class,
    classes.Anagrafe.PersonaTest.class,
    classes.Anagrafe.PersonaBuilderTest.class,
})
public class BatteriaTest {
    // Classe vuota: serve solo per raggruppare la suite di test
}
