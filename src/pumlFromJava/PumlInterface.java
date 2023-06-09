package pumlFromJava;
import javax.lang.model.element.Element;
import java.util.ArrayList;
import javax.lang.model.element.TypeElement;

//Cette classe représente une interface et en produit le code puml
public class PumlInterface implements PumlElement {
    private final Element element;
    //On y stocke l'élément à représenter
    private final ArrayList<PumlMethod> methods = new ArrayList<>();
    //Mais aussi tous les PumlMethod représentant les méthodes de cette interface
    public PumlInterface(Element element){
        this.element = element;
        for (Element thing:element.getEnclosedElements()) {
            methods.add(new PumlMethod(thing));
        }
    }

    @Override
    public String getDccCode() {
        return getKind()+" "+getEnclosingElement()+"."+getSimpleName()+" <<interface>> "+getSuperClass()+" {\n"+getMethods()+"}\n";
    }
    @Override
    public String getDcaCode() {
       return getKind()+" "+getEnclosingElement()+"."+getSimpleName()+" <<interface>> "+getSuperClass()+" {}\n";
    }
    public String getEnclosingElement(){
        return element.getEnclosingElement().toString();
    }
    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }
    public String getKind() {
        return element.getKind().toString().toLowerCase();
    }


    private String getMethods() {
        StringBuilder methodsCode;
        methodsCode = new StringBuilder();
        for (PumlMethod method:methods) {
            methodsCode.append("\t").append(method.getDccCode()).append("\n");
        }
        return methodsCode.toString();
    }
    public String getSuperClass(){
        TypeElement typeElement = (TypeElement) element;
        if (typeElement.getInterfaces().size()>0){
            return " extends "+typeElement.getInterfaces().toString();
        }
        return "";
    }
}
