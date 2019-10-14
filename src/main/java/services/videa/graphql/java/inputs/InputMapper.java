/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
