package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class CreateReservationInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationCreateInput'}}
   */
  private ReservationCreateInput reservation;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
