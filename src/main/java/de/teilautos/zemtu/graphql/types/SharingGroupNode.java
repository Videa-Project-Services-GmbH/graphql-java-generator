package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class SharingGroupNode {
  @NotNull
  private String name;

  private String description;

  @NotNull
  private String id;

  private String url;

  @NotNull
  private Integer pk;
}
