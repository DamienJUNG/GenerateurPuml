package pumlFromJava;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class PumlInterface implements PumlElement {
    private final Element element;
    private final ArrayList<PumlMethod> methods = new ArrayList<>();
    public PumlInterface(Element element){
        this.element = element;
        for (Element thing:element.getEnclosedElements()) {
            methods.add(new PumlMethod(thing));
        }
    }

    @Override
    public String getPumlCode() {
        return getKind()+" "+getSimpleName()+" <<interface>> "+getElements();
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString().toLowerCase();
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
        return "{\n"+getMethods()+"\n}";
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
