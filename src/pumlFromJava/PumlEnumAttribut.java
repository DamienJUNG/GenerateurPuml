package pumlFromJava;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Element;

//Cette classe représente les constantes des énumérations
public class PumlEnumAttribut implements PumlElement {
    private final Element element;
    //On y stocke bien entendu la constante elle-même
    public PumlEnumAttribut(Element element){
        this.element = element;
    }

    @Override
    public String getDccCode() {
        return getSimpleName();
    }

    @Override
    public String getDcaCode() {
        return getDccCode();
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString().toUpperCase();
    }
}
