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
    private List<Object> options;
    private boolean isDcc = true;
    DocletEnvironment docletEnvironment;
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
        options.add(new PumlOptionOUT());
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
        this.options = Arrays.stream(options.toArray()).toList();
        return options;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        docletEnvironment = environment;
            PumlDiagram diagram = new PumlDiagram();
        try {
            String code;
            if (isDcc){
                code = generateDcc(environment);
            }
            else {
                code = generateDca(environment);
            }
            String name;
            if (((PumlOptionOUT)options.get(1)).getName()!=null){
                name = ((PumlOptionOUT)options.get(1)).getName();
            }
            else {
                name = environment.getSpecifiedElements().toArray()[0].toString();
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