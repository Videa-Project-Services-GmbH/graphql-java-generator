package services.videa.graphql.endpoints;

import java.lang.Boolean;
import lombok.Data;

/**
 * Options that are custom per vehicle and are managed
 * via central registry. */
@Data
public class VehicleOptions {
  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean defaultLiftOffer;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean costCalculator;
}
