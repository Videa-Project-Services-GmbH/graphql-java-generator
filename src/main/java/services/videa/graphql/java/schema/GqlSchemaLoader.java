/*
 * Copyright 2019 Videa Project Services GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package services.videa.graphql.java.schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class GqlSchemaLoader {
    private static Logger logger = LoggerFactory.getLogger(GqlSchemaLoader.class);


    /**
     * Load schema content as string from a given file in a package. The file is loaded from the
     * classpath and needs to be located in resource folder.
     *
     * @param fileName File name
     * @param packageName Package name within resources folder.
     * @return File or null in case of an error.
     */
    public static java.io.File load(String fileName, String packageName) {
        String filePath = concatenate(fileName, packageName);
        logger.debug("filePath: {}", filePath);

        URL url = GqlSchemaLoader.class.getClassLoader().getResource(filePath);
        logger.debug("url: {}", url);

        java.io.File file = null;
        if(url != null) {
            file = new File(url.getFile());
        }
        logger.debug("file: {}", file);

        return file;
    }


    /**
     * Concatenate a file path name that is usable to read a resource file from the classpath.
     *
     * @param fileName Simple file name
     * @param packageName Package name
     * @return Concatenated file path for class loader usage
     */
    private static String concatenate(String fileName, String packageName) {
        if(packageName == null) {
            packageName = "";
        }
        String slash = "".equals(packageName) ? "": "/";
        return packageName.replace(".", "/") + slash + fileName;
    }

}
