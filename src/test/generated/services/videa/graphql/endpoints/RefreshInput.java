package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class RefreshInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String refreshToken;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
