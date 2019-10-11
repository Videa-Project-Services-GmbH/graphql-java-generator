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

package services.videa.graphql.java.pojos;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import graphql.language.*;
import services.videa.graphql.java.pojos.ScalarClassMapper;

import javax.lang.model.element.Modifier;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FieldMapper {

    private final String packageName;

    public FieldMapper(final String packageName) {
        this.packageName = packageName;
    }


    /**
     * Map a GraphQL language field defition returned by the schema parser to a field specification
     * used by Java Poet to generate the classes. Field definitions can contain classes of type
     * TypeName, ListType and/or NonNullType which is absolutely considered when mapping.
     *
     * @param fieldDefinition Field definition of GraphQL schema parser
     * @return FieldSpec of Java Poet to generate java classes, null in case of no class type exists yet
     */
    public FieldSpec convert(FieldDefinition fieldDefinition) {

        Type sourceType = fieldDefinition.getType();
        boolean notNullable = false;

        if (sourceType instanceof NonNullType) {
            notNullable = true;
            sourceType = ((NonNullType) fieldDefinition.getType()).getType();
        }
        com.squareup.javapoet.TypeName targetTypeName = extractType(sourceType);

        FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(
                targetTypeName, fieldDefinition.getName(), Modifier.PRIVATE);
        if (notNullable) {
            fieldSpecBuilder.addAnnotation(NotNull.class);
        }

        return fieldSpecBuilder.build();
    }


    private com.squareup.javapoet.TypeName extractType(Type type) {
        Class<?> sourceTypeNameClass = null;
        com.squareup.javapoet.TypeName targetTypeName = null;

        if (type instanceof TypeName) {
            TypeName typeName = (TypeName) type;
            try {
                sourceTypeNameClass = Class.forName(packageName + "." + typeName.getName());
            } catch (ClassNotFoundException e) {
                ScalarClassMapper scalarClassMapper = new ScalarClassMapper();
                sourceTypeNameClass = scalarClassMapper.scalarToClass(typeName.getName());
            }
            targetTypeName = com.squareup.javapoet.TypeName.get(sourceTypeNameClass);

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

    private Type extractListType(ListType listType) {
        if (listType.getType() instanceof NonNullType) {
            NonNullType nonNullType = (NonNullType) listType.getType();
            TypeName aTypeName = (TypeName) nonNullType.getType();
            return aTypeName;
        }
        TypeName aTypeName = (TypeName) listType.getType();
        return aTypeName;
    }

}
