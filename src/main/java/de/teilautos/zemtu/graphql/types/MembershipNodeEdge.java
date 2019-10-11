package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * A Relay edge containing a `MembershipNode` and its cursor.
 */
@Data
public class MembershipNodeEdge {
  private String node;

  @NotNull
  private String cursor;
}
