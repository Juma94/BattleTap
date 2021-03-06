package Model;

import java.io.Serializable;

/**
 * Created by user on 13-12-15.
 */
public class QuestionSecrete implements Serializable{
    private Integer idQuestion;
    private String libelle;

    public QuestionSecrete(Integer idQuestion, String libelle) {
        this.idQuestion = idQuestion;
        this.libelle = libelle;
    }

    public QuestionSecrete() {
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String toString()
    {
        return libelle;
    }
}
