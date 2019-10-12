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

package services.videa.graphql.java.inputs;

import com.squareup.javapoet.TypeSpec;
import graphql.language.InputObjectTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.videa.graphql.java.FileCreator;
import services.videa.graphql.java.GeneratorInterface;

import java.util.Map;


/**
 *
 */
public class InputGenerator implements GeneratorInterface {
    private static Logger logger = LoggerFactory.getLogger(InputGenerator.class);

    private Map<String, InputObjectTypeDefinition> inputs;
    private String packageName;
    private FileCreator fileCreator;


    public InputGenerator(Map<String, InputObjectTypeDefinition> inputs, String generationFolder, String packageName) {
        this.inputs = inputs;
        this.packageName = packageName;
        fileCreator = fileCreator(generationFolder, packageName);
    }


    /**
     *
     * @param inputObjectTypeDefinition
     */
    private void generate(InputObjectTypeDefinition inputObjectTypeDefinition) {
        logger.debug("inputObjectTypeDefinition: {}", inputObjectTypeDefinition);

        TypeSpec typeSpec = InputMapper.convert(inputObjectTypeDefinition, packageName);

        fileCreator.write(typeSpec);
    }


    /**
     *
     */
    @Override
    public void generate() {
        inputs.forEach((key, value) -> generate(value));
    }

}
