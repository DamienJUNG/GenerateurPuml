package pumlFromJava;

import jdk.javadoc.doclet.Doclet;

import java.util.spi.ToolProvider;
import java.io.*;

public class Java2Puml
{

    public static void main(String[] args)
    {
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        System.out.println(args);
        System.out.println(toolProvider.name());

/*
    javadoc -private -sourcepath <src> -doclet pumlFromJava.FirstDoclet -docletpath out/production/<projet>
      <package> ... <fichiers>
 */
        try{
            toolProvider.run(System.out, System.err, args);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
