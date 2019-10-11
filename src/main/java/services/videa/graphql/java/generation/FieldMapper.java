/*
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 *  BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 *  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package services.videa.graphql.java.generation;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import graphql.language.*;

import javax.lang.model.element.Modifier;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class FieldMapper {

    private final String packageName;
    private final ScalarMapper scalarMapper;
    private final Map<String, ScalarTypeDefinition> scalars;


    FieldMapper(Map<String, ScalarTypeDefinition> scalars, ScalarMapper scalarMapper, final String packageName) {
        this.scalars = scalars;
        this.scalarMapper = scalarMapper;
        this.packageName = packageName;
    }


    /**
     * Map a GraphQL language field definition returned by the schema parser to a field specification
     * used by Java Poet to generate the classes. Field definitions can contain classes of type
     * TypeName, ListType and/or NonNullType which is absolutely considered when mapping.
     *
     * @param fieldDefinition Field definition of GraphQL schema parser
     * @return FieldSpec of Java Poet to generate java classes, null in case of no class type exists yet
     */
    FieldSpec convert(FieldDefinition fieldDefinition) {
        return convert(fieldDefinition.getType(), fieldDefinition.getName());
    }


    /**
     * @param inputValueDefinition
     * @return
     */
    public FieldSpec convert(InputValueDefinition inputValueDefinition) {
        return convert(inputValueDefinition.getType(), inputValueDefinition.getName());
    }


    /**
     *
     * @param sourceType
     * @param definitionName
     * @return
     */
    private FieldSpec convert(Type sourceType, String definitionName) {
        boolean notNullable = false;

        if (sourceType instanceof NonNullType) {
            notNullable = true;
            sourceType = ((NonNullType) sourceType).getType();
        }
        com.squareup.javapoet.TypeName targetTypeName = extractType(sourceType);

        if(targetTypeName != null) {
            FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(
                    targetTypeName, definitionName, Modifier.PRIVATE);
            if (notNullable) {
                fieldSpecBuilder.addAnnotation(NotNull.class);
            }
            return fieldSpecBuilder.build();
        }

        return null;
    }


    /*
     *
     */
    private com.squareup.javapoet.TypeName extractType(Type type) {
        Class<?> sourceTypeNameClass = null;
        com.squareup.javapoet.TypeName targetTypeName = null;

        if (type instanceof TypeName) {
            TypeName typeName = (TypeName) type;
            try {
                sourceTypeNameClass = Class.forName(packageName + "." + typeName.getName());
            } catch (ClassNotFoundException e) {
                if (scalars.containsKey(typeName.getName())) {
                    sourceTypeNameClass = String.class;
                } else {
                    sourceTypeNameClass = scalarMapper.convert(typeName.getName());
                }
            }
            if(sourceTypeNameClass != null) {
                targetTypeName = com.squareup.javapoet.TypeName.get(sourceTypeNameClass);
            }

        } else if (type instanceof NonNullType) {
            Type aType = ((NonNullType) type).getType();
            targetTypeName = extractType(aType);

        } else if (type instanceof ListType) {
            Type aType = ((ListType) type).getType();
            if (aType instanceof TypeName) {
                com.squareup.javapoet.TypeName typeName = extractType(aType);
                targetTypeName = ParameterizedTypeName.get(com.squareup.javapoet.ClassName.get(List.class), typeName);
            } else {
                targetTypeName = extractType(aType);
            }
        }

        return targetTypeName;
    }


}
