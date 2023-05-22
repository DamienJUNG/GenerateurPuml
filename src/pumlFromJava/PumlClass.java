package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import java.util.ArrayList;

public class PumlClass implements PumlElement {
    private final Element element;
    private final ArrayList<PumlAttribut> attributs = new ArrayList<>();
    private final ArrayList<PumlMethod> methods = new ArrayList<>();
    private final ArrayList<PumlRelation> relations = new ArrayList<>();
    public PumlClass(Element element){
        this.element = element;
        for (Element thing:element.getEnclosedElements()) {
            if(thing.getKind()== ElementKind.FIELD){
                if (thing.asType().getKind()!=TypeKind.DECLARED || thing.asType().toString().equals("java.lang.String")){
                    attributs.add(new PumlAttribut(thing));
                }
                else {
                    relations.add(new PumlRelation(thing));
                }
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
    public String getDccCode() {
        return getKind()+" "+getSimpleName()+getSuperClass()+getInterfaces()+" {\n"+ getDccAttributs()+"\n"+ getDccMethods()+"}\n"+ getDccRelations()+"\n";
    }

    @Override
    public String getDcaCode() {
        return getKind()+" "+getSimpleName()+getSuperClass()+getInterfaces()+" {\n"+ getDcaAttributs()+"}\n"+ getDcaRelations();
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }
    public String getKind() {
        return element.getKind().toString().toLowerCase();
    }


    private String getDccAttributs(){
        StringBuilder attributsCode;
        attributsCode = new StringBuilder();
        for (PumlAttribut attribut:attributs) {
            attributsCode.append(attribut.getDccCode()).append("\n");
        }
        return attributsCode.toString();
    }
    private String getDcaAttributs(){
        StringBuilder attributsCode;
        attributsCode = new StringBuilder();
        for (PumlAttribut attribut:attributs) {
            attributsCode.append("\t").append(attribut.getDcaCode()).append("\n");
        }
        return attributsCode.toString();
    }

    private String getDccMethods() {
        StringBuilder methodsCode;
        methodsCode = new StringBuilder();
        for (PumlMethod method:methods) {
            methodsCode.append("\t").append(method.getDccCode()).append("\n");
        }
        return methodsCode.toString();
    }
    public String getSuperClass(){
        TypeElement typeElement = (TypeElement) element;
        if (typeElement.getSuperclass()!=null && !typeElement.getSuperclass().toString().equals("java.lang.Object")){
            return " extends "+typeElement.getSuperclass().toString();
        }
        return "";
    }

    public String getInterfaces(){
        TypeElement typeElement = (TypeElement) element;
        if (!typeElement.getInterfaces().isEmpty()){
            return " implements "+typeElement.getInterfaces().toString();
        }
        return "";
    }

    public String getDccRelations(){
        StringBuilder relationsCode;
        relationsCode = new StringBuilder();
        for (PumlRelation relation:relations) {
            relationsCode.append("\t").append(relation.getDccCode()).append("\n");
        }
        return relationsCode.toString();
    }
    public String getDcaRelations(){
        StringBuilder relationsCode;
        relationsCode = new StringBuilder();
        for (PumlRelation relation:relations) {
            relationsCode.append("\t").append(relation.getDcaCode()).append("\n");
        }
        return relationsCode.toString();
    }
}
