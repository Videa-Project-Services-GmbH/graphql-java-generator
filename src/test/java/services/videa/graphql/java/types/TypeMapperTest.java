package services.videa.graphql.java.types;

import com.squareup.javapoet.TypeSpec;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;
import services.videa.graphql.java.inputs.InputMapper;
import services.videa.graphql.java.scalars.ScalarGenerator;

import static org.junit.Assert.assertEquals;

public class TypeMapperTest {

    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";

    private GqlSchemaParser schemaParser;
    private TypeMapper typeMapper;

    @Before
    public void setUp() {
        schemaParser = new GqlSchemaParser("/zemtu-test.gql");
        typeMapper = new TypeMapper(schemaParser.scalars());
    }

    @Test
    public void allTypes() {
        schemaParser.objectTypes().values().forEach(objectType -> {
            TypeSpec typeSpec = typeMapper.convert(objectType, PACKAGE_NAME);
            assertEquals(objectType.getName(), typeSpec.name);
        });
    }


}
