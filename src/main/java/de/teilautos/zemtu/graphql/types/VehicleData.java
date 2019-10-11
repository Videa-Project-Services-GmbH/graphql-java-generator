package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * Dynamic vehicle data.
 */
@Data
public class VehicleData {
  private String electricVehicleData;

  private String floatingPosition;
}
