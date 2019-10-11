package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class VehicleImageNodeConnection {
  @NotNull
  private String pageInfo;

  @NotNull
  private List<String> edges;
}
