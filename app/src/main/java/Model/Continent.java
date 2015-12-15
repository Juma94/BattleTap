package Model;

import java.io.Serializable;

/**
 * Created by user on 13-12-15.
 */
public class Continent implements Serializable {

    private String reference;
    private String libelle;

    public Continent(String reference, String libelle) {
        this.reference = reference;
        this.libelle = libelle;
    }

    public Continent() {
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String toString()
    {
        return libelle.toUpperCase();
    }
}
