/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.javapoet.*;
import graphql.language.ObjectTypeDefinition;
import graphql.language.ScalarTypeDefinition;
import graphql.language.TypeName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import services.videa.graphql.java.FileCreator;
import services.videa.graphql.java.GeneratorInterface;
import services.videa.graphql.java.types.TypeMapper;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractEndpointGenerator implements GeneratorInterface {
    private static Logger logger = LoggerFactory.getLogger(AbstractEndpointGenerator.class);

    protected ObjectTypeDefinition queryTypeDefinition;
    protected TypeMapper typeMapper;
    protected String packageName;
    protected FileCreator fileCreator;


    protected AbstractEndpointGenerator(ObjectTypeDefinition queryTypeDefinition,
                                        Map<String, ScalarTypeDefinition> scalars,
                                        String generationFolder, String packageName) {
        this.queryTypeDefinition = queryTypeDefinition;
        typeMapper = new TypeMapper(scalars);
        this.packageName = packageName;
        fileCreator = new FileCreator(generationFolder, packageName);
    }


    abstract String generateMethodBody(String methodName, String returnTypeName, List<ParameterSpec> parameterSpecList);


    /**
     *
     */
    public void generate() {
        List<MethodSpec> methodSpecList = queryTypeDefinition.getFieldDefinitions().stream().map(methodDefinition -> {

            String methodName = methodDefinition.getName();
            logger.debug("methodName: {}", methodName);

            List<ParameterSpec> parameterSpecList =
                    methodDefinition.getInputValueDefinitions().stream().map(parameterDefinition -> {
                        com.squareup.javapoet.TypeName typeName
                                = typeMapper.typeName(parameterDefinition.getType(), packageName);
                        return ParameterSpec.builder(typeName, parameterDefinition.getName()).build();
                    }).collect(Collectors.toList());

            String returnTypeName = ((TypeName) methodDefinition.getType()).getName();
            ClassName returnType = ClassName.get(packageName, returnTypeName);

            String methodBody = generateMethodBody(methodName, returnTypeName, parameterSpecList);

            return MethodSpec.methodBuilder(methodName)
                    .addModifiers(Modifier.PUBLIC)
                    .addException(IOException.class)
                    .returns(returnType)
                    .addParameters(parameterSpecList)
                    .addCode(CodeBlock.of(methodBody) + LINE_SEPARATOR)
                    .build();

        }).collect(Collectors.toList());

        String comment = queryTypeDefinition.getDescription() != null ?
                queryTypeDefinition.getDescription().getContent() : System.getProperty("line.separator");

        List<FieldSpec> fieldSpecs = new ArrayList<>();
        fieldSpecs.add(FieldSpec.builder(RestTemplate.class, "restTemplate", Modifier.PRIVATE).build());
        fieldSpecs.add(FieldSpec.builder(DefaultUriBuilderFactory.class, "defaultUriBuilderFactory",
                Modifier.PRIVATE).build());
        fieldSpecs.add(FieldSpec.builder(ObjectMapper.class, "objectMapper", Modifier.PRIVATE).build());

        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, "url")
                .addParameter(String.class, "token")
                .addStatement("this.defaultUriBuilderFactory = new DefaultUriBuilderFactory(url)")
                .addStatement("this.restTemplate = init(token)")
                .addStatement("this.objectMapper = new ObjectMapper()")
                .build();

        MethodSpec initMethodSpec = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PRIVATE)
                .addParameter(String.class, "token")
                .addCode(CodeBlock.of("" +
                        "            RestTemplate restTemplate = new RestTemplate();\n" +
                        "            restTemplate.setUriTemplateHandler(this.defaultUriBuilderFactory);\n" +
                        "            restTemplate.getInterceptors().add((request, body, execution) -> {\n" +
                        "                request.getHeaders().set(\"Authorization\", \"Token \" + token);\n" +
                        "                return execution.execute(request, body);\n" +
                        "            });\n" +
                        "            return restTemplate;\n"))
                .returns(RestTemplate.class)
                .build();

        MethodSpec readFieldsMethodSpec = MethodSpec.methodBuilder("readFields")
                .addModifiers(Modifier.PRIVATE)
                .addParameter(Class.class, "aClass")
                .addCode(CodeBlock.of(generateReadFieldsBody()))
                .returns(String.class)
                .build();

        TypeSpec requestTypeSpec = TypeSpec.classBuilder("Request")
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(Getter.class).addAnnotation(Setter.class)
                .addField(String.class, "query", Modifier.PRIVATE)
                .addField(Map.class, "variables", Modifier.PRIVATE)
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder(queryTypeDefinition.getName())
                .addJavadoc(comment)
                .addAnnotation(Data.class)
                .addModifiers(Modifier.PUBLIC)
                .addType(requestTypeSpec)
                .addFields(fieldSpecs)
                .addMethod(constructorMethodSpec)
                .addMethod(initMethodSpec)
                .addMethod(readFieldsMethodSpec)
                .addMethods(methodSpecList)
                .build();

        fileCreator.write(typeSpec);
    }


    private String generateReadFieldsBody() {
        return  "        StringBuffer buffer = new StringBuffer();\n" +
                "        java.lang.reflect.Field[] fields = aClass.getDeclaredFields();\n" +
                "        for (java.lang.reflect.Field field : fields) {\n" +
                "            buffer.append(field.getName()).append(\" \");\n" +
                "            if (!field.getType().getName().startsWith(\"java.lang\")) {\n" +
                "                buffer.append(\" { \").append(System.getProperty(\"line.separator\"));\n" +
                "                Class<?> myClass = null;\n" +
                "                try {\n" +
                "                    if (\"java.util.List\".equals(field.getType().getName())) {\n" +
                "                        java.lang.reflect.ParameterizedType typeName \n" +
                "                                = (java.lang.reflect.ParameterizedType) field.getGenericType();\n" +
                "                        myClass = Class.forName(typeName.getActualTypeArguments()[0].getTypeName());\n" +
                "                    } else {\n" +
                "                        myClass = field.getType();\n" +
                "                    }\n" +
                "                } catch(ClassNotFoundException e) {\n" +
                "                    e.printStackTrace(System.err);\n" +
                "                    System.err.println(\"Setting aClass to String.class\");\n" +
                "                    myClass = String.class;\n" +
                "                }\n" +
                "                String fieldsStr = readFields(myClass);\n" +
                "                buffer.append(fieldsStr).append(System.getProperty(\"line.separator\"));\n" +
                "                buffer.append(\"}\").append(System.getProperty(\"line.separator\"));\n" +
                "            }\n" +
                "        }\n" +
                "        return buffer.toString();\n";
    }


}
