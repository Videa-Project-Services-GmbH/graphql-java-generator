package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class ReservationActionPhaseNode {
  @NotNull
  private String status;

  @NotNull
  private String activity;

  private String description;
}
