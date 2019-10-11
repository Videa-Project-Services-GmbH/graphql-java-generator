package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class RefreshPayload {
  private String token;

  private String payload;

  private String clientMutationId;

  private String refreshToken;
}
