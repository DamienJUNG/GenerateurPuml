package western;//  @ Project : Western
//  @ File Name : western.Genre.Histoire1.java
//  @ Date : 02/02/2010
//  @ Author : P. Divoux
//  @ Date : 31/12/2021
//  @ Author : R. Schneider
//
//

public class Histoire1
{
    public static void main(String[] args)
    {
        Personnage max = new Cowboy("Max");
        Personnage kid = new Cowboy("The Kid", new Boisson("thé à la menthe", Genre.MASCULIN));
        Personnage bill = new Cowboy("Bill", new Boisson("whisky", Genre.MASCULIN));

        max.sePresenter();
        max.boire();
        max.dire("Bon, au boulot, maintenant !");

        kid.sePresenter();
        kid.boire();

        bill.sePresenter();
        bill.boire(new Boisson("eau", Genre.FEMININ));
    }
}
