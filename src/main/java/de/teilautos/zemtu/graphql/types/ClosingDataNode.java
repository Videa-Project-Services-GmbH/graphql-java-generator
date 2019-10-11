package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * Reservation data that is completed after reservation
 * was CLOSED.
 * - Driven distance, real start & end, fees.
 */
@Data
public class ClosingDataNode {
  private String distance;
}
