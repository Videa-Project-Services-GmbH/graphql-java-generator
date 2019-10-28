/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java;

import services.videa.graphql.java.endpoints.MutationGenerator;
import services.videa.graphql.java.endpoints.QueryGenerator;
import services.videa.graphql.java.enums.EnumGenerator;
import services.videa.graphql.java.inputs.InputGenerator;
import services.videa.graphql.java.interfaces.InterfaceGenerator;
import services.videa.graphql.java.schema.GqlSchemaParser;
import services.videa.graphql.java.types.TypeGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Java class generator for a given GraphQL schema file. Approach is 'Schema First'.
 */
public class GqlJavaGenerator {

    /**
     * Generate Java classes from given GraphQL schema. The schema is passed as IDL content string.
     *
     * @param schemaContent GraphQL schema content as IDL string
     * @param outputFolder Generation output folder for Java classes
     * @param packageName Package name for Java classes
     */
    public static void generateJavaClasses(InputStream schemaContent, String outputFolder, String packageName) {
        String convert;
        try {
            convert = convert(schemaContent);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        GqlSchemaParser gqlSchemaParser = new GqlSchemaParser(convert);
        generate(outputFolder, packageName, gqlSchemaParser);
    }


    /**
     * Convert an input stream to a string by reading contect line by line.
     *
     * @param inputStream Given input stream to be converted.
     * @return Converted string
     * @throws IOException In case of internal reader conflicts
     */
    public static String convert(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line).append(System.getProperty("line.separator"));
        }
        reader.close();
        return out.toString();
    }


    /**
     * Generate Java classes from given GraphQL schema parser.
     *
     * @param generationFolder Output folder for generated Java classes
     * @param packageName Package name for generated Java classes
     * @param gqlSchemaParser Schema parser to parse GraphQL types
     */
    private static void generate(String generationFolder, String packageName, GqlSchemaParser gqlSchemaParser) {
        EnumGenerator enumGenerator = new EnumGenerator(gqlSchemaParser.enums(), generationFolder, packageName);
        enumGenerator.generate();

        InterfaceGenerator interfaceGenerator = new InterfaceGenerator(
                gqlSchemaParser.interfaces(), generationFolder, packageName);
        interfaceGenerator.generate();

        InputGenerator inputGenerator = new InputGenerator(
                gqlSchemaParser.inputTypes(), gqlSchemaParser.scalars(), generationFolder, packageName);
        inputGenerator.generate();

        TypeGenerator typeGenerator = new TypeGenerator(gqlSchemaParser.objectTypes(), gqlSchemaParser.scalars(),
                generationFolder, packageName);
        typeGenerator.generate();

        QueryGenerator queryGenerator = new QueryGenerator(
                gqlSchemaParser.objectTypes().get("Query"), gqlSchemaParser.scalars(), generationFolder, packageName);
        queryGenerator.generate();

        MutationGenerator mutationGenerator = new MutationGenerator(
                gqlSchemaParser.objectTypes().get("Mutation"), gqlSchemaParser.scalars(), generationFolder, packageName);
        mutationGenerator.generate();
    }

}
