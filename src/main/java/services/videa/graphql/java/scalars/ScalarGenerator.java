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
import services.videa.graphql.java.FileCreator;
import services.videa.graphql.java.GeneratorInterface;

import java.util.Map;


/**
 *
 */
public class ScalarGenerator implements GeneratorInterface {
    private static Logger logger = LoggerFactory.getLogger(ScalarGenerator.class);

    private Map<String, ScalarTypeDefinition> scalars;
    private FileCreator fileCreator;


    public ScalarGenerator(Map<String, ScalarTypeDefinition> scalars, String generationFolder, String packageName) {
        this.scalars = scalars;
        this.fileCreator = fileCreator(generationFolder, packageName);
    }


    /**
     * Generate a single Java class from a Custom Scalar type. Basic scalars
     * are skipped and they are NOT generated.
     *
     * @param scalar Scalar to be generated.
     */
    private void generate(ScalarTypeDefinition scalar) {
        logger.debug("scalar: {}", scalar);

        ClassName className = BasicScalarMapper.convert(scalar.getName());
        if (className == null) {
            // Other than basic scalar type are custom scalars and they are generated.
            TypeSpec typeSpec = CustomScalarMapper.convert(scalar);
            fileCreator.write(typeSpec);
        }
    }


    /**
     * Generate all Java classes from Basic or Custom Scalar types.
     */
    @Override
    public void generate() {
        scalars.forEach((key, element) -> generate(element));
    }


}
