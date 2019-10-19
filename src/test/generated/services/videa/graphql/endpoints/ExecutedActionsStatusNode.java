package services.videa.graphql.endpoints;

import lombok.Data;

@Data
public class ExecutedActionsStatusNode {
  /**
   * GraphQL: TypeName{name='ReservationActionNode'}
   */
  private ReservationActionNode accessReservation;

  /**
   * GraphQL: TypeName{name='ReservationActionNode'}
   */
  private ReservationActionNode unlockVehicle;

  /**
   * GraphQL: TypeName{name='ReservationActionNode'}
   */
  private ReservationActionNode lockVehicle;

  /**
   * GraphQL: TypeName{name='ReservationActionNode'}
   */
  private ReservationActionNode finishReservation;
}
