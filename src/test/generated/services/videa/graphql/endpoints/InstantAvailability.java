package services.videa.graphql.endpoints;

import java.lang.Boolean;
import lombok.Data;

/**
 * Instant means for the current timeslot. */
@Data
public class InstantAvailability {
  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean usableNow;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean bookableNow;

  /**
   * GraphQL: TypeName{name='Boolean'}
   */
  private Boolean accessibleNow;

  /**
   * GraphQL: TypeName{name='Boolean'}
   */
  private Boolean inHomeArea;
}
