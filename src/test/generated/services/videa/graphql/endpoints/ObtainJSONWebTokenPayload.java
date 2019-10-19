package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * Obtain JSON Web Token mutation */
@Data
public class ObtainJSONWebTokenPayload {
  /**
   * GraphQL: TypeName{name='String'}
   */
  private String token;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String refreshToken;
}
