package western;//  @ Project : Western
//  @ File Name : western.Genre.Histoire3.java
//  @ Date : 02/02/2010
//  @ Author : P. Divoux
//  @ Date : 31/12/2022
//  @ Author : R. Schneider
//
//

//import western2.western.Genre.Narrateur;

import western.Test.Boisson;

public class Histoire3
{
    public static void main(String[] args)
    {
        Cowboy luke = new Cowboy("Luke", new Boisson("bière", Genre.FEMININ));
        Brigand dalton = new Brigand("Dalton", new Boisson("whisky", Genre.MASCULIN));
        Dame scarlett = new Dame("Scarlett");
        Narrateur narrateur = new Narrateur("Al");

        /* Les présentations */
        narrateur.sePresenter();
        narrateur.dire("Il était une fois, à l'Ouest de Java Town, trois personnages singuliers :");
        luke.sePresenter();
        dalton.sePresenter();
        scarlett.sePresenter();

        /* Les libations */
        narrateur.dire("Après leur traversée du désert, nos trois personnages s'arrêtent dans un bar pour étancher leur soif.");
        luke.dire("Nous sommes arrivés. Trinquons, maintenant !");
        luke.boire();
        dalton.boire();
        dalton.dire("Waoh ! Il est fort celui-la !");
        scarlett.boire();
        scarlett.dire("C'est quand même plus hydratant que l'alcool !");

        /* un peu d'action */
        narrateur.dire("Et maintenant, un peu d'action.");
        narrateur.dire(dalton.getNom() + " kidnappe " + scarlett.getNom() + ".");
        dalton.kidnapper(scarlett);
        scarlett.sePresenter();
        dalton.sePresenter();
        narrateur.dire("Mais " + luke.getNom() + " intervient sans tarder et capture " + dalton.getNom() + ".");
        luke.tirerSur(dalton);
        luke.capturer(dalton);
        scarlett.sePresenter();
        dalton.sePresenter();
        luke.sePresenter();
    }
}

