package services.videa.graphql.endpoints;

import java.util.List;
import lombok.Data;

/**
 * Status of actions executed for reservation. */
@Data
public class ReservationActionStatusNode {
  /**
   * GraphQL: NonNullType{type=ListType{type=TypeName{name='ReservationActionTypeEnum'}}}
   */
  private List<ReservationActionTypeEnum> availableActions;

  /**
   * GraphQL: NonNullType{type=ListType{type=TypeName{name='ReservationActionTypeEnum'}}}
   */
  private List<ReservationActionTypeEnum> executableActions;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ExecutedActionsStatusNode'}}
   */
  private ExecutedActionsStatusNode executedActionsStatus;
}
