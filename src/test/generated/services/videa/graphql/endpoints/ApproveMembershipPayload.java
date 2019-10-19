package services.videa.graphql.endpoints;

import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class ApproveMembershipPayload {
  /**
   * GraphQL: ListType{type=TypeName{name='ErrorType'}}
   */
  private List<ErrorType> userErrors;

  /**
   * GraphQL: TypeName{name='MembershipNode'}
   */
  private MembershipNode membership;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
