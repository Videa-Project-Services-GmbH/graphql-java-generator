package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data about a single action.
 */
@Data
public class ReservationActionNode {
  @NotNull
  private String type;

  @NotNull
  private Integer requestId;

  @NotNull
  private String created;

  @NotNull
  private String status;

  @NotNull
  private Boolean isFinalStatus;

  private String phase;

  @NotNull
  private String message;

  @NotNull
  private String hint;
}
