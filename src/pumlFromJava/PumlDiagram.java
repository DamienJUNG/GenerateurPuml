package pumlFromJava;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* PumlDiagram est la classe qui sert à générer le fichier .puml
 * à partir de la chaîne de caractère représentant le digramme */
public class PumlDiagram {
    public PumlDiagram(){}

    /*public void generatePuml :
    * fileName : String : Chaîne qui contient le nom du fichier produit
    * path : String : Chaîne qui contient le chemin vers le répertoire de sortie
    * code : String : Chaîne qui contient le digramme*/
    public void generatePuml(String fileName, String path, String code) throws IOException {
        BufferedWriter file;
        try{
            //Créer et ouvre un flux vers le fichier destination (l'écrase si déjà existant)
            file = new BufferedWriter(new FileWriter(path+"/"+fileName));
        }
        catch(IOException e) {
            System.out.println(path+" n'existe pas, le fichier apparaîtra dans le répertoire courant");
            file = new BufferedWriter(new FileWriter("./"+fileName));
        }

        //Paramètres du diagramme
        file.write("@startuml\n\n");
        file.write("'Code généré automatiquement ;>\n");
        file.write("skinparam style strictuml\n");
        file.write("hide empty members\n");
        file.write("skinparam classAttributeIconSize 0\n");
        file.write("skinparam classFontStyle Bold\n");
        file.write("skinparam classbackgroundColor LightGoldenRodYellow\n");
        file.write("skinparam classbordercolor red\n");
        file.write("skinparam classattribute none\n");
        file.write("skinparam classborderthickness 2\n\n");

        //Code puml
        file.write(code);
        file.write("\n@enduml");
        file.close();
        //On n'oublie pas de fermer le flux :>
    }
}
