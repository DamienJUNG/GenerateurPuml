package pumlFromJava;
import javax.lang.model.element.Element;

public class PumlParameter implements PumlElement {
    private final Element element;
    public PumlParameter(Element element){
        this.element = element;
    }

    @Override
    public String getPumlCode() {
        return getSimpleName()+" : "+getType();
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }

    public String getType(){
        return "";
    }
}
