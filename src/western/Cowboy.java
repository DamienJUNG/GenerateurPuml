package western;

import java.util.ArrayList;
import java.util.List;

public class Cowboy extends Personnage{
    protected String replique = "I’m a poor lonesome cowboy !";
    protected int recompense;
    private final List<HorsLaLoi> vilains = new ArrayList<>();
    public Cowboy(String nom, Boisson boisson, String replique){
        super(nom,boisson);
        this.replique = replique;
        this.recompense=0;
    }
    public Cowboy(String nom, Boisson boisson){
        super(nom,boisson);
        this.recompense=0;
    }
    public Cowboy(String nom){
        super(nom);
        this.recompense=0;
    }

    public String getVilains() {
        StringBuilder prisonniers= new StringBuilder();
        for (HorsLaLoi vilain : this.vilains) {
            prisonniers.append(vilain.getPseudo());
        }
        return prisonniers.toString();
    }

    public void setReplique(String replique){
        this.replique = replique;
    }

    public String getPseudo() {
        return this.nom;
    }

    public void sePresenter(){
        String presentation = String.format("Bonjour, je suis %s et j'aime %s.",
                this.nom, this.boisson.getNomAvecArticleDefini());
        dire(presentation);
        if(this.recompense==0){
            dire("Je n'ai encore rien empoché.");
        }
        else {dire(String.format("J'ai déjà empoché %s$ en capturant %s",this.recompense,this.getVilains()));}
    }

    public void direReplique(){dire(this.replique);}
    public void tirerSur(HorsLaLoi vilain){
        dire("PAN ! PAN ! Prends ça, chacal de "+vilain.getPseudo()+" !");
        vilain.repondre(this);
    }

    public void capturer(HorsLaLoi vilain){
        for (Dame dame:vilain.getSesDames()) {
            dire("Voilà "+dame.getPseudo()+", tu es libre maintenant !");
            dame.seFaitSauver(this);
        }
        vilain.seFaireCapturer(this);
        recompense+=vilain.getPrime();
        this.vilains.add(vilain);
    }
}
