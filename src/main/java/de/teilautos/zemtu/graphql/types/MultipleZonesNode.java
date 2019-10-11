package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * Multiple zones returned as GeoJSON.
 */
@Data
public class MultipleZonesNode {
  private String jsonData;
}
