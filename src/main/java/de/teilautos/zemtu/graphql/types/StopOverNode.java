package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Each stop over comes with the following data:
 */
@Data
public class StopOverNode {
  @NotNull
  private String dateTime;

  private String odometer;

  @NotNull
  private String position;

  @NotNull
  private String address;
}
