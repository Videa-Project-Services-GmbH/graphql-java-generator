package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Trip
 */
@Data
public class TripNode {
  @NotNull
  private String id;

  @NotNull
  private Integer pk;

  @NotNull
  private String start;

  @NotNull
  private String end;
}
