package services.videa.graphql.endpoints;

import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class CreateUserPayload {
  /**
   * GraphQL: ListType{type=TypeName{name='ErrorType'}}
   */
  private List<ErrorType> userErrors;

  /**
   * GraphQL: TypeName{name='UserNode'}
   */
  private UserNode user;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
