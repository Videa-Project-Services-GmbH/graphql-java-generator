package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class Availability {
  @NotNull
  private Boolean availableDuringRequestedTimespan;

  @NotNull
  private String start;

  private String end;
}
