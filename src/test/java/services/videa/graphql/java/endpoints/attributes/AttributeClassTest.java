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

package services.videa.graphql.java.endpoints.attributes;

import org.junit.Test;

public class AttributeClassTest {

    @Test
    public void fetchAttributes() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("services.videa.graphql.java.endpoints.attributes.AttributeClassFake");

        readFields(aClass);
    }

    @Test
    public void attributeEdge() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("services.videa.graphql.java.endpoints.attributes.AttributeEdge");

        readFields(aClass);
    }

    @Test
    public void tryWrappedType() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("java.lang.String");

        readFields(aClass);
    }

    @Test
    public void listAttributes() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("services.videa.graphql.java.endpoints.attributes.AttributeClassConnection");

        String queryRequest = readFields(aClass);
        System.out.println(queryRequest);
    }

    private String readFields(Class<?> aClass) throws ClassNotFoundException {
        StringBuffer buffer = new StringBuffer();
        java.lang.reflect.Field[] fields = aClass.getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            buffer.append(field.getName()).append(" ");
            if (!field.getType().getName().startsWith("java.lang")) {
                buffer.append(" { ").append(System.getProperty("line.separator"));
                Class<?> myClass = null;
                try {
                    if ("java.util.List".equals(field.getType().getName())) {
                        java.lang.reflect.ParameterizedType typeName
                                = (java.lang.reflect.ParameterizedType) field.getGenericType();
                        myClass = Class.forName(typeName.getActualTypeArguments()[0].getTypeName());
                    } else {
                        myClass = field.getType();
                    }
                } catch(ClassNotFoundException e) {
                    e.printStackTrace(System.err);
                    System.err.println("Setting aClass to String.class");
                    myClass = String.class;
                }
                String fieldsStr = readFields(myClass);
                buffer.append(fieldsStr);
                buffer.append(System.getProperty("line.separator")).append("}").append(System.getProperty("line.separator"));
            }
        }
        return buffer.toString();
    }

}
