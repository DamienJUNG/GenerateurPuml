package pumlFromJava;
import java.util.spi.ToolProvider;

public class Java2Puml
{

    public static void main(String[] args)
    {
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        System.out.println(args);
        System.out.println(toolProvider.name());

<<<<<<< HEAD
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
=======
        //javadoc -private -sourcepath <src> -doclet pumlFromJava.FirstDoclet -docletpath out/production/<projet> <package> ... <fichiers>

        toolProvider.run(System.out, System.err, args);
>>>>>>> 82bebdf22b63770860c1500ff92e0f70fc2ec8dd
    }
}
