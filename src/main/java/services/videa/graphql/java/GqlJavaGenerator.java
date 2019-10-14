package services.videa.graphql.java;

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
    }

}
