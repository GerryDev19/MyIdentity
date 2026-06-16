package classes.Accesso;

/*
 * Classe astratta che definisce un account generico che consente l'accesso al sistema.
 */
public abstract class Account {
    static int contatoreID = 0;
    private int ID;

    // Costruttore che assegna un ID univoco all'account grazie al contatore statico contatoreID.
    Account() {
        this.ID = contatoreID++;
    }

    // La password da verificare deve essere cifrata prima di essere passata a questo metodo.
    abstract boolean autentica(String password);

    // Metodo per mostrare messaggi all'utente.
    public void mostraMessaggio(String messaggio) {
        System.out.println(messaggio);
    }

    // Restituisce l'ID univoco dell'account.
    public int getID() {
        return ID;
    }
}
