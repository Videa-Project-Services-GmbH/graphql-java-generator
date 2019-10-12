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

package services.videa.graphql.java.inputs;

import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.GqlSchemaParser;
import services.videa.graphql.java.enums.EnumGenerator;
import services.videa.graphql.java.interfaces.InterfaceGenerator;
import services.videa.graphql.java.scalars.ScalarGenerator;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

public class InputGeneratorTest {

    private static final String SRC_FOLDER = "./src/main/generated";
    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";
    private static final String FILE_PATH = SRC_FOLDER + "/" + PACKAGE_NAME.replace(".", "/");

    private ScalarGenerator scalarGenerator;
    private EnumGenerator enumGenerator;
    private InterfaceGenerator interfaceGenerator;

    private InputGenerator inputGenerator;

    @Before
    public void setUp() {
        GqlSchemaParser schemaParser = new GqlSchemaParser("/zemtu-test.gql");

        scalarGenerator = new ScalarGenerator(schemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);
        enumGenerator = new EnumGenerator(schemaParser.enums(), SRC_FOLDER, PACKAGE_NAME);
        interfaceGenerator = new InterfaceGenerator(schemaParser.interfaces(), SRC_FOLDER, PACKAGE_NAME);

        inputGenerator = new InputGenerator(schemaParser.inputTypes(), SRC_FOLDER, PACKAGE_NAME);
    }


    @Test
    public void allInputs() {
        scalarGenerator.generate();
        enumGenerator.generate();
        inputGenerator.generate();
        inputGenerator.generate();

        File[] files = new File(FILE_PATH).listFiles();
        assertNotNull(files);
        assertTrue(files.length >= 1);

        Arrays.stream(files).forEach(File::delete);
    }


}
