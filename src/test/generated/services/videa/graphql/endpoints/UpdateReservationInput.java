package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class UpdateReservationInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationUpdateInput'}}
   */
  private ReservationUpdateInput reservation;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
