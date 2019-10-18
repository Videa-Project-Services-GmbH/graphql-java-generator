/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.scalars;

import com.squareup.javapoet.ClassName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class BasicScalarMapperTest {

    @Test
    public void floatType() {
        String packageName = "java.lang";
        ClassName className = BasicScalarMapper.convert("Float");

        assertEquals("Float", className.simpleName());
        assertEquals(packageName, className.packageName());
    }


    @Test
    public void integerType() {
        String packageName = "java.lang";
        ClassName className = BasicScalarMapper.convert("Int");

        assertEquals("Integer", className.simpleName());
        assertEquals(packageName, className.packageName());
    }


    @Test
    public void booleanType() {
        String packageName = "java.lang";
        ClassName className = BasicScalarMapper.convert("Boolean");

        assertEquals("Boolean", className.simpleName());
        assertEquals(packageName, className.packageName());
    }


    @Test
    public void idType() {
        String packageName = "java.lang";
        ClassName className = BasicScalarMapper.convert("String");

        assertEquals("String", className.simpleName());
        assertEquals(packageName, className.packageName());
    }


    @Test
    public void stringType() {
        String packageName = "java.lang";
        ClassName className = BasicScalarMapper.convert("String");

        assertEquals("String", className.simpleName());
        assertEquals(packageName, className.packageName());
    }


    /*
                case "Long":
                className = ClassName.get(PACKAGE_LANG, "Long");
                break;
            case "Short":
                className = ClassName.get(PACKAGE_LANG, "Short");
                break;
            case "Byte":
                className = ClassName.get(PACKAGE_LANG, "Byte");
                break;
            case "BigDecimal":
                className = ClassName.get(PACKAGE_MATH, "BigDecimal");
                break;
            case "BigInteger":
                className = ClassName.get(PACKAGE_MATH, "BigInteger");
                break;
            case "Char":

     */

    @Test
    public void longType() {
        ClassName className = BasicScalarMapper.convert("Long");

        assertEquals("Long", className.simpleName());
        assertEquals("java.lang", className.packageName());
    }

    @Test
    public void shortType() {
        ClassName className = BasicScalarMapper.convert("Short");

        assertEquals("Short", className.simpleName());
        assertEquals("java.lang", className.packageName());
    }

    @Test
    public void byteType() {
        ClassName className = BasicScalarMapper.convert("Byte");

        assertEquals("Byte", className.simpleName());
        assertEquals("java.lang", className.packageName());
    }


    @Test
    public void bigDecimalType() {
        ClassName className = BasicScalarMapper.convert("BigDecimal");

        assertEquals("BigDecimal", className.simpleName());
        assertEquals("java.math", className.packageName());
    }

    @Test
    public void bigIntegerType() {
        ClassName className = BasicScalarMapper.convert("BigInteger");

        assertEquals("BigInteger", className.simpleName());
        assertEquals("java.math", className.packageName());
    }

    @Test
    public void characterType() {
        ClassName className = BasicScalarMapper.convert("Char");

        assertEquals("Character", className.simpleName());
        assertEquals("java.lang", className.packageName());
    }


    @Test
    public void customType() {
        ClassName className = BasicScalarMapper.convert("GraphQLType");

        assertNull(className);
    }



}
