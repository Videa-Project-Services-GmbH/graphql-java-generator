package services.videa.graphql.java.enums;

import com.squareup.javapoet.TypeSpec;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumMapperTest {

    private GqlSchemaParser schemaParser = new GqlSchemaParser("/zemtu-test.gql");


    @Test
    public void allEnums() {
        schemaParser.enums().values().forEach(enumType -> {
            TypeSpec typeSpec = EnumMapper.convert(enumType);
            assertEquals(enumType.getName(), typeSpec.name);
            assertTrue(typeSpec.enumConstants.size() >= 1);
        });
    }


}
