package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Options that are custom per vehicle and are managed
 * via central registry.
 */
@Data
public class VehicleOptions {
  @NotNull
  private Boolean defaultLiftOffer;

  @NotNull
  private Boolean costCalculator;
}
