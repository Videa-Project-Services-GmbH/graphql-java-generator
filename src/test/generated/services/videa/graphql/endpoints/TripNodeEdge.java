package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * A Relay edge containing a `TripNode` and its cursor. */
@Data
public class TripNodeEdge {
  /**
   * GraphQL: TypeName{name='TripNode'}
   */
  private TripNode node;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String cursor;
}
