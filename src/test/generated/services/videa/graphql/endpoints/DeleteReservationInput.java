package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class DeleteReservationInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='DeleteInput'}}
   */
  private DeleteInput reservation;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
