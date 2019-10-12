package services.videa.graphql.java.enums;

import com.squareup.javapoet.TypeSpec;
import graphql.language.EnumTypeDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class EnumMapper {
    private static Logger logger = LoggerFactory.getLogger(EnumMapper.class);


    /**
     *
     * @param enumTypeDefinition
     * @return
     */
    public static TypeSpec convert(EnumTypeDefinition enumTypeDefinition) {
        logger.debug("enumTypeDefinition: {}", enumTypeDefinition);

        TypeSpec.Builder builder = TypeSpec.enumBuilder(enumTypeDefinition.getName());
        enumTypeDefinition.getEnumValueDefinitions().forEach(enumValueDefinition -> {
            builder.addEnumConstant(enumValueDefinition.getName());
        });

        if(enumTypeDefinition.getDescription() != null) {
            builder.addJavadoc(enumTypeDefinition.getDescription().getContent());
        }

        TypeSpec typeSpec = builder.build();

        logger.debug("typeSpec: {}", typeSpec);
        return typeSpec;
    }

}
