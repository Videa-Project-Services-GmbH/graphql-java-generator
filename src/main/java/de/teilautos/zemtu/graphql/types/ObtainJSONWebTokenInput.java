package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class ObtainJSONWebTokenInput {
  private String clientMutationId;

  @NotNull
  private String username;

  @NotNull
  private String password;
}
