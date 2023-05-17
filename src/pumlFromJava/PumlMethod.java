package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class PumlMethod implements PumlElement {
    private Element element;
    public PumlMethod(Element element){
        this.element = element;
    }

    @Override
    public String getDccCode() {
        return getAccessLevel()+" "+getSimpleName()+getAttributs()+" : "+getType();
    }

    @Override
    public String getDcaCode() {
        return null;
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }

    public String getType(){
        return element.getEnclosedElements().toString();
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
    public String getAttributs(){
        return element.asType().toString();
    }
    public String getOthersModifiers(){
        String modifiers = "";
        if(element.getModifiers().contains(Modifier.ABSTRACT)){
            modifiers+="{abstract}";
        }
        if(element.getModifiers().contains(Modifier.STATIC)){
            modifiers+="{static}";
        }
        return modifiers;
    }
}

