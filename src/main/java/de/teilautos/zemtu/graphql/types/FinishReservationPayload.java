package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import java.util.List;
import lombok.Data;

/**
 * 
 */
@Data
public class FinishReservationPayload {
  private List<String> userErrors;

  private String reservationAction;

  private String clientMutationId;
}
