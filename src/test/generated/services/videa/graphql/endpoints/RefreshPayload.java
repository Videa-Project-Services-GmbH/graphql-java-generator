package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class RefreshPayload {
  /**
   * GraphQL: TypeName{name='String'}
   */
  private String token;

  /**
   * GraphQL: TypeName{name='GenericScalar'}
   */
  private String payload;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String refreshToken;
}
