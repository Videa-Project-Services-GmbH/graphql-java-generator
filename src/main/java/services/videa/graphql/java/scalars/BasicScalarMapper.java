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

package services.videa.graphql.java.scalars;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import graphql.language.ScalarTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;

public class BasicScalarMapper {
    private static Logger logger = LoggerFactory.getLogger(BasicScalarMapper.class);

    private static final String PACKAGE_LANG = "java.lang";
    private static final String PACKAGE_MATH = "java.math";


    /**
     * Convert a scalar type represented as string to a class name type in JavaPoet.
     *
     * @param scalarName Name of Scalar Type
     * @return Java Poet ClassName containing package and class names.
     */
    public static ClassName convert(String scalarName) {
        logger.debug("scalarName: {}", scalarName);

        ClassName className;
        switch (scalarName) {
            case "Float":
                className = ClassName.get(PACKAGE_LANG, "Float");
                break;
            case "Int":
                className = ClassName.get(PACKAGE_LANG, "Integer");
                break;
            case "Boolean":
                className = ClassName.get(PACKAGE_LANG, "Boolean");
                break;
            case "ID":
            case "String":
                className = ClassName.get(PACKAGE_LANG, "String");
                break;
            default:
                className = convertV12(scalarName);
                break;
        }

        logger.debug("className: {}", className);
        return className;
    }


    /**
     * graphql-java adds the following scalar types which are useful in Java based systems
     *
     * @param scalarName
     * @return
     * @see {https://www.graphql-java.com/documentation/v12/scalars/}
     */
    private static ClassName convertV12(String scalarName) {
        ClassName className;
        switch (scalarName) {
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
                className = ClassName.get(PACKAGE_LANG, "Character");
                break;
            default:
                className = null;
                break;
        }
        return className;
    }


}
