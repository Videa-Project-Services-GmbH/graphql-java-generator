/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.rendering;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * This renderer generates input fields using arguments and return fields using classes' description.
 */
public class GqlRenderer {

    /**
     * Render input fields with assigned values and return this string to be used in query / mutation.
     *
     * @param input Input object as Java class that is genereted from GraphQL schema.
     * @return Input string with assigned values.
     */
    public static String renderInputFields(Object input)  {
        String objectName = "";
        if (input != null) {
            String methodName = new Exception().getStackTrace()[0].getMethodName();

            try {
                objectName = GqlRenderer.class.getMethod(methodName, Object.class).getParameters()[0].getName();
                objectName += ": { " + inputJson(input) + " } ";

            } catch(NoSuchMethodException e) {
                throw new IllegalArgumentException((e));
            } catch(IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }

        return objectName;
    }


    /**
     * Internal convenient method to render a JSON string for input fields. This is used in
     * renderInputFields method.
     *
     * @param input Innput object to render
     * @return
     * @throws IllegalAccessException
     */
    private static String inputJson(Object input) throws IllegalAccessException {
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


    /**
     * Render GraphQL query re. mutation string for return all fields of a given class.
     *
     * @param aClass Class for which field string is to be rendered.
     * @return Rendered field string to paste into GraphQL query / mutation.
     */
    public static String renderReturnFields(Class aClass) {
        StringBuilder fieldBuilder = new StringBuilder();

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {

            if ("java.util.List".equals(field.getType().getName())) {
                field.setAccessible(true);
                fieldBuilder.append(field.getName());

                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    String typeName = ((ParameterizedType) genericType).getActualTypeArguments()[0].getTypeName();
                    if(!isBasicType(typeName)) {
                        fieldBuilder.append(" { ");
                    }
                    Class clazz = classForName(typeName);
                    fieldBuilder.append(renderReturnFields(clazz)).append(" ");

                    if(!isBasicType(typeName)) {
                        fieldBuilder.append(" } ");
                    }
                }

            } else if (isBasicType(field.getType().getName())) {
                fieldBuilder.append(field.getName()).append(" ");

            } else if(field.getType().getName().startsWith(aClass.getPackage().getName())) {
                fieldBuilder.append(field.getName()).append(" ");
                fieldBuilder.append(" { ");
                fieldBuilder.append(renderReturnFields(field.getType()));
                fieldBuilder.append(" } ");

            }
        }

        return fieldBuilder.toString();
    }


    /**
     * Return a class by its full qualified name.
     *
     * @param typeName Class name in a fully qualified manner
     * @return Java class of type typeName
     */
    private static Class classForName(String typeName) {
        try {
            return Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * This convenience methods checks if a fully qualified class name is of Java type,
     * means if it is declared in the Java Class Library (JCL).
     *
     * @param typeName Class name in a fully qualified manner
     * @return True, if class name is of JCL type. False otherwise.
     */
    private static boolean isBasicType(String typeName) {
        return (typeName.startsWith("java.lang") || typeName.startsWith("java.math"));
    }


}
