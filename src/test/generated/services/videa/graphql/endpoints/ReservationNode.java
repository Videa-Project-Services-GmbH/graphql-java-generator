package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import lombok.Data;

/**
 * Reservation */
@Data
public class ReservationNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationStateEnum'}}
   */
  private ReservationStateEnum state;

  /**
   * GraphQL: NonNullType{type=TypeName{name='OwnerNode'}}
   */
  private OwnerNode addedBy;

  /**
   * GraphQL: NonNullType{type=TypeName{name='VehicleNode'}}
   */
  private VehicleNode vehicle;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String note;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationPurposeEnum'}}
   */
  private ReservationPurposeEnum purpose;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean liftOffer;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String fishingPin;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: NonNullType{type=TypeName{name='DateTime'}}
   */
  private String start;

  /**
   * GraphQL: NonNullType{type=TypeName{name='DateTime'}}
   */
  private String end;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean isFishing;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationActionStatusNode'}}
   */
  private ReservationActionStatusNode actionStatus;

  /**
   * GraphQL: NonNullType{type=TypeName{name='NeighboringReservationsNode'}}
   */
  private NeighboringReservationsNode neighboringReservations;

  /**
   * GraphQL: TypeName{name='ClosingDataNode'}
   */
  private ClosingDataNode closingData;

  /**
   * GraphQL: TypeName{name='TripNodeConnection'}
   */
  private TripNodeConnection trips;
}
