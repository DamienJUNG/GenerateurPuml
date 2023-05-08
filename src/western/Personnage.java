package western;

public abstract class Personnage
{
    protected static Boisson BOISSON_PAR_DEFAUT = new Boisson("eau", Genre.FEMININ);
    protected String nom;
    protected Boisson boisson;

    public Personnage(String nom)
    {
        this(nom, BOISSON_PAR_DEFAUT);
    }

    public abstract String getPseudo();
    public Personnage(String nom, Boisson boisson)
    {
        this.nom = nom;
        this.boisson = boisson;
    }

    public void dire(String texte)
    {
        System.out.println(String.format("%s - %s", this.nom, texte));
    }
    public void sePresenter()
    {
        String presentation = String.format("Bonjour, je suis %s et j'aime %s.",
                this.nom, this.boisson.getNomAvecArticleDefini());
        dire(presentation);
    }

    public void boire(Boisson boisson)
    {
        String deLaBoisson = boisson.getNomAvecArticlePartitif();
        if (boisson == this.boisson)
            dire(String.format("Ah ! boire %s ! GLOUPS !", deLaBoisson));
        else
            dire("GLOUPS ! Faut vraiment avoir soif pour boire " + deLaBoisson
                + " ! j'aurais préféré boire " + this.boisson.getNomAvecArticlePartitif() + ".");
    }

    public void boire()
    {
        boire(this.boisson);
    }

    public String getNom()
    {
        return this.nom;
    }
    public Boisson getBoisson()
    {
        return this.boisson;
    }
}