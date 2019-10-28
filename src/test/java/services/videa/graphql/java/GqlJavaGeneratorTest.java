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

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class GqlJavaGeneratorTest extends AbstractGraphQLJavaTest {


    @Test
    public void readSchemaContent() throws FileNotFoundException {
        File initialFile = new File("src/test/resources/graphql-java-test.gql");
        InputStream inputStream = new FileInputStream(initialFile);
        Assert.assertNotNull(inputStream);
    }


    @Test(expected = FileNotFoundException.class)
    public void schemaFileNotFound() throws FileNotFoundException {
        File initialFile = new File("src/test/resources/file-not-found.gql");
        new FileInputStream(initialFile);
    }

    @Test
    public void generateAll() throws IOException {
        File initialFile = new File("src/test/resources/graphql-java-test.gql");
        InputStream inputStream = new FileInputStream(initialFile);
        //String schemaContent = GqlJavaGenerator.convert(inputStream);

        GqlJavaGenerator.generateJavaClasses(inputStream,
                "src/test/generated", "services.videa.graphql.java");

        File[] files = new File(FILE_PATH).listFiles();
        Assert.assertTrue(files.length > 0);

        Arrays.stream(files).forEach(File::delete);

    }

}
