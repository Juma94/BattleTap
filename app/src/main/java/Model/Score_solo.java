package Model;

import java.io.Serializable;

/**
 * Created by user on 13-12-15.
 */
public class Score_solo implements Serializable {

    private Integer idScore;
    private String temps;
    private Integer differenceIncrementation;
    private Integer nbNombresAtteints;
    private Integer idJoueur;

    public Score_solo(Integer idScore, String temps, Integer differenceIncrementation, Integer nbNombresAtteints, Integer idJoueur) {
        this.idScore = idScore;
        this.temps = temps;
        this.differenceIncrementation = differenceIncrementation;
        this.nbNombresAtteints = nbNombresAtteints;
        this.idJoueur = idJoueur;
    }

    public Score_solo() {
    }

    public Integer getIdScore() {
        return idScore;
    }

    public void setIdScore(Integer idScore) {
        this.idScore = idScore;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public Integer getDifferenceIncrementation() {
        return differenceIncrementation;
    }

    public void setDifferenceIncrementation(Integer differenceIncrementation) {
        this.differenceIncrementation = differenceIncrementation;
    }

    public Integer getNbNombresAtteints() {
        return nbNombresAtteints;
    }

    public void setNbNombresAtteints(Integer nbNombresAtteints) {
        this.nbNombresAtteints = nbNombresAtteints;
    }

    public Integer getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Integer idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String toString()
    {
        return "temps = " + temps;
    }
}
