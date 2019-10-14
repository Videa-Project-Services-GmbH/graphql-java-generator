package services.videa.graphql.java;

import services.videa.graphql.java.endpoints.QueryGenerator;
import services.videa.graphql.java.enums.EnumGenerator;
import services.videa.graphql.java.inputs.InputGenerator;
import services.videa.graphql.java.interfaces.InterfaceGenerator;
import services.videa.graphql.java.types.TypeGenerator;

public class WorkflowGenerator {

    public static void main(String[] args) {
        String schemaFile = args[0];
        String generationFolder = args[1];
        String packageName = args[2];

        /*
        java.io.File file = new java.io.File("gql-schema-parser-test.gql");
        GqlSchemaParser schemaParser = new GqlSchemaParser(schemaFile);

        EnumGenerator enumGenerator = new EnumGenerator(schemaParser.enums(), generationFolder, packageName);
        enumGenerator.generate();

        InterfaceGenerator interfaceGenerator = new InterfaceGenerator(
                schemaParser.interfaces(), generationFolder, packageName);
        interfaceGenerator.generate();

        InputGenerator inputGenerator = new InputGenerator(
                schemaParser.inputTypes(), schemaParser.scalars(), generationFolder, packageName);
        inputGenerator.generate();

        TypeGenerator typeGenerator = new TypeGenerator(schemaParser.objectTypes(), schemaParser.scalars(),
                generationFolder, packageName);
        typeGenerator.generate();

        QueryGenerator queryGenerator = new QueryGenerator(
                schemaParser.objectTypes().get("Query"), schemaParser.scalars(), generationFolder, packageName);
        queryGenerator.generate();
        */
    }

}
