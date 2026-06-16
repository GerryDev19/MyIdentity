package classes.Accesso;

import classes.Anagrafe.Persona;

/*
 * Classe che definisce un utente del sistema.
 */
public class Utente extends Account {
    private Persona persona;
    private String username;
    private String password; // La password deve essere memorizzata in forma cifrata

    // Costruttore che inizializza username, password e associa un'istanza di tipo Persona all'utente.
    public Utente(String username, String password, Persona p) {
        super();
        this.username = username;
        this.password = password;
        this.persona = p;
    }

    // Restituisce i dati anagrafici associati all'utente.
    public Persona datiAnagrafici() {
        return this.persona;
    }

    // Restituisce lo username dell'utente.
    public String getUsername() {
        return this.username;
    }

    /*
     * Imposta un nuovo username per l'utente, rimuovendo eventuali spazi iniziali
     * e finali e verificando che il campo non sia vuoto.
     */
    public boolean setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        this.username = username.trim();
        return true;
    }

    /*
     * Imposta una nuova password per l'utente, rimuovendo eventuali spazi iniziali
     * e finali e verificando che il campo non sia vuoto.
     */
    public boolean setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        this.password = password.trim();
        return true;
    }

    // Verifica se la password fornita corrisponde a quella memorizzata per l'utente.
    public boolean autentica(String password) {
        if (password == null) {
            return false;
        }
        return this.password.equals(password);
    }
}
