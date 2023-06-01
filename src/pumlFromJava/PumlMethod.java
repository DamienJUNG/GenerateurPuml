package pumlFromJava;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;

public class PumlMethod implements PumlElement {
    private final PumlType type;
    private final Element element;
    private final PumlAccessLevel accessLevel;
    public PumlMethod(Element element){

        this.element = element;
        ExecutableElement executableElement = (ExecutableElement) element;
        this.type = new PumlType(executableElement.getReturnType());
        accessLevel = new PumlAccessLevel(element.getModifiers());
    }

    @Override
    public String getDccCode() {
        return getAccessLevel()+" "+getSimpleName()+ getParameters()+getType()+getOthersModifiers()+getAnotation();
    }

    @Override
    public String getDcaCode() {
        return null;
    }
        public String getEnclosingElement(){
        int index = element.getEnclosingElement().toString().lastIndexOf(".")+1;
        return element.getEnclosingElement().toString().substring(index);
    }

    @Override
    public String getSimpleName() {
        return element.getSimpleName().toString();
    }

    public String getType(){
        if (type.getDccCode().equals("")){
            return "";
        }
        return " : "+type.getDccCode();
    }

    public String getAccessLevel() {
        return accessLevel.getDccCode();
    }
    public String getParameters() {

        ExecutableElement executableElement = (ExecutableElement) element;
        if(executableElement.getParameters().size() == 0) {
            return "()";
        }
        String renvoi = "(";


        int i = executableElement.getParameters().size();
        for (VariableElement parameter:executableElement.getParameters()) {
            i--;
            String type = "";

            int index = 0;
            //System.out.println(element.getSimpleName()+" "+parameter.getSimpleName());
            if(parameter.asType().toString().contains(".")) {
                index = parameter.asType().toString().lastIndexOf(".") + 1;
            }
            //
            if(parameter.asType().getKind() == TypeKind.INT || parameter.asType().getKind() == TypeKind.BYTE || parameter.asType().getKind() == TypeKind.SHORT || parameter.asType().getKind() == TypeKind.LONG)
            {
                 type += "Integer";
            }
            else if(parameter.asType().getKind() == TypeKind.FLOAT || parameter.asType().getKind() == TypeKind.DOUBLE)
            {
                type += "Real";
            }

            else if(parameter.asType().getKind() == TypeKind.BOOLEAN)
            {
                type += "Boolean";
            }

            else {
                type = parameter.asType().toString().substring(index);
            }

            renvoi += parameter.getSimpleName().toString() +" : "+ type;

            if(i != 0) {
                renvoi += ", ";
            }
        }
        renvoi += ")";

        return renvoi;
    }

    public String getOthersModifiers(){
        String modifiers = "";
        if(element.getModifiers().contains(Modifier.ABSTRACT)){
            modifiers+=" {abstract}";
        }
        if(element.getModifiers().contains(Modifier.STATIC)){
            modifiers+=" {static}";
        }
        return modifiers;
    }
    public String getAnotation() {
        int index = element.getAnnotationMirrors().toString().lastIndexOf(".")+1;

        for(AnnotationMirror annotationMirror : element.getAnnotationMirrors())
        {
            if(element.getAnnotationMirrors().toString().substring(index).equals(annotationMirror.getAnnotationType().asElement().getSimpleName().toString()))
            {
                return " {redefines "+"::"+getSimpleName()+"}";
            }
        }

        return "";
    }
}

