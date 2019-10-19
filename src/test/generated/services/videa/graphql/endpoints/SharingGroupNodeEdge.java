package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * A Relay edge containing a `SharingGroupNode` and its cursor. */
@Data
public class SharingGroupNodeEdge {
  /**
   * GraphQL: TypeName{name='SharingGroupNode'}
   */
  private SharingGroupNode node;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String cursor;
}
