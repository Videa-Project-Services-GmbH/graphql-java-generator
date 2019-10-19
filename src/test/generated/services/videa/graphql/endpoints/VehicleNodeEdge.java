package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * A Relay edge containing a `VehicleNode` and its cursor. */
@Data
public class VehicleNodeEdge {
  /**
   * GraphQL: TypeName{name='VehicleNode'}
   */
  private VehicleNode node;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String cursor;
}
