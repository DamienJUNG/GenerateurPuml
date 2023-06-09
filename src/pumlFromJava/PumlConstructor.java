package pumlFromJava;

import javax.lang.model.element.Element;

//Cette classe hérite de la classe PumlMethod et représente plus spécifiquement les constructeurs
public class PumlConstructor extends PumlMethod {
    public PumlConstructor(Element element){
        super(element);
    }
    @Override
    public String getDccCode() {
        return super.getAccessLevel()+" <<Create>> "+super.getEnclosingElement()+super.getParameters();
    }
}
