package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class AvailabilityInput {
  @NotNull
  private String start;

  private String end;

  private Boolean availableDuringRequestedTimespan;
}
