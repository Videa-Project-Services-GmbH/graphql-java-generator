/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
 * Mapper to convert types from schema to type specifications.
 */
public class TypeMapper {
    private static Logger logger = LoggerFactory.getLogger(TypeMapper.class);

    private CustomScalarMapper customScalarMapper;


    public TypeMapper(Map<String, ScalarTypeDefinition> scalars) {
        customScalarMapper = new CustomScalarMapper(scalars);
    }


    /**
     * Convert type definition to type specification.
     *
     * @param objectTypeDefinition Type definition read from schema.
     * @param packageName Package name where to put generated classes.
     * @return Type specification
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
     * Convert field definition to a field specification.
     *
     * @param fieldDefinition Field definition read from schema.
     * @param packageName Package name where to find class.
     * @return Field specification
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
     * Find out type name from given type and package name.
     *
     * @param type Type and package name
     * @return Type name to generate Java class from.
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
     * Find out list type from package name and list type itself.
     *
     * @param packageName Package name
     * @param aType List type
     * @return Type name
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
     * Define type name by using package name and type name.
     *
     * @param packageName Package name
     * @param aType Type name
     * @return Type name for Java class generation
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
