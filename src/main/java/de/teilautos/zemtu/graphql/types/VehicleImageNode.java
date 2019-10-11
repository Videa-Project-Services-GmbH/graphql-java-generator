package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class VehicleImageNode {
  private String image;

  @NotNull
  private String id;

  @NotNull
  private Integer pk;

  private String thumbnail;
}
