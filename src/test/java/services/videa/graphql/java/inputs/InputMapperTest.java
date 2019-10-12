package services.videa.graphql.java.inputs;

import com.squareup.javapoet.TypeSpec;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;
import services.videa.graphql.java.interfaces.InterfaceMapper;

import static org.junit.Assert.assertEquals;

public class InputMapperTest {

    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";

    private GqlSchemaParser schemaParser = new GqlSchemaParser("/zemtu-test.gql");


    @Test
    public void allInputs() {
        schemaParser.inputTypes().values().forEach(inputType -> {
            TypeSpec typeSpec = InputMapper.convert(inputType, PACKAGE_NAME);
            assertEquals(inputType.getName(), typeSpec.name);
        });
    }


}
