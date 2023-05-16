package western;

import western.Test.Boisson;

import java.util.ArrayList;

public class Brigand extends Personnage implements HorsLaLoi {
    private boolean estLibre;
    private final String look;
    private int prime=300;
    private Cowboy geolier;
    private final ArrayList<Dame> sesDames;
    public Brigand(String nom, Boisson boisson, String look){
        super(nom,boisson);
        this.look = look;
        estLibre =true;
        this.sesDames = new ArrayList<>();
    }
    public Brigand(String nom, String look){
        super(nom);
        this.look = look;
        estLibre =true;
        this.sesDames = new ArrayList<>();
    }
    public Brigand(String nom, Boisson boisson){
        super(nom,boisson);
        this.look = "le méchant";
        estLibre =true;
        this.sesDames = new ArrayList<>();
    }
    public Brigand(String nom){
        super(nom);
        this.look = "le méchant";
        estLibre =true;
        this.sesDames = new ArrayList<>();
    }

    public int getPrime(){return prime;}
    public void kidnapper(Dame dame){
        if(dame.getGeolier()==null) {
            dame.seFaitEnlever(this);
            this.prime +=50;
            this.sesDames.add(dame);
            dire("Ah, ah ! "+dame.getPseudo()+" tu es mienne désormais !");
        }
    }
    public boolean getEstLibre(){
        return this.estLibre;
    }
    public Cowboy getGeolier(){return geolier;}
    public ArrayList<Dame> getSesDames(){return (ArrayList<Dame>) this.sesDames.clone();}

    public void seFaireCapturer(Cowboy cowboy){
        this.geolier = cowboy;
        this.estLibre = false;
        this.sesDames.clear();
        dire("Damned, je suis fait ! Tu m'as eu "+cowboy.getPseudo()+" ! Mais tu n'emporteras pas les "+this.prime +"$ au paradis.");
    }
    public String getPseudo() {
        return this.nom+" "+this.look;
    }

    public void repondre(Cowboy cowboy){
        dire("Tu n'es qu'un coyote "+cowboy.getPseudo()+" !");
    }
    public void sePresenter() {
        String presentation = String.format("Bonjour, je suis %s et j'aime %s.",
                this.getPseudo(), this.boisson.getNomAvecArticleDefini());
        dire(presentation);
        dire(String.format("Ma tête est mise à prix %s$",this.prime));
        if(this.estLibre){dire("Héhé et je suis libre !!");}
        else {dire(String.format("Ce chien de %s me retient prisonnier.",geolier.getPseudo()));}
    }
}
