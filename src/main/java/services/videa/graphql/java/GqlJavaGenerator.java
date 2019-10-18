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
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;
import services.videa.graphql.java.types.TypeGenerator;

import java.io.File;

public class GqlJavaGenerator {

    public static void main(String[] args) {
        if(args.length == 0) {
            throw new IllegalArgumentException("Usage Example: " +
                    "java -jar services.videa.graphql.java.GqlJavaGenerator " +
                    "schema.gql " +
                    "src/main/generated " +
                    "services.videa.graphql.java");
        }
        String schemaFile = args[0];
        if(schemaFile == null) {
            throw new IllegalArgumentException("Specify Argument 1: GraphQL Schema File");
        }
        String generationFolder = args[1];
        if(generationFolder == null) {
            throw new IllegalArgumentException("Specify Argument 2: Generation Folder relative to project's root folder");
        }
        String packageName = args[2];
        if(packageName == null) {
            throw new IllegalArgumentException("Specify Argument 3: Generation Package where to put generated classes");
        }

        generateJavaClasses(schemaFile, generationFolder, packageName);

    }


    public static void generateJavaClasses(String schemaFile, String generationFolder, String packageName) {
        GqlSchemaParser gqlSchemaParser = new GqlSchemaParser(GqlSchemaLoader.load(schemaFile, ""));

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
