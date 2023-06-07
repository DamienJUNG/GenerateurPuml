package Java2PumlTest1;

public class Tkt {
    public int coucou(){
        return 0;
    }

    public String saumon(){
        return null;
    }
}
class patate extends Tkt{
    @Override
    public String saumon(){
        return "Bonjour";
    }

    public double copain(){
        return 0;
    }
}

class orange extends patate{
    public boolean brique(){
        return false;
    }
}

class flute extends patate {
    public int bacon(){
        return 10;
    }
}

class oiseau extends orange implements cahier{
    @Override
    public int coucou(){
        return 10;
    }
    @Override
    public String saumon(){
        return "Bientôt les vacances !!";
    }

    @Override
    public boolean brique() {
        return super.brique();
    }

    @Override
    public double copain() {
        return 10000;
    }

    @Override
    public String fromage() {
        return null;
    }
    @Override
    public String toString(){
        return null;
    }

    public char Char(){
        return '0';
    }
    public int bacon(){
        return 9;
    }
}

interface vaisselle{
    public double copain();
}

interface cahier extends vaisselle {
    public String fromage();
}


