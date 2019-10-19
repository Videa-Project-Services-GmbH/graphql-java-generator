package services.videa.graphql.endpoints;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Reservation data that is completed after reservation
 * was CLOSED.
 * - Driven distance, real start & end, fees. */
@Data
public class ClosingDataNode {
  /**
   * GraphQL: TypeName{name='Decimal'}
   */
  private BigDecimal distance;
}
