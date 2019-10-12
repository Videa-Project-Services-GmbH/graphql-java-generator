package services.videa.graphql.java;

public interface GeneratorInterface {

    default FileCreator fileCreator(String generationFolder, String packageName) {
        return new FileCreator(generationFolder, packageName);
    }

    void generate();

}
