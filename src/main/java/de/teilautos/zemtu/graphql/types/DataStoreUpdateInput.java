package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class DataStoreUpdateInput {
  @NotNull
  private String id;

  @NotNull
  private String jsonData;
}
