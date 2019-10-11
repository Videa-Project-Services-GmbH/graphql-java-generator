package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class CreateUserInput {
  @NotNull
  private String user;

  private String clientMutationId;
}
