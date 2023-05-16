package pumlFromJava;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.Element;

public class PumlAttribut implements PumlElement {
    private final Element element;
    public PumlAttribut(Element element){
        this.element = element;
    }

    @Override
    public String getPumlCode() {
        return getAccessLevel()+" "+getSimpleName()+" : "+getType()+" "+getOthersModifiers();
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }

    public String getType(){
        int index = element.asType().toString().lastIndexOf(".");
        return element.asType().toString();
    }
    public String getAccessLevel() {
        if(element.getModifiers().contains(Modifier.PRIVATE)){
            return "-";
        }
        else if(element.getModifiers().contains(Modifier.PUBLIC)){
            return "+";
        }
        else if(element.getModifiers().contains(Modifier.PROTECTED)){
            return "~";
        }
        else{
            return "#";
        }
    }
    public String getOthersModifiers(){
        String modifiers = "";
        if(element.getModifiers().contains(Modifier.STATIC)){
            modifiers+="{static}";
        }
        return modifiers;
    }
}
