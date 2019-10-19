package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class MembershipCreateInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String userId;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String sharingGroupId;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String note;
}
