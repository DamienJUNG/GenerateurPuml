package pumlFromJava;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;

public class PumlMethod implements PumlElement {
    private final PumlType type;
    private final Element element;
    public PumlMethod(Element element){

        this.element = element;
        ExecutableElement executableElement = (ExecutableElement) element;
        this.type = new PumlType(executableElement.getReturnType());
    }

    @Override
    public String getDccCode() {
        return getAccessLevel()+" "+getSimpleName()+ getParameters()+getType()+getOthersModifiers();
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
        return type.getDccCode();
    }

    public String getAccessLevel() {
        if(element.getModifiers().contains(Modifier.PUBLIC)){
            return "+";
        }
        else if(element.getModifiers().contains(Modifier.PROTECTED)){
            return "#";
        }
        else{
            return "-";
        }
    }
    public String getParameters(){

        String revoi = "(";
        ExecutableElement executableElement = (ExecutableElement) element;

        if(executableElement.getParameters().size() == 0) {
            return "()";
        }

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
            /*
            if (parameter.asType().getKind() == TypeKind.DECLARED){
                element = ((DeclaredType) parameter.asType()).asElement();
            }*/
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

            revoi += parameter.getSimpleName().toString() +" : "+ type;

            if(i != 0) {
                revoi += ", ";
            }
        }
        revoi += ")";

        return revoi;
        /*StringBuilder str = new StringBuilder(element.asType().toString());
        return str.subSequence(0,str.indexOf(")")+1).toString();*/
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
}

