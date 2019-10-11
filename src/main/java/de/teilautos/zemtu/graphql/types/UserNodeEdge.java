package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * A Relay edge containing a `UserNode` and its cursor.
 */
@Data
public class UserNodeEdge {
  private String node;

  @NotNull
  private String cursor;
}
