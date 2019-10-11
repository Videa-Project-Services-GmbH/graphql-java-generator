package services.videa.graphql.java.consumer;

import graphql.language.ObjectTypeDefinition;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.pojos.GraphQLSchemaParser;

public class QueryGeneratorTest {

    private static final String PACKAGE_NAME = "de.teilautos.zemtu.graphql.types";

    private GraphQLSchemaParser schemaParser;

    @Before
    public void setUp() {
        schemaParser = new GraphQLSchemaParser("/zemtu.gql");
    }

    @Test
    public void create() {
        ObjectTypeDefinition queryTypeDefinition = schemaParser.objectTypes().get("Query");
        QueryGenerator queryGenerator = new QueryGenerator(queryTypeDefinition, PACKAGE_NAME);

        queryGenerator.createClass();
    }

}
