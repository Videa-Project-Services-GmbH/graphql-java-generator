package services.videa.graphql.endpoints;

import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class DeleteReservationPayload {
  /**
   * GraphQL: ListType{type=TypeName{name='ErrorType'}}
   */
  private List<ErrorType> userErrors;

  /**
   * GraphQL: TypeName{name='ReservationNode'}
   */
  private ReservationNode reservation;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
