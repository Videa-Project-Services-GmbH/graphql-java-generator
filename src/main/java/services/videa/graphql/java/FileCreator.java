package services.videa.graphql.java;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;

public class FileCreator {

    private String generationFolder;
    private String packageName;


    public FileCreator(String generationFolder, String packageName) {
        this.generationFolder = generationFolder;
        this.packageName = packageName;
    }


    /**
     * Write a Java class file using JavaPoet JavaFile API.
     *
     * @param typeSpec TypeSpec to be generated.
     */
    public void write(TypeSpec typeSpec) {
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();

        File file = new File(generationFolder);
        try {
            javaFile.writeTo(file);
        } catch (IOException e) {
            String message = "Folder not available: " + file.getPath();
            throw new IllegalArgumentException(message, e);
        }
    }

}
