package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class Mutation {
  private String tokenAuth;

  private String verifyToken;

  private String refreshToken;

  private String revokeToken;

  private String createUser;

  private String updateDataStore;

  private String createReservation;

  private String updateReservation;

  private String deleteReservation;

  private String accessReservation;

  private String finishReservation;

  private String unlockVehicle;

  private String lockVehicle;

  private String createMembership;

  private String approveMembership;
}
