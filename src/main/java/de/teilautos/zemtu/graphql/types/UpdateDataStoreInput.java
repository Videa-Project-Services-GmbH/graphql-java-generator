package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class UpdateDataStoreInput {
  @NotNull
  private String dataStore;

  private String clientMutationId;
}
