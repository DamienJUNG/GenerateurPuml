package pumlFromJava;
import javax.lang.model.element.Modifier;
import java.util.Set;
public class PumlModifier implements PumlElement {
    private final Set<Modifier> modifiers;
    //On a besoin de stocker les Modifier de l'élément dont on cherche à représenter la visibilité
    public PumlModifier(Set<Modifier> modifiers){
        this.modifiers = modifiers;
    }

    @Override
    public String getDccCode() {
        return null;
    }
    @Override
    public String getDcaCode() {
        return null;
    }

    @Override
    public String getSimpleName() {
        return null;
    }
    public String getAccessLevel(){
        if(modifiers.contains(Modifier.PUBLIC)){
            return " +";
        }
        else if(modifiers.contains(Modifier.PROTECTED)){
            return " #";
        }
        else{
            return " -";
        }
    }

    public String getClassModifiers() {
        String modifiersCode = "";
        if(modifiers.contains(Modifier.ABSTRACT)){
            modifiersCode+="abstract ";
        }
        if(modifiers.contains(Modifier.STATIC)){
            modifiersCode+="static ";
        }
        return modifiersCode;
    }
    public String getSimpleModifiers(){
        String modifiersCode = "";
        if(modifiers.contains(Modifier.ABSTRACT)){
            modifiersCode+=" {abstract}";
        }
        if(modifiers.contains(Modifier.STATIC)){
            modifiersCode+=" {static}";
        }
        if(modifiers.contains(Modifier.FINAL)){
            modifiersCode+=" {ReadOnly}";
        }
        return modifiersCode;
    }
}
