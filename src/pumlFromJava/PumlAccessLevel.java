package pumlFromJava;

import javax.lang.model.element.Modifier;
import java.util.Set;

//Cette classe représente un niveau d'accès, elle permet de produire le code correspondant au format puml
public class PumlAccessLevel implements PumlElement{
    private Set<Modifier> modifiers;
    //On a besoin de stocker les Modifier de l'élément dont on cherche à représenter la visibilité
    public PumlAccessLevel(Set<Modifier> modifiers){
        this.modifiers = modifiers;
    }

    @Override
    public String getDccCode() {
        if(modifiers.contains(Modifier.PUBLIC)){
            return "+";
        }
        else if(modifiers.contains(Modifier.PROTECTED)){
            return "#";
        }
        else{
            return "-";
        }
    }
    @Override
    public String getDcaCode() {
        return getDccCode();
    }

    @Override
    public String getSimpleName() {
        return null;
    }
}
