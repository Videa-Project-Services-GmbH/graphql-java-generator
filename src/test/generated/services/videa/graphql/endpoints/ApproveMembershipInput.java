package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class ApproveMembershipInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String membershipId;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
