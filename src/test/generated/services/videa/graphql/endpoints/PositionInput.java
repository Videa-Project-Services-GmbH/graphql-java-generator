package services.videa.graphql.endpoints;

import lombok.Data;

@Data
public class PositionInput {
  /**
   * GraphQL: TypeName{name='GeoPointInput'}
   */
  private GeoPointInput point;
}
