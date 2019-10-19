package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * A Relay edge containing a `ReservationNode` and its cursor. */
@Data
public class ReservationNodeEdge {
  /**
   * GraphQL: TypeName{name='ReservationNode'}
   */
  private ReservationNode node;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String cursor;
}
