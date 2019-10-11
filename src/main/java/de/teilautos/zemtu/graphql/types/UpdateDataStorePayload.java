package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class UpdateDataStorePayload {
  private List<String> userErrors;

  @NotNull
  private String dataStore;

  private String clientMutationId;
}
