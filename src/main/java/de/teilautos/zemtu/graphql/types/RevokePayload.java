package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class RevokePayload {
  private Integer revoked;

  private String clientMutationId;
}
