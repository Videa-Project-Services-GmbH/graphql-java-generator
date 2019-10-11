package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Status of actions executed for reservation.
 */
@Data
public class ReservationActionStatusNode {
  @NotNull
  private List<String> availableActions;

  @NotNull
  private List<String> executableActions;

  @NotNull
  private String executedActionsStatus;
}
