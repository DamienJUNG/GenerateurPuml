package western;

import western.Test.Boisson;

import java.util.ArrayList;

public class Ripou extends Cowboy implements HorsLaLoi{
    Brigand brigand;
    public Ripou(String nom, Boisson boisson, String look){
        super(nom,boisson);
        brigand = new Brigand(nom,boisson,look);
    }
    public Ripou(String nom, String look){
        super(nom,BOISSON_PAR_DEFAUT);
        brigand = new Brigand(nom,look);
    }
    public Ripou(String nom, Boisson boisson){
        super(nom,boisson);
        brigand = new Brigand(nom,boisson);
    }
    public Ripou(String nom){
        super(nom,BOISSON_PAR_DEFAUT);
        brigand = new Brigand(nom);
    }

    public void kidnapper(Dame dame) {
        brigand.kidnapper(dame);
    }
    public ArrayList<Dame> getSesDames(){return (ArrayList<Dame>) brigand.getSesDames();}
    public void seFaireCapturer(Cowboy cowboy){
        brigand.seFaireCapturer(cowboy);
    }
    public String getPseudo() {
        if(brigand.getEstLibre()){
            return brigand.getPseudo();
        }
        else {
            return super.getPseudo();
        }
    }
    public void repondre(Cowboy cowboy){
        dire("Tu n'es qu'un coyote "+cowboy.getPseudo()+" !");
    }
    public void sePresenter() {
        String presentation;
        presentation = String.format("Bonjour, je suis %s et j'aime %s.", this.getPseudo(), this.boisson.getNomAvecArticleDefini());
        dire(presentation);
        dire(String.format("Ma tête est mise à prix %s$",brigand.getPrime()));
        if(this.recompense==0){
            dire("Je n'ai encore rien empoché.");
        }
        else {dire(String.format("J'ai déjà empoché %s$ en capturant %s",this.recompense,this.getVilains()));}
        if(brigand.getEstLibre()){dire("Héhé et je suis libre !!");}
        else {dire(String.format("Ce chien de %s me retient prisonnier.",brigand.getGeolier().getPseudo()));}
    }
    public void capturer(HorsLaLoi vilain){
        for (Dame dame:vilain.getSesDames()) {
            kidnapper(dame);
        }
        dire("Héhé désormais tes dames sont miennes "+vilain.getPseudo());
    }
    public int getPrime(){return brigand.getPrime();}
}
