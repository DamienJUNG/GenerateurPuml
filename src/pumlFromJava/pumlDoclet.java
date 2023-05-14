package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class pumlDoclet implements Doclet {
    private String path = "./";

    private String name="";
    @Override
    public void init(Locale locale, Reporter reporter) {  }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        Set<Option> options = new HashSet<>();
        options.add(new Option() {

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
        });
        options.add(new Option() {
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
        });
        return options;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        try {
            if (name.equals("")) {
                name=environment.getSpecifiedElements().toArray()[0]+".puml";
            }

            PumlDiagram diagram = new PumlDiagram();
            String code = "";
            for (Element element :environment.getSpecifiedElements()) {
                code+=element.getKind().toString().toLowerCase()+" "+element.getSimpleName()+" {\n\n";

                for (Element element1:element.getEnclosedElements()) {
                    code+= element1.getKind().toString().toLowerCase() + " " + element1.getSimpleName();
                    if(element1.getKind() == ElementKind.INTERFACE){
                        code += " <<interface>> ";
                    }
                    if(element1.getKind() == ElementKind.ENUM){
                        code += " <<enum>> ";
                    }
                    code += "{\n";

                    for(Element element2: element1.getEnclosedElements()) {
                        if(element2.getKind() == ElementKind.FIELD || element2.getKind() == ElementKind.ENUM_CONSTANT) {
                            code += "\t" + element2.getSimpleName() + "\n";
                        }
                    }
                    code += "}\n";
                }
                code += "}";
            }
            code += "\n@enduml";
            diagram.generatePuml(name,path,code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}