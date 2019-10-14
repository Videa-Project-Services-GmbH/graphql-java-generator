package services.videa.graphql.java.schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
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
     * Convert an input stream to a string by reading contect line by line.
     *
     * @param inputStream Given input stream to be converted.
     * @return Converted string
     * @throws IOException In case of internal reader conflicts
     */
    private static String convert(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
        return out.toString();
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
