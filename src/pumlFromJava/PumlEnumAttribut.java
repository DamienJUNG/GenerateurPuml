package pumlFromJava;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Element;
public class PumlEnumAttribut implements PumlElement {
    private final Element element;
    public PumlEnumAttribut(Element element){
        this.element = element;
    }

    @Override
    public String getPumlCode() {
        return getSimpleName();
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString().toUpperCase();
    }
}
