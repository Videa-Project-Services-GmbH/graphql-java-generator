package services.videa.graphql.endpoints;

import lombok.Data;

/**
 * Dynamic vehicle data. */
@Data
public class VehicleData {
  /**
   * GraphQL: TypeName{name='ElectricVehicleData'}
   */
  private ElectricVehicleData electricVehicleData;

  /**
   * GraphQL: TypeName{name='FloatingPosition'}
   */
  private FloatingPosition floatingPosition;
}
