package pumlFromJava;

import jdk.javadoc.doclet.Doclet;

import java.util.ArrayList;
import java.util.List;

public class PumlOptionDCC implements Doclet.Option{
    public PumlOptionDCC(){}
    boolean isDcc;
    @Override
    public int getArgumentCount() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Demande au programme de générer un DCa";
    }

    @Override
    public Kind getKind() {
        return Doclet.Option.Kind.EXTENDED;
    }

    @Override
    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        list.add("--dca");
        return list;
    }

    @Override
    public String getParameters() {
        return "--dca";
    }

    @Override
    public boolean process(String option, List<String> arguments) {
        isDcc = false;
        return true;
    }

    public boolean isDcc() {
        return isDcc;
    }
}
