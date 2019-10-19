package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class AccessReservationInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String reservationId;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
