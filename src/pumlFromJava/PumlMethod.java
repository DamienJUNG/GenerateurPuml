package pumlFromJava;

import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

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
        return getAccessLevel()+" "+getSimpleName()+ getParameters()+getType()+getOthersModifiers()+ getAnnotation();
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
        if(element.getModifiers().contains(Modifier.FINAL)){
            modifiers+=" {ReadOnly}";
        }
        return modifiers;
    }
    public String getAnnotation() {
        for(AnnotationMirror annotationMirror : element.getAnnotationMirrors())
        {
            if(annotationMirror.getAnnotationType().asElement().getSimpleName().toString().equals("Override"))
            {
                String superThing="";
                /*boolean redefines = false;
                while(!redefines){
                      TypeElement superElement = (TypeElement) ((DeclaredType) element.getEnclosingElement().asType()).asElement();
                      for(TypeMirror typeMirror : superElement.getInterfaces()){
                          TypeElement superInterface = ((TypeElement)(((DeclaredType)typeMirror).asElement()));
                          while (!superInterface.getInterfaces().isEmpty()){
                              for (Element element:superInterface.getEnclosedElements()) {
                                  if(element.getSimpleName().toString().equals(getSimpleName())){
                                  superThing = element.getEnclosingElement().toString();
                                  redefines=true;
                                  }
                              }
                          }
                      }
                      while (!redefines){
                          for (Element element : ((DeclaredType)(superElement.getSuperclass())).asElement().getEnclosedElements()){
                              if(element.getSimpleName().toString().equals(getSimpleName())){
                                  superThing = element.getEnclosingElement().toString();
                                  redefines=true;
                              }
             <             }
                          if(!superElement.getSuperclass().toString().equals("java.lang.Object")){
                              System.out.println(getSimpleName()+" "+superElement.getEnclosingElement());
                              superElement = (TypeElement) ((DeclaredType) superElement.getEnclosingElement().asType()).asElement();
                          }
                          else {
                              superThing="java.lang.Object";
                              redefines=true;
                          }
                      }
                }*/
                return " {redefines "+superThing+"::"+getSimpleName()+"}";
            }
        }

        return "";
    }
}

