package classes.Accesso;

import java.text.SimpleDateFormat;
import java.util.Date;

import classes.Anagrafe.PersonaBuilder;

/*
 * Classe che definisce un amministratore del sistema.
 */
public class Amministratore extends Account {
    private String username;
    private String password; // La password deve essere memorizzata in forma cifrata

    // Costruttore che inizializza username e password dell'amministratore.
    public Amministratore(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    // Crea una nuova persona nel sistema a partire da una stringa di dati formattata: campi separati da virgole.
    public boolean nuovaPersona(String datiPersona) {
        PersonaBuilder builder = new PersonaBuilder();
        if (parse(datiPersona, builder) == false) {
            return false;
        } else {
            builder.getResult();
            return true;
        }
    }

    // Restituisce lo username dell'amministratore.
    public String getUsername() {
        return this.username;
    }

    /*
     * Imposta un nuovo username per l'amministratore, rimuovendo eventuali spazi iniziali
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
     * Imposta una nuova password per l'amministratore, rimuovendo eventuali spazi iniziali
     * e finali e verificando che il campo non sia vuoto.
     */
    public boolean setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        this.password = password.trim();
        return true;
    }

    // Verifica se la password fornita corrisponde a quella memorizzata per l'amministratore.
    public boolean autentica(String password) {
        if (password == null) {
            return false;
        }
        return this.password.equals(password);
    }

    // Parsing dei dati della persona da una stringa formattata, separando i valori con virgole.
    private boolean parse(String dati, PersonaBuilder builder) {
        if (dati == null) {
            mostraMessaggio("I dati non possono essere null.");
            return false;
        }

        // Suddivide la stringa in campi utilizzando la virgola come separatore.
        String[] campi = dati.split(",");

        // Verifica che il numero di campi sia corretto.
        if (campi.length != 9) {
            throw new IllegalArgumentException("Dati persona non validi");
        } else {
            // Popola il builder con i campi estratti dalla stringa, effettuando le opportune validazioni.
            if (builder.setNome(campi[0].trim()) == false) {
                mostraMessaggio("Il campo nome contiene caratteri non validi. Sono consentite solo lettere e spazi.");
                return false;
            }

            if (builder.setCognome(campi[1].trim()) == false) {
                mostraMessaggio("Il campo cognome contiene caratteri non validi. Sono consentite solo lettere e spazi.");
                return false;
            }

            if (builder.setDataNascita(parseData(campi[2].trim())) == false) {
                mostraMessaggio("Il campo data di nascita non è valido.");
                return false;
            }

            if (builder.setCodiceFiscale(campi[3].trim()) == false) {
                mostraMessaggio("Il campo nome contiene caratteri non validi. Sono consentite solo lettere e spazi.");
                return false;
            }

            if (builder.setSesso(campi[4].trim().charAt(0)) == false) {
                mostraMessaggio("Il campo sesso non è valido. Deve essere 'M' o 'F'.");
                return false;
            }

            if (builder.setIndirizzo(campi[5].trim()) == false) {
                mostraMessaggio("Il campo indirizzo non è valido.");
                return false;
            }

            if (builder.setCAP(campi[6].trim()) == false) {
                mostraMessaggio("Il campo CAP non è valido. Deve essere composto da 5 cifre.");
                return false;
            }

            if (builder.setStatoCivile(campi[7].trim()) == false) {
                mostraMessaggio("Il campo stato civile contiene caratteri non validi. Sono consentite solo lettere e spazi.");
                return false;
            }

            if (builder.setTelefono(campi[8].trim()) == false) {
                mostraMessaggio("Il campo telefono non è valido. Deve contenere solo cifre, con lunghezza tra 6 e 20 caratteri.");
                return false;
            }

            return true;
        }
    }

    // Metodo di supporto per il parsing della data in formato "dd/MM/yyyy".
    private Date parseData(String s) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(s);
        } catch (Exception e) {
            return null;
        }
    }

}