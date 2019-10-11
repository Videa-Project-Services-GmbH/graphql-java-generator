package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class ReservationCreateInput {
  @NotNull
  private String start;

  @NotNull
  private String end;

  @NotNull
  private Boolean liftOffer;

  private String note;

  @NotNull
  private String purpose;

  @NotNull
  private String vehicleId;
}
