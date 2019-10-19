package services.videa.graphql.endpoints;

import java.lang.Float;
import lombok.Data;

@Data
public class Distance {
  /**
   * GraphQL: NonNullType{type=TypeName{name='DistanceUnitEnum'}}
   */
  private DistanceUnitEnum unit;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Float'}}
   */
  private Float value;
}
