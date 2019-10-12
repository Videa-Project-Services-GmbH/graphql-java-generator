package services.videa.graphql.java.scalars;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;
import services.videa.graphql.java.types.TypeMapper;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomScalarMapperTest {

    private GqlSchemaParser schemaParser;
    private CustomScalarMapper customScalarMapper;

    @Before
    public void setUp() {
        schemaParser = new GqlSchemaParser("/zemtu-test.gql");
        customScalarMapper = new CustomScalarMapper(schemaParser.scalars());
    }


    @Test
    public void jsonString() {
        ClassName className = customScalarMapper.convert("JSONString");

        assertEquals("String", className.simpleName());
    }


    @Test
    public void allScalars() {
        schemaParser.scalars().values().forEach(scalar -> {
            ClassName className = customScalarMapper.convert(scalar.getName());
            assertEquals("String", className.simpleName());
        });
    }


}
