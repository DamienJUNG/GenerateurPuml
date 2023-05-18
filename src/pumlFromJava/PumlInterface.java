package pumlFromJava;
import javax.lang.model.element.Element;
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
    public String getDccCode() {
        return getKind()+" "+getSimpleName()+" <<interface>> "+getElements();
    }
    @Override
    public String getDcaCode() {
       return getKind()+" "+getSimpleName()+" <<interface>> {}";
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }
    public String getKind() {
        return element.getKind().toString().toLowerCase();
    }

    public String getElements(){
        return "{\n"+getMethods()+"}";
    }

    private String getMethods() {
        StringBuilder methodsCode;
        methodsCode = new StringBuilder();
        for (PumlMethod method:methods) {
            methodsCode.append("\t").append(method.getDccCode()).append("\n");
        }
        return methodsCode.toString();
    }
}
