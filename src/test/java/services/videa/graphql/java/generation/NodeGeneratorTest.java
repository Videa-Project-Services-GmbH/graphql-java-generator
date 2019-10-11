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
import graphql.language.InputObjectTypeDefinition;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.pojos.GraphQLSchemaParser;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class NodeGeneratorTest {

    private static final String SRC_FOLDER = "./src/test/java";
    private static final String PACKAGE_NAME = "services.videa.graphql.java.generation.types";
    private static final String FILE_PATH = "./src/test/java/services/videa/graphql/java/generation/types/";

    private GraphQLSchemaParser schemaParser;
    private ScalarGenerator scalarGenerator;
    private EnumGenerator enumGenerator;
    private InterfaceGenerator interfaceGenerator;
    private InputGenerator inputGenerator;

    private NodeGenerator nodeGenerator = new NodeGenerator(SRC_FOLDER, PACKAGE_NAME);

    @Before
    public void setUp() {
        schemaParser = new GraphQLSchemaParser("/zemtu-test.gql");
        scalarGenerator = new ScalarGenerator(schemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);
        enumGenerator = new EnumGenerator(schemaParser.enums(), SRC_FOLDER, PACKAGE_NAME);
        interfaceGenerator = new InterfaceGenerator(schemaParser.interfaces(), SRC_FOLDER, PACKAGE_NAME);
        inputGenerator = new InputGenerator(schemaParser.inputTypes(),
                schemaParser.enums(), schemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);
    }


    @Test
    public void searchClassString() {
        Class<?> string = nodeGenerator.searchClass("String");
        assertSame(string, String.class);
    }


    @Test
    public void searchClassBigDecimal() {
        Class<?> aClass = nodeGenerator.searchClass("BigDecimal");
        assertSame(aClass, BigDecimal.class);
    }


    @Test
    public void reservationCreateInput() {
        scalarGenerator.generate();
        enumGenerator.generate();
        interfaceGenerator.generate();

        InputObjectTypeDefinition reservationCreateInput = schemaParser.inputTypes().get("ReservationCreateInput");
        nodeGenerator.generate(reservationCreateInput);

        File file = new File(FILE_PATH + "ReservationCreateInput.java");
        assertTrue("file exists", file.exists());

        deleteTypes();
    }


    private void deleteTypes() {
        File[] files = new File(FILE_PATH).listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(File::delete);
        }
    }

}
