package pumlFromJava;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;
import java.util.ArrayList;

public class PumlEnum implements PumlElement {
    private final Element element;
    private final ArrayList<PumlMethod> methods = new ArrayList<>();
    private final ArrayList<PumlEnumAttribut> attributs = new ArrayList<>();
    public PumlEnum(Element element){
        this.element = element;
        for (Element thing:element.getEnclosedElements()) {
            if(element.getKind()== ElementKind.METHOD){
                methods.add(new PumlMethod(thing));
            }
            else {
                attributs.add(new PumlEnumAttribut(thing));
            }
        }
    }

    @Override
    public String getPumlCode() {
        return getKind()+" "+getSimpleName()+" <<enum>>"+getElements();
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



    public String getMethods() {
        String methodsCode="";
        for (PumlMethod method:methods) {
            methodsCode+="\t"+method.getPumlCode()+"\n";
        }
        methodsCode+="\n";
        return methodsCode;
    }

    public String getAttributs(){
        String AttributCode="";
        for (PumlEnumAttribut attribut:attributs) {
            AttributCode+="\t"+attribut.getPumlCode()+"\n";
        }
        AttributCode+="\n";
        return AttributCode;
    }

    public String getElements() {
        return "{\n"+getAttributs()+"\n"+getMethods()+"\n}";
    }
}
