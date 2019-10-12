package services.videa.graphql.java.pojos;

import org.junit.Assert;
import org.junit.Test;

public class RegExTest {

    @Test
    public void replaceDotsBySlash() {
        String srcFolder = "./src/main/generated";
        String packageName = "services.videa.graphql.java.types";
        String filePath = srcFolder + "/" + packageName.replace(".", "/");
        Assert.assertEquals("./src/main/generated/services/videa/graphql/java/types", filePath);
    }
}
