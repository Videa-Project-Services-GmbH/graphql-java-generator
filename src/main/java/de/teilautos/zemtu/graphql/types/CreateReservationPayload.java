package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import java.util.List;
import lombok.Data;

/**
 * 
 */
@Data
public class CreateReservationPayload {
  private List<String> userErrors;

  private String reservation;

  private String clientMutationId;
}
