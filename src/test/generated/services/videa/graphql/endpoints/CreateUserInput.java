package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class CreateUserInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='UserCreateInput'}}
   */
  private UserCreateInput user;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
