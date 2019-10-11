package de.teilautos.zemtu.graphql.types;

import java.lang.Double;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Geographical point.
 */
@Data
public class GeoPoint {
  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  private Double altitude;
}
