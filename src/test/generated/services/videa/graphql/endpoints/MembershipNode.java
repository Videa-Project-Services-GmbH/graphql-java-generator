package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

@Data
public class MembershipNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='UserNode'}}
   */
  private UserNode user;

  /**
   * GraphQL: NonNullType{type=TypeName{name='MembershipStatusEnum'}}
   */
  private MembershipStatusEnum status;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String note;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: NonNullType{type=TypeName{name='SharingGroupNode'}}
   */
  private SharingGroupNode sharingGroup;
}
