package services.videa.graphql.java.inputs;

import com.squareup.javapoet.TypeSpec;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;
import services.videa.graphql.java.interfaces.InterfaceMapper;

import static org.junit.Assert.assertEquals;

public class InputMapperTest {

    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";

    private GqlSchemaParser schemaParser = new GqlSchemaParser("/zemtu-test.gql");
    private InputMapper inputMapper = new InputMapper(PACKAGE_NAME);

    @Test
    public void allInputs() {
        schemaParser.inputTypes().values().forEach(inputType -> {
            TypeSpec typeSpec = inputMapper.convert(inputType);
            assertEquals(inputType.getName(), typeSpec.name);
        });
    }


}
