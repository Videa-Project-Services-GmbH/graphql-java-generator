package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class DataStoreNode {
  @NotNull
  private String user;

  private String jsonData;

  @NotNull
  private String id;

  @NotNull
  private Integer pk;
}
