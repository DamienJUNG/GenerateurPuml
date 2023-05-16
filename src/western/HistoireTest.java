package western;

import western.Test.Boisson;

public class HistoireTest {
    public static void main(String[] args) {
        Brigand brigand = new Brigand("Robert");
        Dame dame = new Dame("Jessica");
        Boisson whisky = new Boisson("whisky", Genre.MASCULIN);
        Cowboy cowboy = new Cowboy("Luke",whisky);
        brigand.sePresenter();
        brigand.kidnapper(dame);
        dame.sePresenter();
        brigand.kidnapper(dame);
        cowboy.sePresenter();
        cowboy.tirerSur(brigand);
        cowboy.capturer(brigand);
        brigand.sePresenter();
        Narrateur narrateur = new Narrateur("Bob");
        narrateur.sePresenter();
        narrateur.boire(whisky);
    }
}
