package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class PumlClass implements PumlElement {
    private final Element element;
    private final ArrayList<PumlAttribut> attributs = new ArrayList<>();
    private final ArrayList<PumlMethod> methods = new ArrayList<>();
    public PumlClass(Element element){
        this.element = element;
        for (Element thing:element.getEnclosedElements()) {
            if(thing.getKind()== ElementKind.FIELD){
                attributs.add(new PumlAttribut(thing));
            }
            else{
                if(thing.getKind()==ElementKind.CONSTRUCTOR){
                    methods.add(new PumlConstructor(thing));
                }
                else {
                    methods.add(new PumlMethod(thing));
                }
            }
        }
    }

    @Override
    public String getPumlCode() {
        return getKind()+" "+getSimpleName()+" "+getElements();
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }
    public String getKind() {
        return element.getKind().toString().toLowerCase();
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

    public String getElements(){
        return "{\n"+getAttributs()+"\n"+getMethods()+"\n}";
    }

    private String getAttributs(){
        String attributsCode;
        attributsCode = "";
        for (PumlAttribut attribut:attributs) {
            attributsCode+=attribut.getPumlCode()+"\n";
        }
        return attributsCode;
    }

    private String getMethods() {
        String methodsCode;
        methodsCode = "";
        for (PumlMethod method:methods) {
            methodsCode+=method.getPumlCode()+"\n";
        }
        return methodsCode;
    }
}
