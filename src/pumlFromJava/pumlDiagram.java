package pumlFromJava;

import javax.lang.model.element.Element;
import java.util.Set;

public class pumlDiagram {
    private Set<Element> elements;
    public pumlDiagram(Set elements){
        this.elements = elements;
    }

    public Set<Element> getElements() {
        return elements;
    }
}
