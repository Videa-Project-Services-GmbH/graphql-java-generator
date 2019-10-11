package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class ReservationUpdateInput {
  @NotNull
  private String id;

  private String start;

  private String end;

  private Boolean liftOffer;

  private String note;

  private String purpose;

  private String vehicleId;
}
