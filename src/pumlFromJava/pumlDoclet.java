package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class pumlDoclet implements Doclet{
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
            if (name.equals("")){name=environment.getSpecifiedElements().toArray()[0]+".puml";}
            FileWriter file;
            try{
                file = new FileWriter(path+"/"+name);
            }
            catch(IOException e) {
                System.out.println(path+" n'existe pas, le fichier apparaîtra dans le répertoire courant");
                file = new FileWriter("./"+name);
            }

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
            pumlDiagram diagram = new pumlDiagram(environment.getIncludedElements());
            for (Element element : diagram.getElements())
            {
                if(!Objects.equals(element.getKind().toString(), "PACKAGE") && element.getEnclosingElement()!=null){
                    System.out.println(element.getSimpleName());
                    file.write(element.getKind().toString().toLowerCase()+" "+element.getSimpleName().toString()+"\n");
                }
            }
            file.write("\n@enduml");
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void dumpElement(Element element)
    {
    }
}