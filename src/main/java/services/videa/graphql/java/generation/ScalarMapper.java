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

package services.videa.graphql.java.generation;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ScalarMapper {

    /**
     * @deprecated Use isPrimitive instead.
     * @param scalarName
     * @return
     */
    public static boolean isScalar(String scalarName) {
        return convert(scalarName) != null;
    }


    public static boolean isPrimitive(String scalarName) {
        return convert(scalarName) != null;
    }


    public static Class<?> convert(String scalarName) {
        Class<?> clazz;
        switch (scalarName) {
            case "Float":
                clazz = Double.class;
                break;
            case "Int":
                clazz = Integer.class;
                break;
            case "Boolean":
                clazz = Boolean.class;
                break;
            case "ID":
            case "String":
                clazz = String.class;
                break;
            default:
                clazz = convertV12(scalarName);
                break;
        }
        return clazz;
    }

    /**
     * graphql-java adds the following scalar types which are useful in Java based systems
     *
     * @see {https://www.graphql-java.com/documentation/v12/scalars/}
     *
     * @param scalarName
     * @return
     */
    private static Class<?> convertV12(String scalarName) {
        Class<?> clazz;
        switch (scalarName) {
            case "Long":
                clazz = Long.class;
                break;
            case "Short":
                clazz = Short.class;
                break;
            case "Byte":
                clazz = Byte.class;
                break;
            case "BigDecimal":
                clazz = BigDecimal.class;
                break;
            case "BigInteger":
                clazz = BigInteger.class;
                break;
            case "Char":
                clazz = Character.class;
                break;
            default:
                clazz = null;
                break;
        }
        return clazz;
    }

}
