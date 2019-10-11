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

import com.squareup.javapoet.*;
import graphql.language.TypeName;
import graphql.language.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.videa.graphql.java.exceptions.GenerationFolderMissingException;
import services.videa.graphql.java.exceptions.GraphQLJavaMappingMissingException;

import javax.lang.model.element.Modifier;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JavaModelGenerator {
    private static Logger logger = LoggerFactory.getLogger(JavaModelGenerator.class);

    private static final String NEWLINE = System.getProperty("line.separator");

    private static final String GENERATION_FOLDER = "./src/main/java";

    private GraphQLSchemaParser graphQLSchemaParser;
    private String targetPackage;

    private ScalarClassMapper scalarClassMapper = new ScalarClassMapper();

    public JavaModelGenerator(GraphQLSchemaParser graphQLSchemaParser, String targetPackage) {
        this.graphQLSchemaParser = graphQLSchemaParser;
        this.targetPackage = targetPackage;
    }

    /**
     * Recursive Java class generation reading GraphQL schema file.
     */
    void toModel(String name) {
        try {
            TypeDefinition typeDefinition = graphQLSchemaParser.findByName(name);
            if (typeDefinition instanceof ScalarTypeDefinition) {
                // nothing to do here as scalars are mapped to Java data types
            } else if (typeDefinition instanceof InputObjectTypeDefinition) {
                this.inputToModel((InputObjectTypeDefinition) typeDefinition);
            } else if (typeDefinition instanceof ObjectTypeDefinition) {
                this.typeToModel((ObjectTypeDefinition) typeDefinition);
            } else if (typeDefinition instanceof EnumTypeDefinition) {
                this.enumToModel((EnumTypeDefinition) typeDefinition);
            } else if (typeDefinition instanceof InterfaceTypeDefinition) {
                this.interfaceToModel((InterfaceTypeDefinition) typeDefinition);
            } else {
                throw new IllegalStateException("Invalid typeDefinition " + typeDefinition);
            }
        } catch (GraphQLJavaMappingMissingException e) {
            toModel(e.getMessage());
        }
    }

    private void enumToModel(EnumTypeDefinition enumTypeDefinition) {
        logger.debug("enumTypeDefinition={}", enumTypeDefinition);

        TypeSpec.Builder builder = TypeSpec.enumBuilder(enumTypeDefinition.getName());
        enumTypeDefinition.getEnumValueDefinitions().stream().forEach(enumValueDefinition -> {
            builder.addEnumConstant(enumValueDefinition.getName());
        });

        String comment = (enumTypeDefinition.getDescription() != null ?
                enumTypeDefinition.getDescription().getContent() : "") + NEWLINE;
        builder.addJavadoc(comment);

        TypeSpec typeSpec = builder.build();
        JavaFile javaFile = JavaFile.builder(targetPackage, typeSpec).build();
        writeModel(javaFile);
    }

    void interfaceToModel(InterfaceTypeDefinition interfaceTypeDefinition) {
        logger.debug("interfaceTypeDefinition={}", interfaceTypeDefinition);

        TypeSpec.Builder builder = TypeSpec.interfaceBuilder(interfaceTypeDefinition.getName());

        /*List<FieldSpec> fieldSpecs = interfaceTypeDefinition.getFieldDefinitions().stream()
                .map(definition -> {
                    FieldAttributes fieldAttributes = extractFieldAttributes(definition.getType());
                    Class<?> aClass = mapGraphQLToClass(fieldAttributes);
                    return FieldSpec.builder(aClass, definition.getName())
                            .addModifiers(Modifier.PUBLIC)
                            .addModifiers(Modifier.STATIC)
                            .addModifiers(Modifier.FINAL)
                            .build();
                }).collect(Collectors.toList());
        builder.addFields(fieldSpecs);*/

        String comment = (
                interfaceTypeDefinition.getDescription() != null ?
                        interfaceTypeDefinition.getDescription().getContent() : "") + NEWLINE;
        builder.addJavadoc(comment);

        JavaFile javaFile = JavaFile.builder(targetPackage, builder.build()).build();
        writeModel(javaFile);
    }

    /**
     * Generate Java classes from entire GraphQL schema.
     */
    public void toModels() {
        this.enumsToModels();
        this.interfacesToModels();
        this.inputsToModels();
        this.objectsToModels();
    }

    private void enumsToModels() {
        this.graphQLSchemaParser.enums().forEach((key, element) -> enumToModel(element));
    }

    private void interfacesToModels() {
        this.graphQLSchemaParser.interfaces().forEach((key, element) -> interfaceToModel(element));
    }

    private void inputsToModels() {
        this.graphQLSchemaParser.inputTypes().forEach((key, input) -> inputToModel(input));
    }

    private void objectsToModels() {
        this.graphQLSchemaParser.objectTypes().forEach((key, element) -> {
            if(!"Query".equals(element.getName()) ) {
                typeToModel(element);
            }
        });
    }

    /*
    private void scalarToModel(ScalarTypeDefinition graphQLScalar) {
        logger.debug("graphQLScalar={}", graphQLScalar);

        // only generate custom scalars as others are mapped by graphql scalars
        if (!graphQLClassMapper.isScalar(graphQLScalar.getName())
                && !graphQLClassMapper.isGraphQLJava(graphQLScalar.getName())) {

            FieldSpec valueField = FieldSpec.builder(String.class, "value")
                    .addModifiers(Modifier.PRIVATE)
                    .build();

            String comment = (
                    graphQLScalar.getDescription() != null ? graphQLScalar.getDescription().getContent() : "") + NEWLINE;
            TypeSpec typeSpec = TypeSpec.classBuilder(graphQLScalar.getName())
                    .addJavadoc(comment)
                    .addAnnotation(Data.class) // lombok boilerplate code
                    .addModifiers(Modifier.PUBLIC)
                    .addField(valueField)
                    .build();

            JavaFile javaFile = JavaFile.builder(GENERATION_PACKAGE, typeSpec).build();
            writeModel(javaFile);
        }
    }
    */

    public void inputToModel(InputObjectTypeDefinition inputObjectTypeDefinition) {
        logger.debug("inputObjectTypeDefinition={}", inputObjectTypeDefinition);

        List<InputValueDefinition> inputValueDefinitions = inputObjectTypeDefinition.getInputValueDefinitions();

        List<FieldSpec> fieldSpecs = inputValueDefinitions.stream().map(definition -> {
            return mapFieldAttributeToSpecification(definition);
        }).collect(Collectors.toList());

        String comment = (
                inputObjectTypeDefinition.getDescription() != null ?
                        inputObjectTypeDefinition.getDescription().getContent() : "") + NEWLINE;
        generateClass(inputObjectTypeDefinition.getName(), fieldSpecs, comment);
    }

    private void typeToModel(ObjectTypeDefinition typeDefinition) {
        logger.trace("typeDefinition={}", typeDefinition);

        String comment =
                (typeDefinition.getDescription() != null ? typeDefinition.getDescription().getContent() : "")
                        + NEWLINE;
        logger.debug("comment={}", comment);

        List<FieldSpec> fieldSpecs = typeDefinition.getFieldDefinitions().stream().map(definition -> {
            FieldSpec fieldSpec = new FieldMapper(targetPackage).convert(definition);

            return fieldSpec;
        }).collect(Collectors.toList());

        generateClass(typeDefinition.getName(), fieldSpecs, comment);
    }


    private void writeModel(JavaFile javaFile) {
        File file = new File(GENERATION_FOLDER);
        try {
            javaFile.writeTo(file);
        } catch (IOException e) {
            String message = "Folder not available: " + file.getPath();
            throw new GenerationFolderMissingException(message, e);
        }
    }

    private void generateClass(String className, List<FieldSpec> fieldSpecs, String comment) {
        TypeSpec typeSpec = TypeSpec.classBuilder(className)
                .addJavadoc(comment)
                .addAnnotation(Data.class) // lombok boilerplate code
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecs)
                .build();

        JavaFile javaFile = JavaFile.builder(targetPackage, typeSpec).build();
        writeModel(javaFile);
    }

    private FieldSpec mapFieldAttributeToSpecification(InputValueDefinition inputValueDefinition) {
        FieldAttributes fieldAttributes = extractFieldAttributes(inputValueDefinition.getType());

        Class<?> aClass = mapGraphQLToClass(fieldAttributes);
        FieldSpec fieldSpec = buildFieldSpec(aClass, inputValueDefinition.getName(), fieldAttributes);

        return fieldSpec;
    }

    private FieldSpec mapFieldAttributeToSpecification(FieldDefinition fieldDefinition) {
        FieldAttributes fieldAttributes = extractFieldAttributes(fieldDefinition.getType());

        Class<?> aClass = mapGraphQLToClass(fieldAttributes);
        FieldSpec fieldSpec = buildFieldSpec(aClass, fieldDefinition.getName(), fieldAttributes);

        return fieldSpec;
    }

    private FieldSpec buildFieldSpec(Class<?> aClass, String name, FieldAttributes fieldAttributes) {
        FieldSpec.Builder builder = FieldSpec.builder(aClass, name).addModifiers(Modifier.PRIVATE);
        if (fieldAttributes.notNullField) {
            builder.addAnnotation(NotNull.class);
        }
        return builder.build();
    }

    private Class<?> mapToClass(String packageName, String clazzName) {
        try {
            return Class.forName(packageName + "." + clazzName);
        } catch (ClassNotFoundException e) {
            throw new GraphQLJavaMappingMissingException(clazzName);
        }
    }

    private Class<?> mapGraphQLToClass(FieldAttributes fieldAttributes) {
        Class<?> aClass;
        try {
            aClass = Class.forName(targetPackage + "." + fieldAttributes.typeName);
        } catch (ClassNotFoundException e) {
            if (scalarClassMapper.isScalar(fieldAttributes.typeName)) {
                aClass = scalarClassMapper.scalarToClass(fieldAttributes.typeName);
            } else {
                aClass = mapToClass(targetPackage, fieldAttributes.typeName);
            }
        } finally {
            if (fieldAttributes.listField) {
                aClass = List.class;
                // FIXME Allow parameterized lists
            }
        }

        // TODO Also consider list of non-null elements like [ID!]!

        return aClass;
    }

    public FieldAttributes extractFieldAttributes(Type type) {
        FieldAttributes fieldAttributes = new FieldAttributes();

        if (type instanceof TypeName) {
            fieldAttributes.typeName = ((TypeName) type).getName();

        } else if (type instanceof NonNullType) {
            fieldAttributes.notNullField = true; // to generate @NotNull annotation

            NonNullType nonNullType = (NonNullType) type;
            Type aType = nonNullType.getType();

            if (aType instanceof ListType) {
                fieldAttributes.listField = true;
                fieldAttributes.typeName = extractTypeName(aType);
            } else if (aType instanceof TypeName) {
                fieldAttributes.typeName = ((TypeName) aType).getName();
            }

        } else if (type instanceof ListType) {
            fieldAttributes.listField = true;

            fieldAttributes.typeName = extractTypeName(type);
        }

        return fieldAttributes;
    }

    private String extractTypeName(Type type) {
        ListType listType = (ListType) type;
        if (listType.getType() instanceof NonNullType) {
            NonNullType nonNullType = (NonNullType) listType.getType();
            TypeName aTypeName = (TypeName) nonNullType.getType();
            return aTypeName.getName();
        }
        TypeName aTypeName = (TypeName) listType.getType();
        return aTypeName.getName();
    }

    public void toEntryPoint(String queryName) {
        logger.trace("queryName={}", queryName);

        ObjectTypeDefinition queryTypeDefinition = this.graphQLSchemaParser.objectTypes().get(queryName);
        List<MethodSpec> methodSpecs = queryTypeDefinition.getFieldDefinitions().stream().map(fieldDefinition -> {
            TypeName typeName = (TypeName) fieldDefinition.getType();
            FieldAttributes methodFieldAttributes = extractFieldAttributes(fieldDefinition.getType());
            Class<?> returnClazz = mapGraphQLToClass(methodFieldAttributes);

            MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(fieldDefinition.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .returns(returnClazz)
                    .addCode(CodeBlock.of("return null;" + NEWLINE));

            fieldDefinition.getInputValueDefinitions().forEach(inputValueDefinition -> {
                FieldAttributes paramFieldAttributes = extractFieldAttributes(inputValueDefinition.getType());
                Class<?> paramClazz = mapGraphQLToClass(paramFieldAttributes);

                methodSpecBuilder.addParameter(ParameterSpec.builder(
                        paramClazz, inputValueDefinition.getName()).build());
            });

            return methodSpecBuilder.build();
        }).collect(Collectors.toList());

        String comment = (queryTypeDefinition.getDescription() != null ?
                queryTypeDefinition.getDescription().getContent() : "") + NEWLINE;

        TypeSpec typeSpec = TypeSpec.classBuilder(queryName)
                .addJavadoc(comment)
                .addMethods(methodSpecs)
                .build();

        JavaFile javaFile = JavaFile.builder(targetPackage, typeSpec).build();
        writeModel(javaFile);
    }

}
