package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

public class PumlRelation implements PumlElement{
    private final Element element;
    public PumlRelation(Element element) {
        this.element = element;
    }
    @Override
    public String getDccCode() {
        if (!element.asType().toString().contains("<")){
            return getSuperClass()+" o-> \""+getAccessLevel()+getSimpleName()+"\" "+getType();
        }
        else if(element.asType().toString().contains("enum")){
            return "";
        }
        else {
            int start = getType().indexOf("<")+1;
            int end = getType().indexOf(">");
            return getSuperClass()+" o-> \""+getAccessLevel()+getSimpleName()+"[*]"+"\" "+getType().substring(start, end);
        }
    }

    @Override
    public String getDcaCode() {
        if (element.asType().toString().contains("enum")){
            return "";
        }
        else if(!element.asType().toString().contains("<")){
            return getSuperClass()+" - "+getType();
        }
        else {
            int start = getType().indexOf("<")+1;
            int end = getType().indexOf(">");
            return getSuperClass()+" -> "+getType().substring(start, end);
        }
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
    public String getType() {
        if (element.asType().toString().contains(".")){
            int index = element.asType().toString().lastIndexOf(".")+1;
            return element.asType().toString().substring(index);
        }
        return element.asType().toString();
    }
    public String getSuperClass() {
        return element.getEnclosingElement().getSimpleName().toString();
    }
    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }
}
