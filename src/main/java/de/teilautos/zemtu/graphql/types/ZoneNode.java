package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Zone
 */
@Data
public class ZoneNode {
  @NotNull
  private String id;

  @NotNull
  private String city;

  @NotNull
  private String label;

  @NotNull
  private String description;

  @NotNull
  private String content;

  @NotNull
  private String color;

  @NotNull
  private Integer pk;

  private String jsonData;
}
