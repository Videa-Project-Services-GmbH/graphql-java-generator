package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class VerifyPayload {
  private String payload;

  private String clientMutationId;
}
