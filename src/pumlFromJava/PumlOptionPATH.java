package pumlFromJava;

import jdk.javadoc.doclet.Doclet;

import java.util.ArrayList;
import java.util.List;

//Cette classe est une option pour doclet, celle-ci permet de déterminer le répertoire de sortie
public class PumlOptionPATH implements Doclet.Option {
    private String path="./";
    public PumlOptionPATH() {
    }
    @Override
    public int getArgumentCount() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Permet de sélectionner le répertoire de sortie";
    }

    @Override
    public Kind getKind() {
        return Kind.EXTENDED;
    }

    @Override
    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        list.add("-d");
        return list;
    }

    @Override
    public String getParameters() {
        return "-d <p1>";
    }

    @Override
    public boolean process(String option, List<String> arguments) {
        path+=arguments.get(0);
        return true;
    }

    public String getPath() {
        return path;
    }
}
