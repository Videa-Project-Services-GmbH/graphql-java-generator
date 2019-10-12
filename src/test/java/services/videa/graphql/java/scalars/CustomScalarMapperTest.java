package services.videa.graphql.java.scalars;

import com.squareup.javapoet.TypeSpec;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomScalarMapperTest {

    private GqlSchemaParser schemaParser = new GqlSchemaParser("/zemtu-test.gql");

    @Before
    public void setUp() {
    }


    @Test
    public void jsonString() {
        TypeSpec typeSpec = CustomScalarMapper.convert(schemaParser.scalars().get("JSONString"));

        assertEquals("JSONString", typeSpec.name);
        assertEquals(1, typeSpec.fieldSpecs.size());
    }


    @Test
    public void allScalars() {
        schemaParser.scalars().values().forEach(scalar -> {
            TypeSpec typeSpec = CustomScalarMapper.convert(scalar);
            assertEquals(scalar.getName(), typeSpec.name);
            assertEquals(1, typeSpec.fieldSpecs.size());
        });
    }


}
