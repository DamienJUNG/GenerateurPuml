package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

public class PumlRelation implements PumlElement{
    private Element element;
    public PumlRelation(Element element) {
        this.element = element;
    }
    @Override
    public String getDccCode() {
        System.out.println(element.getSimpleName()+" : "+element.asType()+" "+element.asType().getKind().isPrimitive());
        if (!element.asType().toString().contains("<")){
            return getSuperClass()+" o-> \""+getAccessLevel()+getSimpleName()+"\" "+getType();
        }
        return "";
    }

    @Override
    public String getDcaCode() {
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

    public String getType() {
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
