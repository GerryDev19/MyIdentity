package classes.Anagrafe;

import java.util.Date;

/*
 * Classe che definisce una persona registrata nell'anagrafe.
 */
public class Persona {
    private int ID;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String codiceFiscale;
    private char sesso;
    private String indirizzo;
    private String CAP;
    private String statoCivile;
    private String telefono;

    // Costruttore che inizializza una nuova persona a partire da un'istanza di tipo PersonaBuilder.
    Persona(PersonaBuilder builder) {
        this.ID = PersonaBuilder.contatoreID++;
        this.nome = builder.getNome();
        this.cognome = builder.getCognome();
        this.dataNascita = builder.getDataNascita();
        this.codiceFiscale = builder.getCodiceFiscale();
        this.sesso = builder.getSesso();
        this.indirizzo = builder.getIndirizzo();
        this.CAP = builder.getCAP();
        this.statoCivile = builder.getStatoCivile();
        this.telefono = builder.getTelefono();
    }

    // Restituisce l'ID univoco della persona.
    public int getID() {
        return ID;
    }

    // Restituisce il nome della persona.
    public String getNome() {
        return nome; 
    }

    // Restituisce il cognome della persona.
    public String getCognome() {
        return cognome; 
    }

    // Restituisce la data di nascita della persona.
    public Date getDataNascita() {
        return dataNascita;
    }

    // Restituisce il codice fiscale della persona.
    public String getCodiceFiscale() {
        return codiceFiscale; 
    }
    
    // Restituisce il sesso della persona.
    public char getSesso() {
        return sesso; 
    }

    // Restituisce l'indirizzo della persona.
    public String getIndirizzo() {
        return indirizzo; 
    }

    // Restituisce il CAP della persona.
    public String getCAP() {
        return CAP; 
    }

    // Restituisce lo stato civile della persona.
    public String getStatoCivile() {
        return statoCivile; 
    }

    // Restituisce il numero di telefono della persona.
    public String getTelefono() {
        return telefono; 
    }
}
