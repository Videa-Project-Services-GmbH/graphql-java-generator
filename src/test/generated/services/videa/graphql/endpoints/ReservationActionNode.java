package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import lombok.Data;

/**
 * Data about a single action. */
@Data
public class ReservationActionNode {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationActionTypeEnum'}}
   */
  private ReservationActionTypeEnum type;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer requestId;

  /**
   * GraphQL: NonNullType{type=TypeName{name='DateTime'}}
   */
  private String created;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationActionStatusEnum'}}
   */
  private ReservationActionStatusEnum status;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean isFinalStatus;

  /**
   * GraphQL: TypeName{name='ReservationActionPhaseNode'}
   */
  private ReservationActionPhaseNode phase;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String message;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String hint;
}
