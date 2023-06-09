package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.io.IOException;
import java.util.*;

//La classe Doclet de l'api, c'est elle qui fait le lien entre javadoc et le reste du package
public class PumlDoclet implements Doclet{
    private PumlOptionPATH path;
    private PumlOptionOUT out;
    private PumlOptionDCC dcc;
    @Override
    public void init(Locale locale, Reporter reporter) {  }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        Set<Option> options = new HashSet<>();
        path = new PumlOptionPATH();
        out = new PumlOptionOUT();
        dcc = new  PumlOptionDCC();
        options.add(out);
        options.add(path);
        options.add(dcc);
        return options;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    /* L'équivalent du "main" du doclet, elle permet ici d'instancier les PumlPackage
     * nécessaire pour construire le diagramme demandé */
    @Override
    public boolean run(DocletEnvironment environment) {
        PumlDiagram diagram = new PumlDiagram();
        try {
            String code;
            if (dcc.isDcc()){
                code = generateDcc(environment);
            }
            else {
                code = generateDca(environment);
            }
            String name;
            if (out.getName()!=null){
                name = (out.getName());
            }
            else {
                name = environment.getSpecifiedElements().toArray()[0].toString()+".puml";
            }
            diagram.generatePuml(name, path.getPath(), code);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    //generateDcc est la méthode qui permet la construction du Dcc
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
    //generateDca est la méthode qui permet la construction du Dca
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