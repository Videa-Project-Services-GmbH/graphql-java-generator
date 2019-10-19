package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class ObtainJSONWebTokenInput {
  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String username;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String password;
}
