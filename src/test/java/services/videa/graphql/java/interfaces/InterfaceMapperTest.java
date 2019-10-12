package services.videa.graphql.java.interfaces;

import com.squareup.javapoet.TypeSpec;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;
import services.videa.graphql.java.enums.EnumMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterfaceMapperTest {

    private GqlSchemaParser schemaParser = new GqlSchemaParser("/zemtu-test.gql");


    @Test
    public void allInterfaces() {
        schemaParser.interfaces().values().forEach(interfaceType -> {
            TypeSpec typeSpec = InterfaceMapper.convert(interfaceType);
            assertEquals(interfaceType.getName(), typeSpec.name);
        });
    }


}
