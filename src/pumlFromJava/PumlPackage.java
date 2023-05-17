package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;

public class PumlPackage implements PumlElement {
    private final Element element;
    private final ArrayList<PumlElement> elements = new ArrayList<>();
    public PumlPackage(Element element){

        this.element = element;
        for (Element thing:element.getEnclosedElements()) {
            if(thing.getKind()==ElementKind.CLASS){
                elements.add(new PumlClass(thing));
            }
            else if(thing.getKind()== ElementKind.INTERFACE){
                elements.add(new PumlInterface(thing));
            }
            else if (thing.getKind()==ElementKind.ENUM){
                elements.add(new PumlEnum(thing));
            }
            else if(thing.getKind()==ElementKind.PACKAGE){
                elements.add(new PumlPackage(thing));
            }
        }
    }

    @Override
    public String getDccCode() {
        return getKind()+" "+getSimpleName()+ getDccElements();
    }

    @Override
    public String getDcaCode() {
        return getKind()+" "+getSimpleName()+ getDcaElements();
    }

    public String getKind(){
        return element.getKind().toString().toLowerCase();
    }

    @Override
    public String getSimpleName() {
        return element.asType().toString();
    }
    public String getDccElements(){
        StringBuilder elementsCode;
        elementsCode = new StringBuilder("{\n");
        for (PumlElement thing:elements) {
            elementsCode.append(thing.getDccCode()).append("\n");
        }
        elementsCode.append("}\n");
        return elementsCode.toString();
    }
    public String getDcaElements(){
        StringBuilder elementsCode;
        elementsCode = new StringBuilder("{\n");
        for (PumlElement thing:elements) {
            elementsCode.append(thing.getDcaCode()).append("\n");
        }
        elementsCode.append("}\n");
        return elementsCode.toString();
    }
}
