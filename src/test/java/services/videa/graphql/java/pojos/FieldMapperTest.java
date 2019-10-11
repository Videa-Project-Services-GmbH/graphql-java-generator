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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import graphql.language.*;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
public class FieldMapperTest {

    private FieldMapper fieldMapper = new FieldMapper("de.teilautos.graphql.mapping");

    @Test
    public void typeName() {
        Type typeName = new TypeName("UserNodeFake");
        FieldDefinition fieldDefinition = new FieldDefinition("fieldDefinition", typeName);

        FieldSpec fieldSpec = fieldMapper.convert(fieldDefinition);

        assertNotNull(fieldSpec.type);
        ClassName className = (ClassName) fieldSpec.type;
        assertNotNull(className);
        assertEquals("UserNodeFake", className.simpleName());
    }

    @Test
    public void listType() {
        Type typeName = new TypeName("UserNodeFake");
        Type listType = new ListType(typeName);

        FieldDefinition fieldDefinition = new FieldDefinition("fieldDefinition", listType);

        FieldSpec fieldSpec = fieldMapper.convert(fieldDefinition);

        assertNotNull(fieldSpec.type);
        ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) fieldSpec.type;
        ClassName typeClass = (ClassName) parameterizedTypeName.typeArguments.get(0);
        assertNotNull(typeClass);
        assertEquals("UserNodeFake", typeClass.simpleName());
    }

    @Test
    public void nonNullType_typeName() {
        Type typeName = new TypeName("UserNodeFake");
        Type nonNullType = new NonNullType(typeName);

        FieldDefinition fieldDefinition = new FieldDefinition("fieldDefinition", nonNullType);

        FieldSpec fieldSpec = fieldMapper.convert(fieldDefinition);

        assertNotNull(fieldSpec.type);
        ClassName className = (ClassName) fieldSpec.type;
        assertNotNull(className);
        assertEquals("UserNodeFake", className.simpleName());
    }

    @Test
    public void nonNullType_listType_typeName() {
        Type typeName = new TypeName("UserNodeFake");
        Type listType = new ListType(typeName);
        Type nonNullType = new NonNullType(listType);

        FieldDefinition fieldDefinition = new FieldDefinition("fieldDefinition", nonNullType);

        FieldSpec fieldSpec = fieldMapper.convert(fieldDefinition);

        assertNotNull(fieldSpec.type);
        ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) fieldSpec.type;
        ClassName typeClass = (ClassName) parameterizedTypeName.typeArguments.get(0);
        assertNotNull(typeClass);
        assertEquals("UserNodeFake", typeClass.simpleName());
    }

}
