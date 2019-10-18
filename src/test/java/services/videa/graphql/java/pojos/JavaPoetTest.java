/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.pojos;

import com.squareup.javapoet.*;
import lombok.Data;
import org.junit.Test;

import javax.lang.model.element.Modifier;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

public class JavaPoetTest {

    @Test
    public void createEnum() throws IOException {
        TypeSpec clazz = TypeSpec.enumBuilder("ReservationActionTypeEnum")
                .addEnumConstant("ACCESS_RESERVATION")
                .addEnumConstant("FINISH_RESERVATION")
                .addEnumConstant("LOCK_VEHICLE")
                .addEnumConstant("UNLOCK_VEHICLE")
                .build();

        JavaFile javaFile = JavaFile.builder("services.videa.graphql.java.pojos", clazz)
                .build();

        javaFile.writeTo(System.out);

    }
    @Test
    public void createSampleClazz() throws IOException {
        FieldSpec reservationId = FieldSpec.builder(String.class, "reservationId")
                .addAnnotation(NotNull.class)
                .addModifiers(Modifier.PRIVATE)
                .build();

        FieldSpec clientMutationId = FieldSpec.builder(String.class, "clientMutationId")
                .addModifiers(Modifier.PRIVATE)
                .build();

        TypeSpec clazz = TypeSpec.classBuilder("AccZemtuInput")
                .addAnnotation(Data.class)
                .addModifiers(Modifier.PUBLIC)
                .addField(reservationId)
                .addField(clientMutationId)
                .build();

        JavaFile javaFile = JavaFile.builder("services.videa.graphql.java.pojos", clazz)
                .build();

        javaFile.writeTo(System.out);
    }

    @Test
    public void createListField() throws IOException {
        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(
                ClassName.get(List.class), TypeName.get(Integer.class));
        FieldSpec fieldSpec = FieldSpec.builder(parameterizedTypeName, "fieldList", Modifier.PRIVATE).build();
        TypeSpec typeSpec = TypeSpec.classBuilder("ListField")
                .addField(fieldSpec)
                .build();
        JavaFile javaFile = JavaFile.builder("services.videa.graphql.java.pojos", typeSpec).build();
        javaFile.writeTo(System.out);
    }


    @Test
    public void className() throws IOException {
        ClassName className = ClassName.get(
                "services.videa.graphql.java.types", "JavaPoetTest");
        FieldSpec fieldSpec = FieldSpec.builder(className, "field", Modifier.PRIVATE).build();

        TypeSpec typeSpec = TypeSpec.classBuilder("Feld")
                .addField(fieldSpec)
                .build();

        JavaFile javaFile = JavaFile.builder("services.videa.graphql.java.pojos", typeSpec).build();
        javaFile.writeTo(System.out);
    }
}
