/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java;

import org.junit.Before;
import services.videa.graphql.java.schema.GqlSchemaLoader;
import services.videa.graphql.java.schema.GqlSchemaParser;

public class AbstractGraphQLJavaTest {

    protected static final String SRC_FOLDER = "./src/test/generated";
    protected static final String PACKAGE_NAME = "services.videa.graphql.java";
    protected static final String FILE_PATH = SRC_FOLDER + "/" + PACKAGE_NAME.replace(".", "/");

    protected GqlSchemaParser gqlSchemaParser;

    @Before
    public void setUp() {
        java.io.File file = GqlSchemaLoader.load("graphql-java-test.gql", "");
        gqlSchemaParser = new GqlSchemaParser(file);
    }

}
