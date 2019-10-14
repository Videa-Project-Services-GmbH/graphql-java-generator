package services.videa.graphql.java.enums;

import com.squareup.javapoet.TypeSpec;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumMapperTest {

    private GqlSchemaParser gqlSchemaParser;

    @Before
    public void setUp() {
        java.io.File file = GqlSchemaLoader.load("graphql-java-test.gql", "");
        gqlSchemaParser = new GqlSchemaParser(file);
    }

    @Test
    public void allEnums() {
        gqlSchemaParser.enums().values().forEach(enumType -> {
            TypeSpec typeSpec = EnumMapper.convert(enumType);
            assertEquals(enumType.getName(), typeSpec.name);
            assertTrue(typeSpec.enumConstants.size() >= 1);
        });
    }


}
