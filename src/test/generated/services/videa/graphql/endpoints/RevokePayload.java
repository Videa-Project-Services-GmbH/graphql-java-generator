package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

@Data
public class RevokePayload {
  /**
   * GraphQL: TypeName{name='Int'}
   */
  private Integer revoked;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
