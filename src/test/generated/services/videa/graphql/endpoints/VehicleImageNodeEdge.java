package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * A Relay edge containing a `VehicleImageNode` and its cursor. */
@Data
public class VehicleImageNodeEdge {
  /**
   * GraphQL: TypeName{name='VehicleImageNode'}
   */
  private VehicleImageNode node;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String cursor;
}
