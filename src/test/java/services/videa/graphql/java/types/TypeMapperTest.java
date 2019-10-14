package services.videa.graphql.java.types;

import com.squareup.javapoet.TypeSpec;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;

import static org.junit.Assert.assertEquals;

public class TypeMapperTest {

    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";

    private GqlSchemaParser schemaParser;
    private TypeMapper typeMapper;

    @Before
    public void setUp() {
        java.io.File file = GqlSchemaLoader.load("graphql-java-test.gql", "");
        schemaParser = new GqlSchemaParser(file);
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
