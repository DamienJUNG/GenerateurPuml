package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.ElementType;
import java.util.ArrayList;

public class PumlMethod implements PumlElement {
    private final Element element;
    public PumlMethod(Element element){
        this.element = element;
    }

    @Override
    public String getDccCode() {
        return getAccessLevel()+" "+getSimpleName()+getAttributs()+getType();
    }

    @Override
    public String getDcaCode() {
        return null;
    }
        public String getEnclosingElement(){
        int index = element.getEnclosingElement().toString().lastIndexOf(".")+1;
        return element.getEnclosingElement().toString().substring(index);
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }

    public String getType(){
        StringBuilder str = new StringBuilder(element.asType().toString());
        String type = str.subSequence(str.indexOf(")")+1,str.length()).toString();
        if(!type.equals("void")){
            return " : "+type;
        }
        return "";
    }

    public String getAccessLevel() {
        if(element.getModifiers().contains(Modifier.PUBLIC)){
            return "+";
        }
        else if(element.getModifiers().contains(Modifier.PROTECTED)){
            return "~";
        }
        else{
            return "-";
        }
    }
    public String getAttributs(){
        StringBuilder str = new StringBuilder(element.asType().toString());
        System.out.println(element.getSimpleName()+" "+str.subSequence(0,str.indexOf(")")+1).toString());
        return str.subSequence(0,str.indexOf(")")+1).toString();
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

