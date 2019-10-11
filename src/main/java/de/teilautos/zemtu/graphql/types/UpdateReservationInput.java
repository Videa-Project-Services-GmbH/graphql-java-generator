package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class UpdateReservationInput {
  @NotNull
  private String reservation;

  private String clientMutationId;
}
