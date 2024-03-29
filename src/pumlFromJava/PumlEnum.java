package pumlFromJava;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;

public class PumlEnum implements PumlElement {
    private final Element element;
    private final ArrayList<PumlMethod> methods = new ArrayList<>();
    private final ArrayList<PumlEnumAttribut> attributs = new ArrayList<>();
    public PumlEnum(Element element){
        this.element = element;
        for (Element thing:element.getEnclosedElements()) {
            if(thing.getKind()==ElementKind.ENUM_CONSTANT){
                System.out.println(element.getSimpleName());
                attributs.add(new PumlEnumAttribut(thing));
            }
            else{
                methods.add(new PumlMethod(thing));
            }
        }
    }

    @Override
    public String getDccCode() {
        return getKind()+" "+getSimpleName()+" <<enum>> {\n"+ getDccAttributs()+"\n"+ getDccMethods()+"}";
    }

    @Override
    public String getDcaCode() {
        return getKind()+" "+getSimpleName()+" <<enum>> {\n"+ getDcaAttributs()+"}";
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }
    public String getKind() {
        return element.getKind().toString().toLowerCase();
    }



    public String getDccMethods() {
        StringBuilder methodsCode= new StringBuilder();
        for (PumlMethod method:methods) {
            methodsCode.append("\t").append(method.getDccCode()).append("\n");
        }
        methodsCode.append("\n");
        return methodsCode.toString();
    }

    public String getDccAttributs(){
        StringBuilder AttributCode= new StringBuilder();
        for (PumlEnumAttribut attribut:attributs) {
            AttributCode.append("\t").append(attribut.getDccCode()).append("\n");
        }
        AttributCode.append("\n");
        return AttributCode.toString();
    }
     public String getDcaAttributs(){
        return getDccAttributs();
    }

}
