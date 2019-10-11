package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Reservation
 */
@Data
public class ReservationNode {
  @NotNull
  private String id;

  @NotNull
  private String state;

  @NotNull
  private String addedBy;

  @NotNull
  private String vehicle;

  private String note;

  @NotNull
  private String purpose;

  @NotNull
  private Boolean liftOffer;

  private String fishingPin;

  @NotNull
  private Integer pk;

  @NotNull
  private String start;

  @NotNull
  private String end;

  @NotNull
  private Boolean isFishing;

  @NotNull
  private String actionStatus;

  @NotNull
  private String neighboringReservations;

  private String closingData;

  private String trips;
}
