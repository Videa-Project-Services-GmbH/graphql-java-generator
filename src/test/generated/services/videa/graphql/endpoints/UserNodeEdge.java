package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * A Relay edge containing a `UserNode` and its cursor. */
@Data
public class UserNodeEdge {
  /**
   * GraphQL: TypeName{name='UserNode'}
   */
  private UserNode node;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String cursor;
}
