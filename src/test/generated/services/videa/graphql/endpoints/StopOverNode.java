package services.videa.graphql.endpoints;

import java.lang.String;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Each stop over comes with the following data: */
@Data
public class StopOverNode {
  /**
   * GraphQL: NonNullType{type=TypeName{name='DateTime'}}
   */
  private String dateTime;

  /**
   * GraphQL: TypeName{name='Decimal'}
   */
  private BigDecimal odometer;

  /**
   * GraphQL: NonNullType{type=TypeName{name='GeoPoint'}}
   */
  private GeoPoint position;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String address;
}
