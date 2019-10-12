package services.videa.graphql.java;

import services.videa.graphql.java.enums.EnumGenerator;
import services.videa.graphql.java.generation.*;
import services.videa.graphql.java.scalars.ScalarGenerator;

public class WorkflowGenerator {

    public static void main(String[] args) {
        String schemaFile = args[0];
        String targetFolder = args[1];
        String targetPackage = args[2];

        GqlSchemaParser schemaParser = new GqlSchemaParser(schemaFile);

        ScalarGenerator scalarGenerator = new ScalarGenerator(schemaParser.scalars(), targetFolder, targetPackage);
        EnumGenerator enumGenerator = new EnumGenerator(schemaParser.enums(), targetFolder, targetPackage);
        InterfaceGenerator interfaceGenerator = new InterfaceGenerator(
                schemaParser.interfaces(), targetFolder, targetPackage);
        NodeGenerator nodeGenerator = new NodeGenerator(schemaParser.inputTypes(), targetFolder, targetPackage);
    }
}
