package pumlFromJava;

import jdk.javadoc.doclet.Doclet;

import java.util.ArrayList;
import java.util.List;

public class PumlOptionOUT implements Doclet.Option {
    String name;
    public PumlOptionOUT(){
    }
                @Override
            public int getArgumentCount() {
                return 1;
            }

            @Override
            public String getDescription() {
                return "Permet de donner un nom au puml produit";
            }

            @Override
            public Kind getKind() {
                return Kind.EXTENDED;
            }

            @Override
            public List<String> getNames() {
                List<String> list = new ArrayList<>();
                list.add("-out");
                return list;
            }

            @Override
            public String getParameters() {
                return "-out <p1>";
            }

            @Override
            public boolean process(String option, List<String> arguments) {
                if(arguments.get(0).contains(".puml")){
                 name = arguments.get(0);
                }
                else {
                    name = arguments.get(0)+".puml";
                }
                return true;
            }

    public String getName() {
        return name;
    }
}