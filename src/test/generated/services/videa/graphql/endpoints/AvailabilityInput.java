package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

@Data
public class AvailabilityInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='DateTime'}}
   */
  private String start;

  /**
   * GraphQL: TypeName{name='DateTime'}
   */
  private String end;

  /**
   * GraphQL: TypeName{name='Boolean'}
   */
  private Boolean availableDuringRequestedTimespan;
}
