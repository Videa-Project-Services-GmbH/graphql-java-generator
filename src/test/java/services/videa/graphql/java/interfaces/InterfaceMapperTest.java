package services.videa.graphql.java.interfaces;

import com.squareup.javapoet.TypeSpec;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterfaceMapperTest {

    private GqlSchemaParser gqlSchemaParser;

    @Before
    public void setUp() {
        java.io.File file = GqlSchemaLoader.load("graphql-java-test.gql", "");
        gqlSchemaParser = new GqlSchemaParser(file);
    }


    @Test
    public void allInterfaces() {
        gqlSchemaParser.interfaces().values().forEach(interfaceType -> {
            TypeSpec typeSpec = InterfaceMapper.convert(interfaceType);
            assertEquals(interfaceType.getName(), typeSpec.name);
        });
    }


}
