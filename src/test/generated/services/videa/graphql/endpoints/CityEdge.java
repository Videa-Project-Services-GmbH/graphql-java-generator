package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

/**
 * A Relay edge containing a `City` and its cursor. */
@Data
public class CityEdge {
  /**
   * GraphQL: TypeName{name='CityNode'}
   */
  private CityNode node;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String cursor;
}
