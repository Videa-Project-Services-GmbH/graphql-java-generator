package services.videa.graphql.java.inputs;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
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
public class InputMapper {
    private static Logger logger = LoggerFactory.getLogger(InputMapper.class);

    private CustomScalarMapper customScalarMapper;
    private String packageName;

    public InputMapper(Map<String, ScalarTypeDefinition> scalars, String packageName) {
        customScalarMapper = new CustomScalarMapper(scalars);
        this.packageName = packageName;
    }


    public TypeSpec convert(InputObjectTypeDefinition inputObjectTypeDefinition) {
        logger.debug("inputObjectTypeDefinition: {}", inputObjectTypeDefinition);

        List<FieldSpec> fieldSpecs = inputObjectTypeDefinition.getInputValueDefinitions().stream()
                .map(this::convert)
                .collect(Collectors.toList());

        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(inputObjectTypeDefinition.getName())
                .addAnnotation(Data.class)
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecs);

        if(inputObjectTypeDefinition.getDescription() != null) {
            typeSpecBuilder.addJavadoc(inputObjectTypeDefinition.getDescription().getContent());
        }

        TypeSpec typeSpec = typeSpecBuilder.build();

        logger.debug("typeSpec: {}", typeSpec);
        return typeSpec;
    }


    private FieldSpec convert(InputValueDefinition inputValueDefinition) {
        logger.debug("inputValueDefinition: {}", inputValueDefinition);

        String typeName = typeName(inputValueDefinition.getType());

        ClassName className = BasicScalarMapper.convert(typeName);
        if (className == null) {
            className = customScalarMapper.convert(typeName);
            if(className == null) {
                className = ClassName.get(packageName, typeName);
            }
        }

        FieldSpec fieldSpec = FieldSpec.builder(className, inputValueDefinition.getName(), Modifier.PRIVATE)
                .addJavadoc("GraphQL: " + inputValueDefinition.getType() + System.getProperty("line.separator"))
                .build();

        logger.debug("fieldSpec: {}", fieldSpec);
        return fieldSpec;
    }


    public static String typeName(Type type) {
        String typeName = null;
        if (type instanceof graphql.language.TypeName) {
            typeName = ((graphql.language.TypeName) type).getName();
        } else if (type instanceof NonNullType) {
            Type aType = ((NonNullType) type).getType();
            typeName = ((graphql.language.TypeName) aType).getName();
        } else if (type instanceof ListType) {
            Type aType = ((ListType) type).getType();
            typeName = ((graphql.language.TypeName) aType).getName();
        }
        return typeName;
    }


}
