package classes.Anagrafe;

import java.util.Date;

/*
 * Classe che definisce un costruttore di istanze di Persona, secondo il pattern Builder.
 */
public class PersonaBuilder {
    static int contatoreID = 0; // ID univoco usato per assegnare alle nuove registrazioni un valore ID a incremento unitario.
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String codiceFiscale;
    private char sesso;
    private String indirizzo;
    private String CAP;
    private String statoCivile;
    private String telefono;

    public PersonaBuilder() {}

    // Resetta tutti i campi del builder.
    public boolean reset() {
        this.nome = null;
        this.cognome = null;
        this.dataNascita = null;
        this.codiceFiscale = null;
        this.sesso = '\0';
        this.indirizzo = null;
        this.CAP = null;
        this.statoCivile = null;
        this.telefono = null;
        return true;
    }

    // Valida e imposta il campo nome.
    public boolean setNome(String nome) {
        if (nome == null) {
            return false;
        }
        String filtrato = nome.trim();
        if (filtrato.isEmpty()) {
            return false;
        }
        
        /* 
         *Accetta solo caratteri letterali Unicode e spazi
         * \p{L} corrisponde a qualsiasi lettera in Unicode
         */
        if (!filtrato.matches("[\\p{L} ]+")) {
            return false;
        }
        this.nome = filtrato;
        return true;
    }

    // Valida e imposta il campo cognome.
    public boolean setCognome(String cognome) {
        if (cognome == null) {
            return false;
        }
        String filtrato = cognome.trim();
        if (filtrato.isEmpty()) {
            return false;
        }
        if (!filtrato.matches("[\\p{L} ]+")) {
            return false;
        }
        this.cognome = filtrato;
        return true;
    }

    // Valida e imposta il campo data di nascita.
    public boolean setDataNascita(Date dataNascita) {
        if (dataNascita == null) {
            return false;
        }
        Date adesso = new Date();
        if (dataNascita.after(adesso)) {
            return false; // Le date future non vengono accettate
        }
        this.dataNascita = dataNascita;
        return true;
    }

    // Valida e imposta il campo codice fiscale.
    public boolean setCodiceFiscale(String codiceFiscale) {
        if (codiceFiscale == null) {
            return false;
        }
        String filtrato = codiceFiscale.trim().toUpperCase();
        // Il codice fiscale è composto da 16 caratteri alfanumerici
        if (!filtrato.matches("[A-Z0-9]{16}")) {
            return false;
        }
        
        if (ElencoPersone.getInstance().verificaCodiceFiscale(filtrato) == false) {
            return false;
        } else {
            this.codiceFiscale = filtrato;
            return true;
        }
    }

    // Valida e imposta il campo sesso.
    public boolean setSesso(char sesso) {
        // Il metodo toUpperCase converte il carattere in maiuscolo.
        char s = Character.toUpperCase(sesso);
        if (s == 'M' || s == 'F') {
            this.sesso = s;
            return true;
        }
        return false;
    }

    // Valida e imposta il campo indirizzo.
    public boolean setIndirizzo(String indirizzo) {
        if (indirizzo == null) {
            return false;
        }
        String filtrato = indirizzo.trim();
        if (filtrato.isEmpty()) {
            return false;
        }
        this.indirizzo = filtrato;
        return true;
    }

    // Valida e imposta il campo CAP.
    public boolean setCAP(String CAP) {
        if (CAP == null) {
            return false;
        }
        String filtrato = CAP.trim();
        // Il CAP è composto da 5 cifre.
        if (!filtrato.matches("\\d{5}")) {
            return false;
        }
        this.CAP = filtrato;
        return true;
    }

    // Valida e imposta il campo stato civile.
    public boolean setStatoCivile(String statoCivile) {
        if (statoCivile == null) {
            return false;
        }
        String filtrato = statoCivile.trim();
        if (filtrato.isEmpty()) {
            return false;
        }
        // Il campo ammette lettere e spazi.
        if (!filtrato.matches("[\\p{L} ]+")) {
            return false;
        }
        this.statoCivile = filtrato;
        return true;
    }

    // Valida e imposta il campo telefono.
    public boolean setTelefono(String telefono) {
        if (telefono == null) {
            return false;
        }
        String filtrato = telefono.trim();
        // Il campo ammette solo cifre, lunghezza 6-20
        if (!filtrato.matches("[0-9]{6,20}")) {
            return false;
        }
        if (ElencoPersone.getInstance().verificaTelefono(filtrato) == false) {
            return false;
        } else {
            this.telefono = filtrato;
            return true;
        }
    }

    // Crea una nuova istanza di Persona con i dati attualmente impostati nel builder e la registra nell'elenco delle persone.
    public Persona getResult() {
        contatoreID++;
        return ElencoPersone.getInstance().registra(this);
    }

    String getNome() {
        return nome;
    }

    String getCognome() {
        return cognome;
    }

    Date getDataNascita() {
        return dataNascita;
    }

    String getCodiceFiscale() {
        return codiceFiscale;
    }

    char getSesso() {
        return sesso;
    }

    String getIndirizzo() {
        return indirizzo;
    }

    String getCAP() {
        return CAP;
    }

    String getStatoCivile() {
        return statoCivile;
    }

    String getTelefono() {
        return telefono;
    }
}