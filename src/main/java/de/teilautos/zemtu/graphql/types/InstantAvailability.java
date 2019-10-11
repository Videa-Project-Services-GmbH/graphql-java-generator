package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Instant means for the current timeslot.
 */
@Data
public class InstantAvailability {
  @NotNull
  private Boolean usableNow;

  @NotNull
  private Boolean bookableNow;

  private Boolean accessibleNow;

  private Boolean inHomeArea;
}
