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
    private PumlOptionPATH path;
    private PumlOptionOUT out;
    private PumlOptionDCC dcc;
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

    @Override
    public boolean run(DocletEnvironment environment) {
        docletEnvironment = environment;
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