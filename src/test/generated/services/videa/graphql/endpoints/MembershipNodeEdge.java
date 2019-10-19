package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * A Relay edge containing a `MembershipNode` and its cursor. */
@Data
public class MembershipNodeEdge {
  /**
   * GraphQL: TypeName{name='MembershipNode'}
   */
  private MembershipNode node;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String cursor;
}
