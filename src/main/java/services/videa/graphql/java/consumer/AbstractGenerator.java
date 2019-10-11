package services.videa.graphql.java.consumer;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import graphql.language.*;

import java.io.File;
import java.io.IOException;

abstract class AbstractGenerator {

    private static final String GENERATION_FOLDER = "./src/main/java";

    static final String NEWLINE = System.getProperty("line.separator");

    String targetPackageName = "de.teilautos.graphql.zemtu.model";


    /**
     *
     */
    Class<?> toTypeClazz(String clazzName, String packageName) {
        try {
            return Class.forName(packageName + "." + clazzName);
        } catch (ClassNotFoundException e) {
            // if no object or input type can be found a scalar type will be considered
            Class<?> aClass = toScalarClass(clazzName);
            if (aClass != null) {
                return aClass;
            }
            return String.class;
        }
    }


    private Class<?> toScalarClass(String scalarName) {
        Class<?> clazz = null;
        switch (scalarName) {
            case "Float":
                clazz = Double.class;
                break;
            case "Int":
                clazz = Integer.class;
                break;
            case "Boolean":
                clazz = Boolean.class;
                break;
            case "ID":
            case "String":
                clazz = String.class;
                break;
            default:
                break;
        }
        return clazz;
    }


    String extractTypeName(Type type) {
        String typeName = null;
        if (type instanceof TypeName) {
            typeName = ((TypeName) type).getName();

        } else if (type instanceof NonNullType) {
            Type aType = ((NonNullType) type).getType();
            typeName = ((TypeName) aType).getName();
        } else if (type instanceof ListType) {
            Type aType = ((ListType) type).getType();
            typeName = ((TypeName) aType).getName();
        }
        return typeName;
    }


    String extractComment(Description description) {
        return (description != null ? description.getContent() : "") + NEWLINE;
    }


    void writeClass(TypeSpec typeSpec) {
        JavaFile javaFile = JavaFile.builder(targetPackageName, typeSpec).build();
        File file = new File(GENERATION_FOLDER);
        try {
            javaFile.writeTo(file);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }


}
