package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * A Relay edge containing a `VehicleNode` and its cursor.
 */
@Data
public class VehicleNodeEdge {
  private String node;

  @NotNull
  private String cursor;
}
