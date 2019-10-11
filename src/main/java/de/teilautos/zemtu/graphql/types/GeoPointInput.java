package de.teilautos.zemtu.graphql.types;

import java.lang.Double;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class GeoPointInput {
  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;
}
