package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

@Data
public class ElectricVehicleData implements DataItem {
  /**
   * GraphQL: TypeName{name='DateTime'}
   */
  private String dateTime;

  /**
   * GraphQL: TypeName{name='Boolean'}
   */
  private Boolean displayRecommendation;

  /**
   * GraphQL: TypeName{name='StateOfCharge'}
   */
  private StateOfCharge stateOfCharge;

  /**
   * GraphQL: TypeName{name='Boolean'}
   */
  private Boolean charging;

  /**
   * GraphQL: TypeName{name='Boolean'}
   */
  private Boolean pluggedIn;
}
