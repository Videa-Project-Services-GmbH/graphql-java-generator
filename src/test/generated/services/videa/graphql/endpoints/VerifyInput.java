package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class VerifyInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String token;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
