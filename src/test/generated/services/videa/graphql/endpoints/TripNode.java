package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

/**
 * Trip */
@Data
public class TripNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: NonNullType{type=TypeName{name='StopOverNode'}}
   */
  private StopOverNode start;

  /**
   * GraphQL: NonNullType{type=TypeName{name='StopOverNode'}}
   */
  private StopOverNode end;
}
