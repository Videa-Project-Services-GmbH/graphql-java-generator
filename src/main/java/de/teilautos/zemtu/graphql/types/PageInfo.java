package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * The Relay compliant `PageInfo` type, containing data necessary to paginate this connection.
 */
@Data
public class PageInfo {
  @NotNull
  private Boolean hasNextPage;

  @NotNull
  private Boolean hasPreviousPage;

  private String startCursor;

  private String endCursor;
}
