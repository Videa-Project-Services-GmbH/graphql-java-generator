/*
 * Copyright (c) 2019.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 *  BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 *  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package services.videa.graphql.java.pojos;

import graphql.language.InputObjectTypeDefinition;
import graphql.language.ObjectTypeDefinition;
import graphql.language.ScalarTypeDefinition;
import graphql.language.TypeDefinition;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

public class GraphQLSchemaParserTest {

    private GraphQLSchemaParser graphQLSchemaParser;

    @Before
    public void setUp() {
        graphQLSchemaParser = new GraphQLSchemaParser("/zemtu.gql");
    }


    @Test
    public void parseReservationCreateInput() {
        GraphQLSchemaParser schemaParser = new GraphQLSchemaParser("/zemtu-test.gql");
        InputObjectTypeDefinition reservationCreateInput = schemaParser.inputTypes().get("ReservationCreateInput");
        assertNotNull(reservationCreateInput);
        assertEquals("Kein Input mit dem Namen '" + reservationCreateInput.getName()
                + "' im SchemaParser gefunden.", "ReservationCreateInput", reservationCreateInput.getName());
    }


    @Test
    public void scalars() {
        Map<String, ScalarTypeDefinition> scalars = graphQLSchemaParser.scalars();

        assertNotNull(scalars);
    }

    @Test
    public void types() {
        Map<String, TypeDefinition> types = graphQLSchemaParser.types();

        assertNotNull(types);
    }

    @Test
    public void findByName_Scalar() {
        String name = "Decimal";
        TypeDefinition byName = graphQLSchemaParser.findByName(name);
        assertTrue(byName instanceof ScalarTypeDefinition);
    }

    @Test
    public void findByName_Type() {
        String name = "AccessReservationPayload";
        TypeDefinition byName = graphQLSchemaParser.findByName(name);
        assertTrue(byName instanceof ObjectTypeDefinition);
    }

    @Test
    public void findByName_Input() {
        String name = "ApproveMembershipInput";
        TypeDefinition byName = graphQLSchemaParser.findByName(name);
        assertTrue(byName instanceof InputObjectTypeDefinition);
    }

}
