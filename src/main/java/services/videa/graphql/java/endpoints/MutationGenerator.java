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

import com.squareup.javapoet.ParameterSpec;
import graphql.language.ObjectTypeDefinition;
import graphql.language.ScalarTypeDefinition;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;


public class MutationGenerator extends AbstractEndpointGenerator {

    public MutationGenerator(ObjectTypeDefinition queryTypeDefinition,
                             Map<String, ScalarTypeDefinition> scalars,
                             String srcFolder, String packageName) {
        super(queryTypeDefinition, scalars, srcFolder, packageName);
    }

    String generateMethodBody(String methodName, String returnTypeName, List<ParameterSpec> parameterSpecList) {
        StringBuilder body = new StringBuilder();
        body.append("Request request = new Request();").append(LINE_SEPARATOR);

        body.append("String jsonBody = \"mutation { ").append(methodName).append( " \";").append(LINE_SEPARATOR);

        body.append("jsonBody += \" ( \";").append(LINE_SEPARATOR);
        body.append("jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);")
                .append(LINE_SEPARATOR);
        body.append("jsonBody += \" ) \";").append(LINE_SEPARATOR);

        body.append("jsonBody += \" { \";").append(LINE_SEPARATOR);
        body.append("jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields("
                + returnTypeName + ".class);").append(LINE_SEPARATOR);
        body.append("jsonBody += \" } \";").append(LINE_SEPARATOR);

        body.append("jsonBody += \" } \";").append(LINE_SEPARATOR);

        body.append("request.setQuery(jsonBody);").append(LINE_SEPARATOR);
        body.append("request.setVariables(java.util.Collections.emptyMap());").append(LINE_SEPARATOR);

        body.append("Object response = restTemplate.postForObject(\"\", request, Object.class);").append(LINE_SEPARATOR);
        body.append(
                "java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);")
                .append(LINE_SEPARATOR);
        body.append("Map nodeMap = (Map)((Map)map.get(\"data\")).get(\"" + methodName + "\");").append(LINE_SEPARATOR);

        body.append("String responseJson = objectMapper.writeValueAsString(nodeMap);").append(LINE_SEPARATOR);
        body.append(
                returnTypeName + " nodeObject = objectMapper.readValue(responseJson, " + returnTypeName + ".class);")
                .append(LINE_SEPARATOR);

        body.append("return nodeObject;").append(LINE_SEPARATOR);

        return body.toString();
    }

    private String generateParameterList(List<ParameterSpec> parameterSpecList) {
        StringBuffer generationBuffer = new StringBuffer();
        parameterSpecList.forEach(parameterSpec -> {
            String format = MessageFormat.format("if({0} != null) ", parameterSpec.name) + " { ";
            generationBuffer.append(format).append(LINE_SEPARATOR);

            String type = "String".equals(parameterSpec.name) ? "\\\"{0}\\\", \"" : "{0}, \"";
            String pair = "    jsonBody += java.text.MessageFormat.format(\""
                    + parameterSpec.name + ":" + type + ", " + parameterSpec.name+ ");";
            generationBuffer.append(pair).append(LINE_SEPARATOR);

            generationBuffer.append("}").append(LINE_SEPARATOR);
        });

        return generationBuffer.toString();
    }



}
