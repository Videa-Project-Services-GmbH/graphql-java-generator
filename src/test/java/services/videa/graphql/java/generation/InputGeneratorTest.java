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
import org.junit.Ignore;
import org.junit.Test;
import services.videa.graphql.java.pojos.GraphQLSchemaParser;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

@Ignore
public class InputGeneratorTest {

    private static final String SRC_FOLDER = "./src/test/java";
    private static final String PACKAGE_NAME = "services.videa.graphql.java.generation.types";
    private static final String FILE_PATH = "./src/test/java/services/videa/graphql/java/generation/types/";

    private GraphQLSchemaParser schemaParser;
    private EnumGenerator enumGenerator;
    private InterfaceGenerator interfaceGenerator;
    private InputGenerator inputGenerator;

    @Before
    public void setUp() {
        schemaParser = new GraphQLSchemaParser("/zemtu-test.gql");
        enumGenerator = new EnumGenerator(schemaParser.enums(), SRC_FOLDER, PACKAGE_NAME);
        interfaceGenerator = new InterfaceGenerator(schemaParser.interfaces(), SRC_FOLDER, PACKAGE_NAME);
        inputGenerator = new InputGenerator(schemaParser.inputTypes(),
                schemaParser.enums(), schemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);
    }


    @Test
    public void generateReservationCreateInput() {
        EnumTypeDefinition reservationPurposeEnum = schemaParser.enums().get("ReservationPurposeEnum");
        enumGenerator.generate(reservationPurposeEnum);

        InputObjectTypeDefinition reservationCreateInput = schemaParser.inputTypes().get("ReservationCreateInput");
        inputGenerator.generate(reservationCreateInput);

        File file = new File(FILE_PATH + "ReservationCreateInput.java");
        assertTrue("file exists", file.exists());

        deleteTypes();
    }


    private void wait(int millis) {
        long anchor = System.currentTimeMillis() + millis;
        while(anchor + millis > System.currentTimeMillis()) {
        }
    }

    @Test
    public void generateCreateReservationInput() {
        enumGenerator.generate();

        InputObjectTypeDefinition reservationCreateInput = schemaParser.inputTypes().get("ReservationCreateInput");
        inputGenerator.generate(reservationCreateInput);

        deleteTypes();
    }


    @Test
    public void generateUserCreateInput() {
        enumGenerator.generate();
        try {
            InputObjectTypeDefinition reservationCreateInput = schemaParser.inputTypes().get("UserCreateInput");
            inputGenerator.generate(reservationCreateInput);
        } catch(Exception e) {
            throw e;
        } finally {
            deleteTypes();
        }
    }

    @Test
    public void generateCreateUserInput() {
        enumGenerator.generate();
        try {
            InputObjectTypeDefinition reservationCreateInput = schemaParser.inputTypes().get("CreateUserInput");
            inputGenerator.generate(reservationCreateInput);
        } catch(Exception e) {
            throw e;
        } finally {
            deleteTypes();
        }
    }


    @Test
    public void allIntputs() {
        enumGenerator.generate();
        inputGenerator.generate();

        deleteTypes();
    }


    private void deleteTypes() {
        File[] files = new File(FILE_PATH).listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(File::delete);
        }
    }

}
