package services.videa.graphql.java.scalars;

import com.squareup.javapoet.ClassName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BasicScalarMapperTest {

    /*
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

     */
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
        String packageName = "services.videa";
        ClassName className = BasicScalarMapper.convert("GraphQLType");

        assertNull(className);
    }



}
