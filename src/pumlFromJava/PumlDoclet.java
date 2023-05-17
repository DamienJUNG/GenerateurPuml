package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.io.IOException;
import java.util.*;

public class PumlDoclet implements Doclet{
    private String path = "./";
    private String name="";
    private boolean isDcc = true;
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
        options.add(new Option() {
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
                return Kind.EXTENDED;
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
        });
        return options;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        if (name.equals("")){name=environment.getSpecifiedElements().toArray()[0]+".puml";}
            PumlDiagram diagram = new PumlDiagram();
        try {
            String code;
            System.out.println(environment.getIncludedElements());
            if (isDcc){
                code = generateDcc(environment);
            }
            else {
                code = generateDca(environment);
            }
            diagram.generatePuml(name, path, code);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public String generateDcc(DocletEnvironment environment){
        StringBuilder code = new StringBuilder();
        for (Element element:environment.getIncludedElements()) {
            if(element.getKind()==ElementKind.MODULE){
                for (Element thing:element.getEnclosedElements()) {
                PumlPackage pack = new PumlPackage(thing);
                code.append(pack.getDccCode()).append("\n");
                }
            }
        }
        return code.toString();
    }
    public String generateDca(DocletEnvironment environment){
        StringBuilder code = new StringBuilder();
        for (Element element:environment.getIncludedElements()) {
            if(element.getKind()==ElementKind.MODULE){
                for (Element thing:element.getEnclosedElements()) {
                PumlPackage pack = new PumlPackage(thing);
                code.append(pack.getDcaCode()).append("\n");
                }
            }
        }
        return code.toString();
    }
}