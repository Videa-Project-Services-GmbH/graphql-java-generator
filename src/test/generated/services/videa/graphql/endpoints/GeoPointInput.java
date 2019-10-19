package services.videa.graphql.endpoints;

import java.lang.Float;
import lombok.Data;

@Data
public class GeoPointInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='Float'}}
   */
  private Float latitude;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Float'}}
   */
  private Float longitude;
}
