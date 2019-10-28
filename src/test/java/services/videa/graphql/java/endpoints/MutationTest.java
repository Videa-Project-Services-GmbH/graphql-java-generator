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
import services.videa.graphql.java.endpoints.fakes.CreateUserInputFake;
import services.videa.graphql.java.endpoints.fakes.CreateUserPayloadFake;
import services.videa.graphql.java.endpoints.fakes.UserCreateInputFake;
import services.videa.graphql.java.rendering.GqlRenderer;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MutationTest {

    private String url = "https://devteilautos.zemtu.com/graphql";
    private String token = "2920a7e21d0b6ef854c0a53c7299403424086e11";

    @Test
    public void pojo() throws IllegalAccessException {
        UserCreateInputFake input = createUserCreateInput();

        String inputJson = inputJson(input);
        assertNotNull(inputJson);

        String output = "username: \"user.name\" email: \"user.name@mailinator.com\" " +
                "password: \"secreate\" firstName: \"User\" lastName: \"Name\" zip: \"77777\" city: \"City\"";
        assertEquals(output.trim(), inputJson.trim());
    }

    private UserCreateInputFake createUserCreateInput() {
        UserCreateInputFake input = new UserCreateInputFake();
        input.setUsername("user.name");
        input.setEmail("user.name@mailinator.com");
        input.setPassword("secreate");
        input.setFirstName("User");
        input.setLastName("Name");
        input.setZip("77777");
        input.setCity("City");
        return input;
    }


    @Test
    public void pojoObject() throws IllegalAccessException, NoSuchMethodException {
        CreateUserInputFake input = new CreateUserInputFake();
        input.setUser(createUserCreateInput());
        input.setClientMutationId("7c32421f-ec08-48fb-9c05-68d836b2cee3");

        String inputJson = inputJson(input);

        String output = "user:  { username: \"user.name\" email: \"user.name@mailinator.com\" password: \"secreate\" " +
                "firstName: \"User\" lastName: \"Name\" zip: \"77777\" city: \"City\"  } " +
                "clientMutationId: \"7c32421f-ec08-48fb-9c05-68d836b2cee3\"";
        assertEquals(output.trim(), inputJson.trim());
    }


    @Test
    public void createInputJson() throws NoSuchMethodException, IllegalAccessException {
        CreateUserInputFake input = new CreateUserInputFake();
        input.setUser(createUserCreateInput());
        input.setClientMutationId("7c32421f-ec08-48fb-9c05-68d836b2cee3");

        String json = createInput(input);

        String output = "input: { user:  { username: \"user.name\" email: \"user.name@mailinator.com\" " +
                "password: \"secreate\" firstName: \"User\" lastName: \"Name\" zip: \"77777\" city: \"City\"  } " +
                "clientMutationId: \"7c32421f-ec08-48fb-9c05-68d836b2cee3\"  }";
        assertEquals(output.trim(), json.trim());

    }


    @Test
    public void createUserPayload() {
        String json = GqlRenderer.renderReturnFields(CreateUserPayloadFake.class);

        assertNotNull(json);
        assertEquals("userErrors { field messages   } " +
                "user  { id email zip city username firstName lastName externalUserId " +
                "title url company tel telMobile avatar pk address fullName  } " +
                "clientMutationId ".trim(), json.trim());
    }

    public String createInput(Object input) throws NoSuchMethodException, IllegalAccessException {
        String objectName = "";
        if (input != null) {
            String methodName = new Exception().getStackTrace()[0].getMethodName();
            objectName = this.getClass().getMethod(methodName, Object.class)
                    .getParameters()[0].getName()
                    .replace("arg0", "input");
            objectName += ": { " + inputJson(input) + " } ";
        }
        return objectName;
    }


    private String inputJson(Object input) throws IllegalAccessException {
        StringBuilder inputJson = new StringBuilder();

        for (Field field : input.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldClassName = field.getType().getName();
            Object value = field.get(input);

            if (value != null) {
                if (fieldClassName.startsWith("java.lang")
                        || fieldClassName.startsWith("java.math")
                        || fieldClassName.startsWith("java.util")) {

                    String quotes = value.getClass().getName().equals("java.lang.String") ? "\"" : "";
                    inputJson.append(field.getName()).append(": ").append(quotes).append(value).append(quotes)
                            .append(" ");

                } else {
                    inputJson.append(field.getName()).append(": ").append(" { ");
                    String fieldJson = inputJson(value);
                    inputJson.append(fieldJson).append(" } ");
                }
            }
        }
        return inputJson.toString();
    }

}
