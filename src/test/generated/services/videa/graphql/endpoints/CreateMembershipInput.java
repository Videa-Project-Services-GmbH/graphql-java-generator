package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class CreateMembershipInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='MembershipCreateInput'}}
   */
  private MembershipCreateInput membership;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
