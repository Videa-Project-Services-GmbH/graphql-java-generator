package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

/**
 * The Relay compliant `PageInfo` type, containing data necessary to paginate this connection. */
@Data
public class PageInfo {
  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean hasNextPage;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean hasPreviousPage;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String startCursor;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String endCursor;
}
