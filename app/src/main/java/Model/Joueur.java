package Model;

/**
 * Created by user on 13-12-15.
 */
public class Joueur {

    private Integer idJoueur;
    private String pseudo;
    private String motDePasse;
    private String reponseSecrete;
    private Integer idQuestion;

    public Joueur(Integer idJoueur, String pseudo, String motDePasse, String reponseSecrete, Integer idQuestion) {
        this.idJoueur = idJoueur;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.reponseSecrete = reponseSecrete;
        this.idQuestion = idQuestion;
    }

    public Joueur() {
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
}
