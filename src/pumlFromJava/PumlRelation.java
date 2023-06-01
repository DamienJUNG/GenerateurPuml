package pumlFromJava;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

public class PumlRelation implements PumlElement{
    private final Element element;
    private final PumlType type;
    public PumlRelation(Element element) {
        this.element = element;
        this.type = new PumlType(element.asType());
    }
    @Override
    public String getDccCode() {
        return getSuperClass()+" o-> \""+getMultipicity()+"\\n"+getAccessLevel()+getSimpleName()+"\" "+getType();
    }
    public String getMultipicity(){
        if(element.asType().toString().contains("<")){
            return "0 .. *";
        }
        return "1";
    }

    @Override
    public String getDcaCode() {
        return getSuperClass()+" - "+getType();
    }
    public String getAccessLevel() {
        if(element.getModifiers().contains(Modifier.PUBLIC)){
            return " +";
        }
        else if(element.getModifiers().contains(Modifier.PROTECTED)){
            return " #";
        }
        else{
            return " -";
        }
    }
    public String getType() {
        return type.getLongName();
    }
    public String getSuperClass() {
        return element.getEnclosingElement().getSimpleName().toString();
    }
    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }
}
