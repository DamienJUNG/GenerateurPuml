package pumlFromJava;

import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

/* Cette classe représente une méthode en puml */
public class PumlMethod implements PumlElement {
    private final PumlType type;
    //Elle dispose d'un PumlType pour représenter le type de retour de la méthode
    private final Element element;
    //Et de l'élément qu'elle doit représenter
    private final PumlAccessLevel accessLevel;
    //Mais aussi d'un PumlAccessLevel qui représente le niveau d'accès de la méthode
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

        StringBuilder parameters = new StringBuilder("(");

        for(int i=0;i<executableElement.getParameters().size();i++){
            VariableElement variableElement = executableElement.getParameters().get(i);
            PumlType pumlType = new PumlType(variableElement.asType());
            if (executableElement.getParameters().size()-1==i){
                parameters.append(variableElement.getSimpleName()).append(" : ").append(pumlType.getSimpleName());
            }
            else {
                parameters.append(variableElement.getSimpleName()).append(" : ").append(pumlType.getSimpleName()).append(", ");
            }
        }
        parameters.append(")");
        return parameters.toString();
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
                TypeElement typeElement = (TypeElement) ((DeclaredType) element.getEnclosingElement().asType()).asElement();
                for (int i=0;i<typeElement.getInterfaces().size() && superThing.equals("");i++) {
                    superThing = checkInterfaces(typeElement.getInterfaces().get(i));
                }
                if (superThing.equals("")){
                    superThing = checkSuperClasses(typeElement.getSuperclass());
                }
                return " {redefines "+superThing+"::"+getSimpleName()+"}";
            }
        }

        return "";
    }

    //Parcourt les super classe à la recherche de celle qui contient la méthode originelle
    private String checkSuperClasses(TypeMirror typeMirror){
        String superClass = "";
        TypeElement classElement = (TypeElement)((DeclaredType)typeMirror).asElement();
        for(int i=0;i<classElement.getEnclosedElements().size() && superClass.equals("");i++){
            if(getSimpleName().contentEquals(classElement.getEnclosedElements().get(i).getSimpleName())){
                superClass = classElement.getSimpleName().toString();
            }
        }
        for(int i=0;i<classElement.getInterfaces().size() && superClass.equals("");i++){
            superClass = checkInterfaces(classElement.getInterfaces().get(i));
        }
        if (superClass.equals("")){
            superClass = checkSuperClasses(classElement.getSuperclass());
        }
        return superClass;
    }

    //Parcourt les interfaces à la recherche de celle qui contient la méthode originelle
    private String checkInterfaces(TypeMirror typeMirror){
        String superInterface = "";
        TypeElement interfaceElement = (TypeElement)((DeclaredType)typeMirror).asElement();
        for(int i=0;i<interfaceElement.getEnclosedElements().size() && superInterface.equals("");i++){
            if(getSimpleName().contentEquals(interfaceElement.getEnclosedElements().get(i).getSimpleName())){
                superInterface = interfaceElement.getSimpleName().toString();
            }
        }
        for (int i=0;i<interfaceElement.getInterfaces().size() && superInterface.equals("");i++){
            superInterface = checkInterfaces(interfaceElement.getInterfaces().get(i));
        }
        return superInterface;
    }
}

