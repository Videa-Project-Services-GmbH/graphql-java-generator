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

import com.squareup.javapoet.*;
import lombok.Data;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import javax.lang.model.element.Modifier;
import java.io.IOException;

public class CodeBlockTest {

    private String url = "https://devteilautos.zemtu.com/graphql";
    private String token = "2920a7e21d0b6ef854c0a53c7299403424086e11";
    @Test
    public void methodImpelementation() throws IOException {

        String restTemplateSnippet =
                "        RestTemplate restTemplate = new RestTemplate();\n" +
                "        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(url));\n" +
                "        restTemplate.getInterceptors().add((request, body, execution) -> {\n" +
                "            request.getHeaders().set(\"Authorization\", \"Token \" + token);\n" +
                "            return execution.execute(request, body);\n" +
                "        });\n" +
                "        return restTemplate;\n";
        CodeBlock codeBlock = CodeBlock.builder().add(restTemplateSnippet, url, token).build();
        //System.out.println(codeBlock);

        MethodSpec methodSpec = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PRIVATE)
                .returns(RestTemplate.class)
                .addCode(codeBlock)
                .build();

        String query =
                "    if(restTemplate == null) {\n" +
                "        restTemplate = init();\n" +
                "    }\n" +
                "    GraphQLQuery allUsersQuery = new GraphQLQuery();\n" +
                "    allUsersQuery.setQuery(queryUser);\n" +
                "    allUsersQuery.setVariables(Collections.emptyMap());\n" +
                "    ResponseEntity<Object> response = restTemplate.postForEntity(\"\", allUsersQuery, Object.class);\n";

        MethodSpec queryMethodSpec = MethodSpec.methodBuilder("query")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, "payload", Modifier.FINAL)
                .addCode(query)
                .returns(Object.class)
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder("Client")
                .addField(RestTemplate.class, "restTemplate", Modifier.PRIVATE)
                .addField(String.class, "url", Modifier.PRIVATE)
                .addField(String.class, "token", Modifier.PRIVATE)
                .addAnnotation(Data.class)
                .addMethod(methodSpec)
                .addMethod(queryMethodSpec)
                .build();

        JavaFile javaFile = JavaFile.builder("services.videa.graphql.java.endpoints", typeSpec).build();
        //javaFile.writeTo(System.out);

    }
}
