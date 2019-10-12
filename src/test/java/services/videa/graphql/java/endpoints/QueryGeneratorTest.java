package services.videa.graphql.java.endpoints;

import graphql.language.ObjectTypeDefinition;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;
import services.videa.graphql.java.enums.EnumGenerator;
import services.videa.graphql.java.inputs.InputGenerator;
import services.videa.graphql.java.interfaces.InterfaceGenerator;
import services.videa.graphql.java.scalars.ScalarGenerator;
import services.videa.graphql.java.types.TypeGenerator;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueryGeneratorTest {

    private static final String SRC_FOLDER = "./src/main/generated";
    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";
    private static final String FILE_PATH = SRC_FOLDER + "/" + PACKAGE_NAME.replace(".", "/");

    private GqlSchemaParser schemaParser;

    private ScalarGenerator scalarGenerator;
    private EnumGenerator enumGenerator;
    private InterfaceGenerator interfaceGenerator;
    private InputGenerator inputGenerator;

    private TypeGenerator typeGenerator;

    @Before
    public void setUp() {
        schemaParser = new GqlSchemaParser("/zemtu-test.gql");

        scalarGenerator = new ScalarGenerator(schemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);
        enumGenerator = new EnumGenerator(schemaParser.enums(), SRC_FOLDER, PACKAGE_NAME);
        interfaceGenerator = new InterfaceGenerator(schemaParser.interfaces(), SRC_FOLDER, PACKAGE_NAME);
        inputGenerator = new InputGenerator(schemaParser.inputTypes(), SRC_FOLDER, PACKAGE_NAME);

        typeGenerator = new TypeGenerator(schemaParser.objectTypes(),
                schemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);
    }


    @Test
    public void generate() {
        scalarGenerator.generate();
        enumGenerator.generate();
        inputGenerator.generate();
        interfaceGenerator.generate();

        typeGenerator.generate();

        try {
            ObjectTypeDefinition queryTypeDefinition = schemaParser.objectTypes().get("Query");
            QueryGenerator queryGenerator
                    = new QueryGenerator(queryTypeDefinition, schemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);

            queryGenerator.generate();

            File[] files = new File(FILE_PATH).listFiles();
            assertNotNull(files);
        } finally {
            File[] files = new File(FILE_PATH).listFiles();
            Arrays.stream(files).forEach(File::delete);
        }


    }

}
