package services.videa.graphql.java.inputs;

import com.squareup.javapoet.TypeSpec;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;

import static org.junit.Assert.assertEquals;

public class InputMapperTest {

    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";

    private GqlSchemaParser schemaParser;
    private InputMapper inputMapper;

    @Before
    public void setUp() {
        java.io.File file = GqlSchemaLoader.load("graphql-java-test.gql", "");
        schemaParser = new GqlSchemaParser(file);
        inputMapper = new InputMapper(schemaParser.scalars(), PACKAGE_NAME);
    }


    @Test
    public void allInputs() {
        schemaParser.inputTypes().values().forEach(inputType -> {
            TypeSpec typeSpec = inputMapper.convert(inputType);
            assertEquals(inputType.getName(), typeSpec.name);
        });
    }


}
