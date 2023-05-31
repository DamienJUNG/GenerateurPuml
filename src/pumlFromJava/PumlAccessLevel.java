package pumlFromJava;

import javax.lang.model.element.Modifier;
import java.util.Set;

public class PumlAccessLevel implements PumlElement{
    private Set<Modifier> modifiers;
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
