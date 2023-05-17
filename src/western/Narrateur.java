package western;

public class Narrateur extends Personnage{
    public Narrateur(String nom){
        super(nom);
    }
    public void boire(Boisson boisson){
        String deLaBoisson = boisson.getNomAvecArticlePartitif();
        if (boisson == BOISSON_PAR_DEFAUT)
            dire(String.format("Ah ! boire %s ! GLOUPS !", deLaBoisson));
        else{
            throw new RuntimeException("Le narrateur ne supporte que l'eau...");
        }
    }
    public void sePresenter(){dire(String.format("Bonjour je suis %s et j'aime %s.",this.getPseudo(),this.boisson.getNomAvecArticleDefini()));}
    public String getPseudo(){return this.nom+", le narrateur";}
    public void dire(String texte) {
        System.out.println(String.format(texte));
    }
}
