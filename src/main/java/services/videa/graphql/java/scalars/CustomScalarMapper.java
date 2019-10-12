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

package services.videa.graphql.java.scalars;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;
import graphql.language.ScalarTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;

public class CustomScalarMapper {
    private static Logger logger = LoggerFactory.getLogger(CustomScalarMapper.class);


    /**
     * Convert a GraphQL scalar type to a Java Poet type specification. With this a
     * Java class can be generated.
     *
     * @param scalar Scalar type to convert to TypeSpec
     * @return TypeSpec to generate JavaPoet class
     */
    public static TypeSpec convert(ScalarTypeDefinition scalar) {
        logger.debug("scalar: {}", scalar);

        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(scalar.getName())
                .addModifiers(Modifier.PUBLIC)
                .addField(ClassName.get("java.lang", "String"),
                        "value", Modifier.PUBLIC);

        if (scalar.getDescription() != null) {
            typeSpecBuilder.addJavadoc(scalar.getDescription().getContent());
        }

        TypeSpec typeSpec = typeSpecBuilder.build();

        logger.debug("typeSpec: {}", typeSpec);
        return typeSpec;
    }


}
