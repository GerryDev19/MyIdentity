package classes.StruttureDati;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe astratta che definisce un elenco generico di elementi di tipo T.
 */
public abstract class Elenco<T> {
    protected List<T> elementi;

    // Costruttore che inizializza l'elenco come una lista vuota (in questa implementazione è stato scelto ArrayList).
    protected Elenco() {
        this.elementi = new ArrayList<>();
    }

    // Aggiunge un elemento all'elenco.
    public boolean aggiungi(T elemento) {
        return elementi.add(elemento);
    }

    // Restituisce il numero totale di elementi presenti nell'elenco.
    public int totale() {
        return elementi.size();
    }

    // Restituisce una copia dell'elenco degli elementi.
    public List<T> getElenco() {
        return new ArrayList<>(elementi);
    }

    // Restituisce l'elemento all'indice specificato.
    public T getElemento(int index) {
        if (index < 0 || index >= elementi.size()) return null;
        return elementi.get(index);
    }

    // Metodo astratto che deve essere implementato dalle sottoclassi per visualizzare gli elementi dell'elenco.
    public abstract List<T> visualizza();
}