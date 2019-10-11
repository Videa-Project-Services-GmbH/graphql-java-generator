package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class FloatingPosition {
  private String dateTime;

  private Boolean displayRecommendation;

  private String point;

  private String address;
}
