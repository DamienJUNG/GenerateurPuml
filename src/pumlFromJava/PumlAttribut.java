package pumlFromJava;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeKind;

public class PumlAttribut implements PumlElement {
    private final Element element;
    private final PumlType type;
    PumlAccessLevel accessLevel;

    public PumlAttribut(Element element){
        this.element = element;
        accessLevel = new PumlAccessLevel(element.getModifiers());
        type = new PumlType(element.asType());
    }

    @Override
    public String getDccCode() {
        return getAccessLevel()+" "+getSimpleName()+getType()+" "+getOthersModifiers();
    }

    @Override
    public String getDcaCode() {
        return getSimpleName();
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }

    public String getType(){
        return type.getDccCode();
    }
    public String getAccessLevel() {
        return accessLevel.getDccCode();
    }
    public String getOthersModifiers(){
        String modifiers = "";
        if(element.getModifiers().contains(Modifier.STATIC)){
            modifiers+=" {static}";
        }
        return modifiers;
    }
}
