package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;

public class PumlType implements PumlElement{
    static ArrayList<String> primitiveObjects = new ArrayList<>();
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
                if (declaredType.getTypeArguments().size()>0){
                    int taille = declaredType.getTypeArguments().size()-1;
                    PumlType innerType = new PumlType(declaredType.getTypeArguments().get(taille));
                    if (declaredType.getEnclosingType().getKind()==TypeKind.NONE && !innerType.getDccCode().contains("[*]")){
                        return innerType.getDccCode()+"[*]";
                    }
                    return innerType.getDccCode();
                }
                else if(!type.toString().equals("java.lang.String")){
                    return declaredType.asElement().asType().toString();
                }
                else {
                    return "String";
                }
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
        return type.toString();
    }
    public String getLongName(){
        return getDccCode().replace("[*]", "");
    }
    public boolean isPrimitive(){
        if (type.getKind().isPrimitive()){
            return true;
        }

        if (type.getKind()==TypeKind.DECLARED){
            DeclaredType declaredType = (DeclaredType) type;
            if (declaredType.getTypeArguments().size()>0){
                int taille = declaredType.getTypeArguments().size()-1;
                PumlType innerType = new PumlType(declaredType.getTypeArguments().get(taille));
                System.out.println("ici !! "+innerType.getSimpleName());
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
}
