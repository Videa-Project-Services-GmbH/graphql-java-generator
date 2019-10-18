/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.types;

import graphql.language.ObjectTypeDefinition;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.AbstractGraphQLJavaTest;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;
import services.videa.graphql.java.enums.EnumGenerator;
import services.videa.graphql.java.inputs.InputGenerator;
import services.videa.graphql.java.interfaces.InterfaceGenerator;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TypeGeneratorTest extends AbstractGraphQLJavaTest {

    private EnumGenerator enumGenerator;
    private InterfaceGenerator interfaceGenerator;
    private InputGenerator inputGenerator;

    private TypeGenerator typeGenerator;

    @Before
    public void setUp() {
        super.setUp();

        enumGenerator = new EnumGenerator(gqlSchemaParser.enums(), SRC_FOLDER, PACKAGE_NAME);
        interfaceGenerator = new InterfaceGenerator(gqlSchemaParser.interfaces(), SRC_FOLDER, PACKAGE_NAME);
        inputGenerator = new InputGenerator(gqlSchemaParser.inputTypes(), gqlSchemaParser.scalars(),
                SRC_FOLDER, PACKAGE_NAME);

        typeGenerator = new TypeGenerator(gqlSchemaParser.objectTypes(),
                gqlSchemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);
    }


    @Test
    public void userNode() {
        enumGenerator.generate();
        inputGenerator.generate();
        interfaceGenerator.generate();

        ObjectTypeDefinition userNode = gqlSchemaParser.objectTypes().get("UserNode");
        typeGenerator.generate(userNode);

        File[] files = new File(FILE_PATH).listFiles();
        assertNotNull(files);
        assertTrue(files.length >= 1);

        Arrays.stream(files).forEach(File::delete);
    }


    @Test
    public void dataStoreNode() {
        enumGenerator.generate();
        inputGenerator.generate();
        interfaceGenerator.generate();

        ObjectTypeDefinition ownerNode = gqlSchemaParser.objectTypes().get("OwnerNode");
        typeGenerator.generate(ownerNode);

        ObjectTypeDefinition userNode = gqlSchemaParser.objectTypes().get("DataStoreNode");
        typeGenerator.generate(userNode);

        File[] files = new File(FILE_PATH).listFiles();
        assertNotNull(files);
        assertTrue(files.length >= 1);

        Arrays.stream(files).forEach(File::delete);
    }


    @Test
    public void allTypes() {
        enumGenerator.generate();
        inputGenerator.generate();
        interfaceGenerator.generate();

        typeGenerator.generate();

        File[] files = new File(FILE_PATH).listFiles();
        assertNotNull(files);
        assertTrue(files.length >= 1);

        Arrays.stream(files).forEach(File::delete);
    }


}
