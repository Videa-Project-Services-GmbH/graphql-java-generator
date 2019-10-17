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

import org.junit.Assert;
import org.junit.Test;
import services.videa.graphql.java.CreateUserInput;
import services.videa.graphql.java.CreateUserPayload;
import services.videa.graphql.java.Mutation;
import services.videa.graphql.java.UserCreateInput;
import services.videa.graphql.java.endpoints.fakes.CreateUserInputFake;
import services.videa.graphql.java.endpoints.fakes.CreateUserPayloadFake;
import services.videa.graphql.java.endpoints.fakes.UserCreateInputFake;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.UUID;


public class MutationTest {

    private String url = "https://devteilautos.zemtu.com/graphql";
    private String token = "2920a7e21d0b6ef854c0a53c7299403424086e11";

    @Test
    public void createUser() throws IOException, NoSuchMethodException, IllegalAccessException {
        Mutation mutation = new Mutation(url, token);

        String uuid = UUID.randomUUID().toString();
        UserCreateInput user = new UserCreateInput();
        user.setUsername(uuid);
        user.setEmail(uuid + "@mailinator.com");
        user.setPassword(uuid);
        user.setFirstName("User");
        user.setLastName("Name");
        user.setZip("77777");
        user.setCity("City");

        CreateUserInput input = new CreateUserInput();
        input.setUser(user);

        CreateUserPayload payload = mutation.createUser(input);

    }

    @Test
    public void pojo() throws IllegalAccessException, NoSuchMethodException {
        UserCreateInputFake input = createUserCreateInput();

        String inputJson = inputJson(input);
        Assert.assertNotNull(inputJson);

        String output = "username: \"user.name\" email: \"user.name@mailinator.com\" " +
                "password: \"secreate\" firstName: \"User\" lastName: \"Name\" zip: \"77777\" city: \"City\"";
        Assert.assertEquals(output.trim(), inputJson.trim());
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
        Assert.assertEquals(output.trim(), inputJson.trim());
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
        Assert.assertEquals(output.trim(), json.trim());

    }


    @Test
    public void createUserPayload() {
        String json = readFields(CreateUserPayloadFake.class);

        Assert.assertNotNull(json);
    }

    private String readFields(Class aClass) {
        StringBuilder fieldBuilder = new StringBuilder();

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if ("java.util.List".equals(field.getType().getName())) {
                field.setAccessible(true);
                fieldBuilder.append(field.getName());

                Type genericType = field.getGenericType();
                System.out.println(genericType);

                if (genericType instanceof ParameterizedType) {
                    String typeName = ((ParameterizedType) genericType).getActualTypeArguments()[0].getTypeName();
                    if(!isBasic(typeName)) {
                        fieldBuilder.append(" { ");
                    }
                    Class clazz = classForName(typeName);
                    fieldBuilder.append(readFields(clazz)).append(" ");

                    if(!isBasic(typeName)) {
                        fieldBuilder.append(" } ");
                    }
                }
            } else if (isBasic(field.getType().getName())) {
                fieldBuilder.append(field.getName()).append(" ");
            } else if(field.getType().getName().startsWith(aClass.getPackage().getName())) {
                fieldBuilder.append(field.getName()).append(" ");
                fieldBuilder.append(" { ");
                fieldBuilder.append(readFields(field.getType()));
                fieldBuilder.append(" } ");
            }
        }
        return fieldBuilder.toString();
    }

    private Class classForName(String typeName) {
        try {
            return Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }


    private boolean isBasic(String typeName) {
        return (typeName.startsWith("java.lang") || typeName.startsWith("java.math"));
    }


    public String createInput(Object input) throws NoSuchMethodException, IllegalAccessException {
        String objectName = "";
        if (input != null) {
            String methodName = new Exception().getStackTrace()[0].getMethodName();
            objectName = this.getClass().getMethod(methodName, Object.class).getParameters()[0].getName();
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
