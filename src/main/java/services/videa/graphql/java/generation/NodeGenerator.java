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
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import graphql.language.InputObjectTypeDefinition;
import graphql.language.InputValueDefinition;
import graphql.language.Node;
import graphql.language.Type;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.videa.graphql.java.AbstractGenerator;

import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NodeGenerator extends AbstractGenerator {
    private static Logger logger = LoggerFactory.getLogger(NodeGenerator.class);

    private Map<String, InputObjectTypeDefinition> inputs;


    public NodeGenerator(Map<String, InputObjectTypeDefinition> inputs, String targetFolder, String targetPackage) {
        super(targetFolder, targetPackage);
        this.inputs = inputs;
    }


    void generate(Node node) {
        logger.debug("node={}", node);

        String name = null;
        String comment = null;
        List<FieldSpec> fieldSpecs = null;
        if (node instanceof InputObjectTypeDefinition) {
            InputObjectTypeDefinition typeDefinition = (InputObjectTypeDefinition) node;

            name = typeDefinition.getName();
            comment = comment(typeDefinition.getDescription());

            List<InputValueDefinition> valueDefinitions = typeDefinition.getInputValueDefinitions();
            fieldSpecs = valueDefinitions.stream().map(this::convert).collect(Collectors.toList());

        } else if (node instanceof InputValueDefinition) {
            InputValueDefinition valueDefinition = (InputValueDefinition) node;
            name = valueDefinition.getName();
            comment = comment(valueDefinition.getDescription());

            //valueDefinition.getChildren().stream().map()
        }

        TypeSpec typeSpec = TypeSpec.classBuilder(name)
                .addJavadoc(comment)
                .addAnnotation(Data.class)
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecs)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();
        writeModel(javaFile);

    }

    private FieldSpec convert(InputValueDefinition valueDefinition) {
        Type type = valueDefinition.getType();
        logger.debug("type={}", type);

        String extractTypeName = extractTypeName(type);
        Class<?> aClass = searchClass(extractTypeName);
        if (aClass == null) {
            generate(valueDefinition);
            aClass = searchClass(extractTypeName);
        }
        TypeName typeName = TypeName.get(aClass);

        return FieldSpec.builder(typeName, valueDefinition.getName(), Modifier.PRIVATE).build();
    }


    /**
     * Search a class by its simple name within a given set of packages.
     *
     * @param simpleName
     * @return
     */
    Class<?> searchClass(String simpleName) {
/*
        Class<?> aClass = ScalarMapper.convert(simpleName);
        if (aClass != null) {
            logger.debug("aClass found: {}", aClass);
            return aClass;
        }

        String[] searchPackages = {targetPackage};
        for (String searchPackage : searchPackages) {
            try {
                aClass = Class.forName(searchPackage + "." + simpleName);
                if (aClass != null) {
                    logger.debug("aClass found: {}", aClass);
                    return aClass;
                }
            } catch (ClassNotFoundException e) {
                logger.debug("looked for: {}", e.getMessage());
            }
        }

 */
        return null;
    }


    @Override
    public void generate() {
        inputs.forEach((key, value) -> generate(value));
    }

}
