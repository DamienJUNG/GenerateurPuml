package pumlFromJava;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

//Représente une relation entre deux éléments (classes, interfaces, enum)
public class PumlRelation implements PumlElement{
    private final Element element;
    private final PumlType type;
    private final PumlAccessLevel accessLevel;
    public PumlRelation(Element element) {
        this.element = element;
        this.type = new PumlType(element.asType());
        this.accessLevel = new PumlAccessLevel(element.getModifiers());
    }
    @Override
    public String getDccCode() {
        return getSuperClass()+" o--> \""+getMultiplicity()+"\\n"+getAccessLevel()+getSimpleName()+"\" "+getType();
    }
    public String getMultiplicity(){
        if(element.asType().toString().contains("<")){
            return "0 .. *";
        }
        return "1";
    }

    @Override
    public String getDcaCode() {
        return getSuperClass()+" -- "+getType();
    }
    public String getAccessLevel() {
        return accessLevel.getDccCode();
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
