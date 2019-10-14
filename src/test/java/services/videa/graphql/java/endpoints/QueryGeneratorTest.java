package services.videa.graphql.java.endpoints;

import graphql.language.ObjectTypeDefinition;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.AbstractGraphQLJavaTest;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;
import services.videa.graphql.java.enums.EnumGenerator;
import services.videa.graphql.java.inputs.InputGenerator;
import services.videa.graphql.java.interfaces.InterfaceGenerator;
import services.videa.graphql.java.types.TypeGenerator;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueryGeneratorTest extends AbstractGraphQLJavaTest {

    private static final String SRC_FOLDER = "./src/main/generated";
    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";
    private static final String FILE_PATH = SRC_FOLDER + "/" + PACKAGE_NAME.replace(".", "/");

    private EnumGenerator enumGenerator;
    private InterfaceGenerator interfaceGenerator;
    private InputGenerator inputGenerator;

    private TypeGenerator typeGenerator;

    @Before
    public void setUp() {
        super.setUp();

        enumGenerator = new EnumGenerator(gqlSchemaParser.enums(), SRC_FOLDER, PACKAGE_NAME);
        interfaceGenerator = new InterfaceGenerator(gqlSchemaParser.interfaces(), SRC_FOLDER, PACKAGE_NAME);
        inputGenerator = new InputGenerator(gqlSchemaParser.inputTypes(), gqlSchemaParser.scalars(),
                SRC_FOLDER, PACKAGE_NAME);

        typeGenerator = new TypeGenerator(gqlSchemaParser.objectTypes(),
                gqlSchemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);
    }


    @Test
    public void generate() {
        enumGenerator.generate();
        inputGenerator.generate();
        interfaceGenerator.generate();

        typeGenerator.generate();

        try {
            ObjectTypeDefinition queryTypeDefinition = gqlSchemaParser.objectTypes().get("Query");
            QueryGenerator queryGenerator
                    = new QueryGenerator(queryTypeDefinition, gqlSchemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);

            queryGenerator.generate();

            File[] files = new File(FILE_PATH).listFiles();
            assertNotNull(files);
        } finally {
            File[] files = new File(FILE_PATH).listFiles();
            Arrays.stream(files).forEach(File::delete);
        }


    }

}
