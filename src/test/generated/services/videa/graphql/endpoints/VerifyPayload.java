package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class VerifyPayload {
  /**
   * GraphQL: TypeName{name='GenericScalar'}
   */
  private String payload;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
