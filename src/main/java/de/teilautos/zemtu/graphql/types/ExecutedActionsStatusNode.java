package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class ExecutedActionsStatusNode {
  private String accessReservation;

  private String unlockVehicle;

  private String lockVehicle;

  private String finishReservation;
}
