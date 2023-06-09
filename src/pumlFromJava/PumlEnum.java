package pumlFromJava;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;
import javax.lang.model.element.TypeElement;

//Cette classe représente une énumération et en produit le représentation en puml
public class PumlEnum implements PumlElement {
    private final Element element;
    //On conserve l'élément à représenter
    private final ArrayList<PumlEnumAttribut> attributs = new ArrayList<>();
    //Ainsi que les PumlEnumAttribut représentant les constantes de l'énumération
    public PumlEnum(Element element){
        this.element = element;
        for (Element thing:element.getEnclosedElements()) {
            if(thing.getKind()==ElementKind.ENUM_CONSTANT){
                attributs.add(new PumlEnumAttribut(thing));
            }
        }
    }

    @Override
    public String getDccCode() {
        return getKind()+" "+getEnclosingElement()+"."+getSimpleName()+getSuperClass()+getInterfaces()+" <<enum>> {\n"+ getDccAttributs()+"\n"+"}";
    }

    @Override
    public String getDcaCode() {
        return getKind()+" "+getEnclosingElement()+"."+getSimpleName()+getSuperClass()+" <<enum>> {\n"+ getDcaAttributs()+"}";
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
        public String getSuperClass(){
        TypeElement typeElement = (TypeElement) element;
        if (typeElement.getSuperclass()!=null && !typeElement.getSuperclass().toString().contains(getSimpleName())){
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

}
