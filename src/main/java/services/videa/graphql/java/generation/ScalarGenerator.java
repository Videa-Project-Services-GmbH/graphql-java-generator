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

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import graphql.language.EnumTypeDefinition;
import graphql.language.ScalarTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.util.Map;

public class ScalarGenerator extends AbstractGenerator {
    private static Logger logger = LoggerFactory.getLogger(ScalarGenerator.class);

    private Map<String, ScalarTypeDefinition> scalars;


    public ScalarGenerator(Map<String, ScalarTypeDefinition> scalars, String targetFolder, String targetPackage) {
        super(targetFolder, targetPackage);
        this.scalars = scalars;
    }


    void generate(ScalarTypeDefinition scalar) {
        logger.debug("scalar={}", scalar);

        if(!ScalarMapper.isPrimitive(scalar.getName())) {
            TypeSpec.Builder builder = TypeSpec.classBuilder(scalar.getName())
                    .addField(String.class, "value", Modifier.PUBLIC);

            String comment = (scalar.getDescription() != null ?
                    scalar.getDescription().getContent() : "") + LINE_SEPARATOR;
            builder.addJavadoc(comment);

            TypeSpec typeSpec = builder.build();

            JavaFile javaFile = JavaFile.builder(targetPackage, typeSpec).build();
            writeModel(javaFile);
        }
    }


    @Override
    void generate() {
        scalars.forEach((key, element) -> generate(element));
    }


}
