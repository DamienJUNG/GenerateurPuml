package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.*;
import java.util.ArrayList;

/* Cette classe représente un type en plantuml.
 * Elle permet de renvoyer la représentation d'un type standardisé
 * ou un type personnalisé en plantuml */
public class PumlType implements PumlElement{
    static ArrayList<String> primitiveObjects = new ArrayList<>();
    /* Liste qui représente les types primitifs
     * considérés comme non-primitifs par l'api java.lang.model */
    static {
        primitiveObjects.add("java.lang.String");
        primitiveObjects.add("java.lang.Integer");
        primitiveObjects.add("java.lang.Boolean");
        primitiveObjects.add("java.lang.Character");
        primitiveObjects.add("java.lang.Double");
        primitiveObjects.add("java.lang.Float");
        primitiveObjects.add("java.lang.Byte");
        primitiveObjects.add("java.lang.Long");
        primitiveObjects.add("java.lang.Short");
    }
    private final TypeMirror type;
    //On stocke le type à représenter en puml
    public PumlType(TypeMirror type){
        this.type = type;
    }

    @Override
    public String getDccCode() {
        switch (type.getKind()){
            case INT,BYTE,SHORT,LONG-> {
                return "Integer";
            }
            case FLOAT,DOUBLE -> {
                return "Real";
            }
            case BOOLEAN -> {
                return "Boolean";
            }
            case CHAR -> {
                return "String";
            }
            case VOID -> {
                return "";
            }
            case ARRAY -> {
                PumlType arrayType = new PumlType(((ArrayType) type).getComponentType());
                if (!arrayType.getDccCode().contains("[*]"))
                {
                    return arrayType.getDccCode()+"[*]";
                }
                return arrayType.getDccCode();
            }
            case DECLARED -> {
                DeclaredType declaredType = (DeclaredType) type;
                //S'il contient plusieurs types
                if (declaredType.getTypeArguments().size()>0){
                    int taille = declaredType.getTypeArguments().size()-1;
                    PumlType innerType = new PumlType(declaredType.getTypeArguments().get(taille));
                    if (declaredType.getEnclosingType().getKind()==TypeKind.NONE && !innerType.getDccCode().contains("[*]")){
                        return innerType.getDccCode()+"[*]";
                    }
                    return innerType.getDccCode();
                }
                //Sinon si ce n'est pas type java.lang....
                else if(!primitiveObjects.contains(type.toString())){
                    return declaredType.asElement().asType().toString();
                }
                //Sinon, on a juste à le traduire
                else {
                    PumlType realType = new PumlType(declaredType);
                    return realType.translateToPuml();
                }
            }
            case EXECUTABLE -> {
                ExecutableType executableType = (ExecutableType)type;
                PumlType pumlType = new PumlType(executableType.getReturnType());
                return pumlType.getDccCode();
            }
            default -> {
            int index = type.toString().lastIndexOf(".")+1;
            return type.toString().substring(index);
            }
        }

    }

    @Override
    public String getDcaCode() {
        return null;
    }

    @Override
    public String getSimpleName() {
        if (getDccCode().contains(".")){
            String type = getDccCode();
            int index = type.lastIndexOf(".")+1;
            return type.substring(index);
        }
        else {
            return getDccCode();
        }
    }
    //Permet d'obtenir le nom simple dy type
    public String getLongName(){
        return getDccCode().replace("[*]", "");
    }

    //Vérifie si le type fait partie des types primitifs uml
    public boolean isPrimitive(){
        if (type.getKind().isPrimitive()){
            return true;
        }

        if (type.getKind()==TypeKind.DECLARED){
            DeclaredType declaredType = (DeclaredType) type;
            if (declaredType.getTypeArguments().size()>0){
                int taille = declaredType.getTypeArguments().size()-1;
                PumlType innerType = new PumlType(declaredType.getTypeArguments().get(taille));
                return innerType.isPrimitive();
            }
        }

        if (type.getKind() == TypeKind.ARRAY){
            PumlType arrayType = new PumlType(((ArrayType) type).getComponentType());
            return arrayType.isPrimitive();
        }

        for (String str:PumlType.primitiveObjects) {
            if(type.toString().contains(str)){
                return true;
            }
        }
        return false;
    }

    /* "Traduit" un type java.lang.... en un type uml standardisé */
    private String translateToPuml(){
        switch (type.toString()){
            case "java.lang.String","java.lang.Character" -> {
                return "String";
            }
            case "java.lang.Integer","java.lang.Byte","java.lang.Long","java.lang.Short" -> {
                return "Integer";
            }
            case "java.lang.Double","java.lang.Float" -> {
                return "Real";
            }
            case "java.lang.Boolean" -> {
                return "Boolean";
            }
        }
        return "???";
        //Si on ne sait vraiment pas de quel type il s'agit (ce n'est pas censé arriver)
    }
}
