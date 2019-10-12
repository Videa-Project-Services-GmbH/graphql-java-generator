package services.videa.graphql.java.types;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import graphql.language.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.videa.graphql.java.scalars.BasicScalarMapper;
import services.videa.graphql.java.scalars.CustomScalarMapper;

import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *
 */
public class TypeMapper {
    private static Logger logger = LoggerFactory.getLogger(TypeMapper.class);

    private CustomScalarMapper customScalarMapper;


    public TypeMapper(Map<String, ScalarTypeDefinition> scalars) {
        customScalarMapper = new CustomScalarMapper(scalars);
    }


    /**
     *
     * @param objectTypeDefinition
     * @param packageName
     * @return
     */
    public TypeSpec convert(ObjectTypeDefinition objectTypeDefinition, String packageName) {
        logger.debug("objectTypeDefinition: {}", objectTypeDefinition);

        List<FieldSpec> fieldSpecs = objectTypeDefinition.getFieldDefinitions().stream()
                .map(fieldDefinition -> convert(fieldDefinition, packageName))
                .collect(Collectors.toList());

        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(objectTypeDefinition.getName())
                .addAnnotation(Data.class)
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecs);

        if (objectTypeDefinition.getDescription() != null) {
            typeSpecBuilder.addJavadoc(objectTypeDefinition.getDescription().getContent());
        }

        objectTypeDefinition.getImplements().forEach(element -> {
            TypeName interfaceType = (TypeName) element;
            typeSpecBuilder.addSuperinterface(ClassName.get(packageName, interfaceType.getName()));
        });

        TypeSpec typeSpec = typeSpecBuilder.build();

        logger.debug("typeSpec: {}", typeSpec);
        return typeSpec;
    }


    /**
     *
     * @param fieldDefinition
     * @param packageName
     * @return
     */
    private FieldSpec convert(FieldDefinition fieldDefinition, String packageName) {
        logger.debug("fieldDefinition: {}", fieldDefinition);

        com.squareup.javapoet.TypeName typeName = typeName(fieldDefinition.getType(), packageName);

        FieldSpec fieldSpec = FieldSpec.builder(typeName, fieldDefinition.getName(), Modifier.PRIVATE)
                .addJavadoc("GraphQL: " + fieldDefinition.getType() + System.getProperty("line.separator"))
                .build();

        logger.debug("fieldSpec: {}", fieldSpec);
        return fieldSpec;
    }


    /**
     * @param type
     * @return
     */
    public com.squareup.javapoet.TypeName typeName(Type type, String packageName) {
        com.squareup.javapoet.TypeName typeName;

        if (type instanceof TypeName) {
            // No further type definition as TypeName is the very last type in tree.
            typeName = defineTypeName(packageName, (TypeName) type);

        } else if (type instanceof NonNullType) {
            // NonNullType can have TypeName and ListType embedded.
            Type aType = ((NonNullType) type).getType();

            if (aType instanceof TypeName) {
                // As above no further type definition as TypeName is the very last type in tree.
                typeName = defineTypeName(packageName, (TypeName) aType);

            } else if (aType instanceof ListType) {
                // Recursive call of type definition as further types embedded.
                typeName = defineListType(packageName, (ListType) aType);

            } else {
                throw new IllegalStateException("Embedded type '" + aType + "' cannot be unwrapped.");
            }

        } else if (type instanceof ListType) {
            // Yet another recursive call for outer list.
            typeName = defineListType(packageName, (ListType) type);

        } else {
            throw new IllegalStateException("Type '" + type + "' cannot be unwrapped.");
        }

        logger.debug("typeName: {}", typeName);
        return typeName;
    }


    /**
     *
     * @param packageName
     * @param aType
     * @return
     */
    private com.squareup.javapoet.TypeName defineListType(String packageName, ListType aType) {
        com.squareup.javapoet.TypeName typeName;
        com.squareup.javapoet.TypeName parameterizedType = typeName(aType.getType(), packageName);
        typeName = ParameterizedTypeName.get(
                ClassName.get("java.util", "List"),
                parameterizedType);
        return typeName;
    }


    /**
     *
     * @param packageName
     * @param aType
     * @return
     */
    private com.squareup.javapoet.TypeName defineTypeName(String packageName, TypeName aType) {
        com.squareup.javapoet.TypeName typeName;
        String aTypeName = aType.getName();
        typeName = BasicScalarMapper.convert(aTypeName);
        if (typeName == null) {
            typeName = customScalarMapper.convert(aTypeName);
            if(typeName == null) {
                typeName = ClassName.get(packageName, aTypeName);
            }
        }
        return typeName;
    }


}
