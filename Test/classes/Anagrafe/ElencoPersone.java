package classes.Anagrafe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import classes.StruttureDati.Elenco;

/*
 * Classe che definisce un elenco di persone registrate nell'anagrafe.
 */
public class ElencoPersone extends Elenco<Persona> {
    private static ElencoPersone instance;

    // Costruttore privato per implementare il pattern Singleton.
    private ElencoPersone() {}

    // Restituisce l'unica istanza di ElencoPersone.
    public static ElencoPersone getInstance() {
        if (instance == null) {
            instance = new ElencoPersone();
        }
        return instance;
    }

    // Cerca persone nell'elenco in base a un valore qualsiasi di ricerca.
    public List<Persona> cerca(String valore) {
        List<Persona> risultato = new ArrayList<>();
        String query = valore.toLowerCase(); // Ricerca case-insensitive

        for (Persona p : getElenco()) {
            // Ricerca tra tutti gli attributi dell'istanza Persona
            if ((p.getNome() != null && p.getNome().toLowerCase().contains(query)) ||
                (p.getCognome() != null && p.getCognome().toLowerCase().contains(query)) ||
                (p.getDataNascita() != null && p.getDataNascita().toString().toLowerCase().contains(query)) ||
                (p.getCodiceFiscale() != null && p.getCodiceFiscale().toLowerCase().contains(query)) ||
                (p.getIndirizzo() != null && p.getIndirizzo().toLowerCase().contains(query)) ||
                (p.getCAP() != null && p.getCAP().toLowerCase().contains(query)) ||
                (p.getStatoCivile() != null && p.getStatoCivile().toLowerCase().contains(query)) ||
                (p.getTelefono() != null && p.getTelefono().toLowerCase().contains(query))) {

                risultato.add(p);
            }
        }

        return risultato;
    }

    // Ordina le persone nell'elenco in base a un criterio specificato.
    public List<Persona> ordina(String criterio) {
        List<Persona> copia = new ArrayList<>(elementi);
        if (criterio == null || copia.isEmpty()) return copia;

        String c = criterio.trim().toLowerCase();

        switch (c) {
            case "nome":
                /*
                 *  Si passa come argomento al metodo sort un Comparator, e in particolare il suo metodo comparing, per gestire i valori nulli.
                 *  Il metodo nullsLast posiziona eventuali valori nulli (caso anomalo) alla fine dell'ordinamento, a cui si passa come argomento
                 *  il metodo compareToIgnoreCase per effettuare un confronto tra stringhe ignorando le differenze tra maiuscole e minuscole.
                 */
                copia.sort(Comparator.comparing(Persona::getNome, Comparator.nullsLast(String::compareToIgnoreCase)));
                break;

            case "cognome":
                copia.sort(Comparator.comparing(Persona::getCognome, Comparator.nullsLast(String::compareToIgnoreCase)));
                break;

            case "datanascita":
                copia.sort(Comparator.comparing(Persona::getDataNascita,
                        Comparator.nullsLast(Comparator.naturalOrder())));
                break;

            case "codicefiscale":
                copia.sort(Comparator.comparing(Persona::getCodiceFiscale,
                        Comparator.nullsLast(String::compareToIgnoreCase)));
                break;

            case "indirizzo":
                copia.sort(Comparator.comparing(Persona::getIndirizzo,
                        Comparator.nullsLast(String::compareToIgnoreCase)));
                break;

            case "cap":
                copia.sort(Comparator.comparing(Persona::getCAP,
                        Comparator.nullsLast(String::compareToIgnoreCase)));
                break;

            case "statocivile":
                copia.sort(Comparator.comparing(Persona::getStatoCivile,
                        Comparator.nullsLast(String::compareToIgnoreCase)));
                break;

            case "telefono":
                copia.sort(Comparator.comparing(Persona::getTelefono,
                        Comparator.nullsLast(String::compareTo)));
                break;

            default:
                throw new IllegalArgumentException("Criterio non riconosciuto: " + criterio);
        }

        return copia;
    }

    // Restituisce l'elenco completo delle persone registrate.
    public List<Persona> visualizza() {
        return new ArrayList<>(elementi);
    }

    // Registra una nuova persona nell'elenco utilizzando i dati forniti dal builder PersonaBuilder.
    public Persona registra(PersonaBuilder builder) {
        Persona p = new Persona(builder);
        this.aggiungi(p);
        return p;
    }

    // Modifica i dati di una persona esistente identificata dal suo ID attraverso un nuovo set di dati forniti dal builder PersonaBuilder.
    public Persona modifica(int IDPersona, PersonaBuilder nuoviDati) {
        for (int i = 0; i < elementi.size(); i++) {
            Persona p = elementi.get(i);
            if (p.getID() == IDPersona) {
                Persona aggiornata = new Persona(nuoviDati);
                elementi.set(i, aggiornata);
                return aggiornata;
            }
        }
        return null;
    }

    // Elimina una persona dall'elenco in base al suo ID.
    public boolean elimina(int IDPersona) {
        return elementi.removeIf(p -> p.getID() == IDPersona);
    }

    /*  
     *  Se il codice fiscale da controllare è già presente nell'anagrafe, ritorna false:
     *  la verifica serve per evitare registrazioni di persone con lo stesso codice fiscale.
     */
    public boolean verificaCodiceFiscale(String codiceFiscale) {
        for (Persona p : elementi) {
            if (p.getCodiceFiscale().equalsIgnoreCase(codiceFiscale)) {
                return false;
            }
        }
        return true;
    }

    /*  
     *  Se il numero di telefono da controllare è già presente nell'anagrafe, ritorna false:
     *  la verifica serve per evitare registrazioni di persone con lo stesso numero di telefono.
     */
    public boolean verificaTelefono(String telefono) {
        for (Persona p : elementi) {
            if (p.getTelefono().equals(telefono)) {
                return false;
            }
        }
        return true;
    }

    // Restituisce la persona corrispondente all'ID specificato, o null se non trovata.
    public Persona getPersona(int ID) {
        for (Persona p : elementi) {
            if (p.getID() == ID) {
                return p;
            }
        }
        return null;
    }
}