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
            System.out.println(thing.getSimpleName()+" : "+thing.getKind());
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
    public String getPumlCode() {
        return getKind()+" "+getSimpleName()+getElements();
    }
    public String getKind(){
        return element.getKind().toString().toLowerCase();
    }

    @Override
    public String getSimpleName() {
        return element.asType().toString();
    }
    public String getElements(){
        StringBuilder elementsCode;
        elementsCode = new StringBuilder("{\n");
        for (PumlElement thing:elements) {
            elementsCode.append(thing.getPumlCode()).append("\n");
        }
        elementsCode.append("}\n");
        return elementsCode.toString();
    }
}
