package services.videa.graphql.endpoints;

import lombok.Data;

@Data
public class NeighboringReservationsNode {
  /**
   * GraphQL: TypeName{name='ReservationNode'}
   */
  private ReservationNode previousReservation;

  /**
   * GraphQL: TypeName{name='ReservationNode'}
   */
  private ReservationNode nextReservation;

  /**
   * GraphQL: TypeName{name='ReservationNode'}
   */
  private ReservationNode blockingReservation;
}
