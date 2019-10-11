package de.teilautos.zemtu.graphql.types;

import java.lang.Double;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class Distance {
  @NotNull
  private String unit;

  @NotNull
  private Double value;
}
