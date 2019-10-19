package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * Multiple zones returned as GeoJSON. */
@Data
public class MultipleZonesNode {
  /**
   * GraphQL: TypeName{name='JSONString'}
   */
  private String jsonData;
}
