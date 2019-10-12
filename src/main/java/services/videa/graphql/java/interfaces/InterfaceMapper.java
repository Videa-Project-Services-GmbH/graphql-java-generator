package services.videa.graphql.java.interfaces;

import com.squareup.javapoet.TypeSpec;
import graphql.language.EnumTypeDefinition;
import graphql.language.InterfaceTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class InterfaceMapper {
    private static Logger logger = LoggerFactory.getLogger(InterfaceMapper.class);


    public static TypeSpec convert(InterfaceTypeDefinition interfaceTypeDefinition) {
        logger.debug("interfaceTypeDefinition: {}", interfaceTypeDefinition);

        TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(interfaceTypeDefinition.getName());

        if(interfaceTypeDefinition.getDescription() != null) {
            typeSpecBuilder.addJavadoc(interfaceTypeDefinition.getDescription().content);
        }

        TypeSpec typeSpec = typeSpecBuilder.build();

        logger.debug("typeSpec: {}", typeSpec);
        return typeSpec;
    }

}
