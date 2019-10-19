package services.videa.graphql.endpoints;

import java.lang.Float;
import lombok.Data;

/**
 * Geographical point. */
@Data
public class GeoPoint {
  /**
   * GraphQL: NonNullType{type=TypeName{name='Float'}}
   */
  private Float latitude;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Float'}}
   */
  private Float longitude;

  /**
   * GraphQL: TypeName{name='Float'}
   */
  private Float altitude;
}
