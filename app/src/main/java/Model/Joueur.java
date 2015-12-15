package Model;

import java.io.Serializable;

/**
 * Created by user on 13-12-15.
 */
public class Joueur implements Serializable{

    private Integer idJoueur;
    private String pseudo;
    private String motDePasse;
    private String reponseSecrete;
    private Integer idQuestion;
    private String email;
    private char sexe;
    private String reference;

    public Joueur(Integer idJoueur, String pseudo, String motDePasse, String reponseSecrete, Integer idQuestion, String email, char sexe, String reference) {
        this.idJoueur = idJoueur;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.reponseSecrete = reponseSecrete;
        this.idQuestion = idQuestion;
        this.email = email;
        this.sexe = sexe;
        this.reference = reference;
    }

    public Joueur() {
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Integer idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getReponseSecrete() {
        return reponseSecrete;
    }

    public void setReponseSecrete(String reponseSecrete) {
        this.reponseSecrete = reponseSecrete;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }




    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String toString()
    {
        return pseudo + " " + email + " " + motDePasse + " " + sexe + " " + reponseSecrete;
    }
}
