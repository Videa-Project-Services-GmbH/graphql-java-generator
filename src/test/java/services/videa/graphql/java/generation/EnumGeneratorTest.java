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

package services.videa.graphql.java.generation;

import graphql.language.EnumTypeDefinition;
import org.junit.Test;
import services.videa.graphql.java.pojos.GraphQLSchemaParser;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class EnumGeneratorTest {

    @Test
    public void generateReservationPurposeEnum() {
        GraphQLSchemaParser schemaParser = new GraphQLSchemaParser("/zemtu-test.gql");
        EnumGenerator enumGenerator = new EnumGenerator(schemaParser.enums(),
                "./src/test/java", "services.videa.graphql.java.generation.types");

        EnumTypeDefinition reservationPurposeEnum = schemaParser.enums().get("ReservationPurposeEnum");
        enumGenerator.generate(reservationPurposeEnum);

        File file = new File(
                "./src/test/java/services/videa/graphql/java/generation/types/ReservationPurposeEnum.java");
        String filePath = file.getPath();
        System.out.println(filePath);

        assertTrue("Datei wurde nicht generiert.", file.exists());

        boolean delete = file.delete();
        assertTrue("Datei wurde nicht gelöscht", delete);

    }


    @Test
    public void allEnums() {
        GraphQLSchemaParser schemaParser = new GraphQLSchemaParser("/zemtu-test.gql");
        EnumGenerator enumGenerator = new EnumGenerator(schemaParser.enums(),
                "./src/test/java", "services.videa.graphql.java.generation.types");
        enumGenerator.generate();

        // TODO assert result
    }
}
