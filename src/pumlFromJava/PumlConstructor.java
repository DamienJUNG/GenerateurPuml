package pumlFromJava;

import javax.lang.model.element.Element;

public class PumlConstructor extends PumlMethod {
    public PumlConstructor(Element element){
        super(element);
    }
    @Override
    public String getPumlCode() {
        return super.getAccessLevel()+" <<Create>> "+super.getSimpleName()+super.getAttributs();
    }
}
