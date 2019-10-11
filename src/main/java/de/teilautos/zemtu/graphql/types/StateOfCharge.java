package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class StateOfCharge {
  private Integer value;

  private String zone;
}
