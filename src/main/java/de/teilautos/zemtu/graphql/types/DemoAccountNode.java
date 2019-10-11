package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class DemoAccountNode {
  @NotNull
  private Boolean enabled;

  private String username;

  private String password;
}
