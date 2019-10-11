package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class RevokeInput {
  @NotNull
  private String refreshToken;

  private String clientMutationId;
}
