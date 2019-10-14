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

import com.squareup.javapoet.TypeSpec;
import graphql.language.InputObjectTypeDefinition;
import graphql.language.ObjectTypeDefinition;
import graphql.language.ScalarTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.videa.graphql.java.FileCreator;
import services.videa.graphql.java.GeneratorInterface;
import services.videa.graphql.java.inputs.InputMapper;
import services.videa.graphql.java.scalars.CustomScalarMapper;

import java.util.Map;


/**
 *
 */
public class TypeGenerator implements GeneratorInterface {
    private static Logger logger = LoggerFactory.getLogger(TypeGenerator.class);


    private Map<String, ObjectTypeDefinition> types;
    private TypeMapper typeMapper;

    private String packageName;
    private FileCreator fileCreator;


    public TypeGenerator(Map<String, ObjectTypeDefinition> types,
                         Map<String, ScalarTypeDefinition> scalars,
                         String generationFolder, String packageName) {
        this.types = types;
        typeMapper = new TypeMapper(scalars);
        this.packageName = packageName;
        fileCreator = fileCreator(generationFolder, packageName);
    }


    /**
     *
     * @param objectTypeDefinition
     */
    public void generate(ObjectTypeDefinition objectTypeDefinition) {
        logger.debug("objectTypeDefinition: {}", objectTypeDefinition);

        TypeSpec typeSpec = typeMapper.convert(objectTypeDefinition, packageName);

        fileCreator.write(typeSpec);
    }


    /**
     * This public methods generates all GraphQL types except Query and Mutation. These two
     * special endpoint classes are to be generated specifically after creation of all other types.
     */
    @Override
    public void generate() {
        types.forEach((key, value) -> {
            if(!("Query".equals(value.getName()) || "Mutation".equals(value.getName()))) {
                generate(value);
            }
        });
    }

}
