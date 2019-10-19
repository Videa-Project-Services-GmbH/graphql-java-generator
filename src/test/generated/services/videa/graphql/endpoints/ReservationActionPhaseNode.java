package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class ReservationActionPhaseNode {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationActionPhaseStatusEnum'}}
   */
  private ReservationActionPhaseStatusEnum status;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationActionPhaseActivityEnum'}}
   */
  private ReservationActionPhaseActivityEnum activity;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String description;
}
