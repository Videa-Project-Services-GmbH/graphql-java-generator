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
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import graphql.language.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * This generator creates Java classes for InputObject GraphQL types. Prerequisite
 * are all generated Enum types.
 */
public class InputGenerator extends AbstractGenerator {
    private static Logger logger = LoggerFactory.getLogger(InputGenerator.class);

    private Map<String, InputObjectTypeDefinition> inputs;
    private Map<String, EnumTypeDefinition> enums;
    private Map<String, ScalarTypeDefinition> scalars;

    private FieldMapper fieldMapper;

    InputGenerator(Map<String, InputObjectTypeDefinition> inputs,
                   Map<String, EnumTypeDefinition> enums,
                   Map<String, ScalarTypeDefinition> scalars,
                   String targetFolder, String targetPackage) {
        super(targetFolder, targetPackage);
        this.inputs = inputs;
        this.enums = enums;
        this.scalars = scalars;
        this.fieldMapper = new FieldMapper(scalars, new ScalarMapper(), targetPackage);
    }


    /**
     * Recursive generation method for input types.
     *
     * @param inputObjectTypeDefinition
     */
    void generate(InputObjectTypeDefinition inputObjectTypeDefinition) {
        logger.debug("inputObjectTypeDefinition={}", inputObjectTypeDefinition);

        List<InputValueDefinition> inputValueDefinitions = inputObjectTypeDefinition.getInputValueDefinitions();

        List<FieldSpec> fieldSpecs = inputValueDefinitions.stream().map(inputValueDefinition -> {

            String typeName = extractTypeName(inputValueDefinition.getType());
            logger.debug("typeName={}", typeName);
            try {
                // Check that the type is neither a basic scalar nor a customized scalar.
                if(!ScalarMapper.isScalar(typeName) && !scalars.containsKey(typeName)) {
                    Class<?> aClass = Class.forName(targetPackage + "." + typeName);
                    logger.debug("aClass={}", aClass);
                }
            } catch (ClassNotFoundException e) {
                // in case the input type has not been generated yet do it now recursively
                InputObjectTypeDefinition myInputObjectTypeDefinition = inputs.get(typeName);
                this.generate(myInputObjectTypeDefinition);
            }

            FieldSpec fieldSpec = fieldMapper.convert(inputValueDefinition);
            if(fieldSpec == null) {
                InputObjectTypeDefinition typeDefinition = inputs.get(typeName);
                generate(typeDefinition);
//                fieldSpec = fieldMapper.convert(typeDefinition);
            }
            return fieldSpec;
        }).collect(Collectors.toList());

        // Generate the java class using given comment and fields.
        String comment = extractComment(inputObjectTypeDefinition.getDescription());
        TypeSpec typeSpec = TypeSpec.classBuilder(inputObjectTypeDefinition.getName())
                .addJavadoc(comment)
                .addAnnotation(Data.class)
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecs)
                .build();

        JavaFile javaFile = JavaFile.builder(targetPackage, typeSpec).build();
        writeModel(javaFile);
    }


    @Override
    void generate() {
        this.inputs.forEach((key, input) -> generate(input));
    }


}
