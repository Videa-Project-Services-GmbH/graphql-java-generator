/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.interfaces;

import com.squareup.javapoet.TypeSpec;
import graphql.language.InterfaceTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.videa.graphql.java.FileCreator;
import services.videa.graphql.java.GeneratorInterface;

import java.util.Map;


/**
 *
 */
public class InterfaceGenerator implements GeneratorInterface {
    private static Logger logger = LoggerFactory.getLogger(InterfaceGenerator.class);

    private Map<String, InterfaceTypeDefinition> interfaces;
    private FileCreator fileCreator;

    public InterfaceGenerator(Map<String, InterfaceTypeDefinition> interfaces,
                              String generationFolder, String packageName) {
        this.interfaces = interfaces;
        fileCreator = fileCreator(generationFolder, packageName);
    }


    /**
     *
     * @param interfaceTypeDefinition
     */
    private void generate(InterfaceTypeDefinition interfaceTypeDefinition) {
        logger.debug("interfaceTypeDefinition: {}", interfaceTypeDefinition);

        TypeSpec typeSpec = InterfaceMapper.convert(interfaceTypeDefinition);

        fileCreator.write(typeSpec);
    }


    @Override
    public void generate() {
        interfaces.forEach((key, element) -> generate(element));
    }


}
