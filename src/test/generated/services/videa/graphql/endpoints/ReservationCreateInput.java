package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

@Data
public class ReservationCreateInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='DateTime'}}
   */
  private String start;

  /**
   * GraphQL: NonNullType{type=TypeName{name='DateTime'}}
   */
  private String end;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean liftOffer;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String note;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationPurposeEnum'}}
   */
  private ReservationPurposeEnum purpose;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String vehicleId;
}
