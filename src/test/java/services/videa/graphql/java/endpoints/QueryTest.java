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

import org.junit.Test;
import services.videa.graphql.java.types.Query;
import services.videa.graphql.java.types.UserNodeConnection;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class QueryTest {

    private String url = "https://devteilautos.zemtu.com/graphql";
    private String token = "2920a7e21d0b6ef854c0a53c7299403424086e11";

    @Test
    public void userNodeConnection() throws IOException {
        Query query = new Query(url, token);
        UserNodeConnection userNodeConnection = query.allUsers(
                10, null, null, null, null,
                null, null, null, null, null);

        System.out.println(userNodeConnection);
        assertNotNull(userNodeConnection);
   }

}
