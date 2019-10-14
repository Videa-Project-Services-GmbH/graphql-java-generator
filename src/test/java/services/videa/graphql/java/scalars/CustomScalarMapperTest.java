package services.videa.graphql.java.scalars;

import com.squareup.javapoet.ClassName;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomScalarMapperTest {

    private GqlSchemaParser gqlSchemaParser;
    private CustomScalarMapper customScalarMapper;

    @Before
    public void setUp() {
        java.io.File file = GqlSchemaLoader.load("graphql-java-test.gql", "");
        gqlSchemaParser = new GqlSchemaParser(file);
        customScalarMapper = new CustomScalarMapper(gqlSchemaParser.scalars());
    }


    @Test
    public void jsonString() {
        ClassName className = customScalarMapper.convert("JSONString");

        assertEquals("String", className.simpleName());
    }


    @Test
    public void allScalars() {
        gqlSchemaParser.scalars().values().forEach(scalar -> {
            ClassName className = customScalarMapper.convert(scalar.getName());
            assertEquals("String", className.simpleName());
        });
    }


}
