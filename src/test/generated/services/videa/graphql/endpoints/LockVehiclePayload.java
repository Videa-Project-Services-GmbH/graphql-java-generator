package services.videa.graphql.endpoints;

import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class LockVehiclePayload {
  /**
   * GraphQL: ListType{type=TypeName{name='ErrorType'}}
   */
  private List<ErrorType> userErrors;

  /**
   * GraphQL: TypeName{name='ReservationActionNode'}
   */
  private ReservationActionNode reservationAction;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
