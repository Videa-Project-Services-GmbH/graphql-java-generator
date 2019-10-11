package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class NeighboringReservationsNode {
  private String previousReservation;

  private String nextReservation;

  private String blockingReservation;
}
