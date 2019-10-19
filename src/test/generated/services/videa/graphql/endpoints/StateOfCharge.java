package services.videa.graphql.endpoints;

import java.lang.Integer;
import lombok.Data;

@Data
public class StateOfCharge {
  /**
   * GraphQL: TypeName{name='Int'}
   */
  private Integer value;

  /**
   * GraphQL: TypeName{name='StateOfChargeZoneEnum'}
   */
  private StateOfChargeZoneEnum zone;
}
