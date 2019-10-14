/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.endpoints;

import graphql.language.ObjectTypeDefinition;
import org.junit.Before;
import org.junit.Test;
import services.videa.graphql.java.AbstractGraphQLJavaTest;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;
import services.videa.graphql.java.enums.EnumGenerator;
import services.videa.graphql.java.inputs.InputGenerator;
import services.videa.graphql.java.interfaces.InterfaceGenerator;
import services.videa.graphql.java.types.TypeGenerator;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueryGeneratorTest extends AbstractGraphQLJavaTest {

    private static final String SRC_FOLDER = "./src/main/generated";
    private static final String PACKAGE_NAME = "services.videa.graphql.java.types";
    private static final String FILE_PATH = SRC_FOLDER + "/" + PACKAGE_NAME.replace(".", "/");

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
    public void generate() {
        enumGenerator.generate();
        inputGenerator.generate();
        interfaceGenerator.generate();

        typeGenerator.generate();

        try {
            ObjectTypeDefinition queryTypeDefinition = gqlSchemaParser.objectTypes().get("Query");
            QueryGenerator queryGenerator
                    = new QueryGenerator(queryTypeDefinition, gqlSchemaParser.scalars(), SRC_FOLDER, PACKAGE_NAME);

            queryGenerator.generate();

            File[] files = new File(FILE_PATH).listFiles();
            assertNotNull(files);
        } finally {
            File[] files = new File(FILE_PATH).listFiles();
            Arrays.stream(files).forEach(File::delete);
        }


    }

}
