package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class ElectricVehicleData {
  private String dateTime;

  private Boolean displayRecommendation;

  private String stateOfCharge;

  private Boolean charging;

  private Boolean pluggedIn;
}
