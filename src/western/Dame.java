package western;

public class Dame extends Personnage{
    boolean estlibre;
    private HorsLaLoi geolier;
    public Dame(String nom,Boisson boisson){
        super(nom,boisson);
        this.estlibre=true;
    }

    public Dame(String nom){
        super(nom);
        estlibre=true;
    }

    public HorsLaLoi getGeolier(){
        return this.geolier;
    }
    public void seFaitEnlever(HorsLaLoi vilain) {
        if(this.estlibre || vilain.getClass() == Ripou.class){
            this.geolier=vilain;
            estlibre = false;
            dire("Hiii ! Au secours ! "+vilain.getPseudo()+" m'enlève !");
        }
        else {
            this.dire("Je suis déjà captive !! Si vous souhaitez me capturer arranger vous avec "+this.geolier.getPseudo());
        }
    }

    public void seFaitSauver(Cowboy cowboy){
        estlibre = true;
        dire("Merci "+cowboy.getPseudo()+", tu es mon sauveur !");
    }
    public String getPseudo() {
        return "Miss "+this.nom;
    }

    public void sePresenter() {
        String presentation = String.format("Bonjour, je suis %s et j'aime %s.",
                this.getPseudo(), this.boisson.getNomAvecArticleDefini());
        dire(presentation);
        if(this.estlibre){dire("Je suis libre :>");}
        else {dire(String.format("Je suis captive de cet escroc de %s.",this.geolier.getPseudo()));}
    }
}
