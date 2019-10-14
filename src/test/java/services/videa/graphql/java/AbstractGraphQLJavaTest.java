package services.videa.graphql.java;

import org.junit.Before;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;

public class AbstractGraphQLJavaTest {

    protected GqlSchemaParser gqlSchemaParser;

    @Before
    public void setUp() {
        java.io.File file = GqlSchemaLoader.load("graphql-java-test.gql", "");
        gqlSchemaParser = new GqlSchemaParser(file);
    }

}
