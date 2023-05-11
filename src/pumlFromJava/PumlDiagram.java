package pumlFromJava;

import java.io.FileWriter;
import java.io.IOException;

public class PumlDiagram {

    public PumlDiagram() {}

    public void generatePuml(String fileName, String path, String code) throws IOException {
        FileWriter file;
        try{
            file = new FileWriter(path+"/"+fileName);
        }
        catch(IOException e) {
            System.out.println(path+" n'existe pas, le fichier apparaîtra dans le répertoire courant");
            file = new FileWriter("./"+fileName);
        }
        file.write("@startuml\n");
        file.write("'Code généré automatiquement ;>\n");
        file.write("skinparam style strictuml\n");
        file.write("hide empty members\n");
        file.write("skinparam classAttributeIconSize 0\n");
        file.write("skinparam classFontStyle Bold\n");
        file.write("skinparam classbackgroundColor LightGoldenRodYellow\n");
        file.write("skinparam classbordercolor red\n");
        file.write("skinparam classattribute none\n");
        file.write("skinparam classborderthickness 2\n\n");
        file.write(code);
        file.close();
    }
}
