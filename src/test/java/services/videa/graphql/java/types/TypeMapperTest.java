package services.videa.graphql.java.types;

import com.squareup.javapoet.TypeSpec;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;
import services.videa.graphql.java.inputs.InputMapper;

import static org.junit.Assert.assertEquals;

public class TypeMapperTest {

    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";

    private GqlSchemaParser schemaParser = new GqlSchemaParser("/zemtu-test.gql");


    @Test
    public void allTypes() {
        schemaParser.objectTypes().values().forEach(objectType -> {
            TypeSpec typeSpec = TypeMapper.convert(objectType, PACKAGE_NAME);
            assertEquals(objectType.getName(), typeSpec.name);
        });
    }


}
