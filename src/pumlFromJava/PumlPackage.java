package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;

//Cette classe représente un package et permet d'en construire la représentation en puml
public class PumlPackage implements PumlElement {
    private final Element element; //Elle possède l'élément qu'elle représente
    private final ArrayList<PumlElement> elements = new ArrayList<>();
    //Et dispose de la liste des PumlElement qui représentent les sous éléments du package
    public PumlPackage(Element element){

        this.element = element;
        /* Pour chaque élément contenu dans l'élément représenté
         * On instancie un PumlElement correspondant à l'élément à représenter */
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
        return getDccElements();
    }

    @Override
    public String getDcaCode() {
        return getDcaElements();
    }

    public String getKind(){
        return element.getKind().toString().toLowerCase();
    }

    @Override
    public String getSimpleName() {
        return element.asType().toString();
    }
    public String getDccElements(){
        StringBuilder elementsCode=new StringBuilder("\n");
        for (PumlElement thing:elements) {
            elementsCode.append(thing.getDccCode()).append("\n");
        }
        return elementsCode.toString();
    }
    public String getDcaElements(){
        StringBuilder elementsCode = new StringBuilder("\n");
        for (PumlElement thing:elements) {
            elementsCode.append(thing.getDcaCode()).append("\n");
        }
        return elementsCode.toString();
    }
}
