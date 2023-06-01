package pumlFromJava;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

public class PumlType implements PumlElement{
    private final TypeMirror type;
    public PumlType(TypeMirror type){
        this.type = type;
    }

    @Override
    public String getDccCode() {
        switch (type.getKind()){
            case INT,BYTE,SHORT,LONG-> {
                return " Integer";
            }
            case FLOAT,DOUBLE -> {
                return " Real";
            }
            case BOOLEAN -> {
                return " Boolean";
            }
            case VOID -> {
                return "";
            }
            case ARRAY -> {
                PumlType arrayType = new PumlType(((ArrayType) type).getComponentType());
                return arrayType.getDccCode()+"[*]";
            }
            case DECLARED -> {
                DeclaredType declaredType = (DeclaredType) type;
                if (declaredType.getTypeArguments().size()>0){
                    int taille = declaredType.getTypeArguments().size()-1;
                    PumlType innerType = new PumlType(declaredType.getTypeArguments().get(taille));
                    if (declaredType.getEnclosingType().getKind()==TypeKind.NONE){
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
}
