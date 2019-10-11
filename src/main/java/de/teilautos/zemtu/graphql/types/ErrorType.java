package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class ErrorType {
  @NotNull
  private String field;

  @NotNull
  private String messages;
}
