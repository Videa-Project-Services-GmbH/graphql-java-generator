package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

@Data
public class ReservationUpdateInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: TypeName{name='DateTime'}
   */
  private String start;

  /**
   * GraphQL: TypeName{name='DateTime'}
   */
  private String end;

  /**
   * GraphQL: TypeName{name='Boolean'}
   */
  private Boolean liftOffer;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String note;

  /**
   * GraphQL: TypeName{name='ReservationPurposeEnum'}
   */
  private ReservationPurposeEnum purpose;

  /**
   * GraphQL: TypeName{name='ID'}
   */
  private String vehicleId;
}
