package services.videa.graphql.java.schema;

import org.junit.Assert;
import org.junit.Test;

public class GqlSchemaLoaderTest {

    @Test
    public void loadFromClasspath() {
        java.io.File file = GqlSchemaLoader.load(
                "graphql-java-test.gql", "");

        Assert.assertNotNull(file);
    }

}
