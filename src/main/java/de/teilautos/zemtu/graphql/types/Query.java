package de.teilautos.zemtu.graphql.types;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

/**
 * 
 */
class Query {
  public String membership(String id) {
    return null;
  }

  public String allMemberships(Integer first, Integer last, String before, String after, String id,
      String user, String carsharinggroup, String orderBy) {
    return null;
  }

  public String sharingGroup(String id) {
    return null;
  }

  public String allSharingGroups(Integer first, Integer last, String before, String after,
      String id, String name, String orderBy) {
    return null;
  }

  public String vehicle(String id) {
    return null;
  }

  public String allVehicles(String position, String availability, Integer first, Integer last,
      String before, String after, String id, String zip, String city, String description,
      String orderBy) {
    return null;
  }

  public String user(String id) {
    return null;
  }

  public String allUsers(Integer first, Integer last, String before, String after, String id,
      String username, String email, String firstName, String lastName, String orderBy) {
    return null;
  }

  public String city(String id) {
    return null;
  }

  public String allCities(String before, String after, Integer first, Integer last) {
    return null;
  }

  public String dataStore() {
    return null;
  }

  public String environment() {
    return null;
  }

  public String reservation(String id) {
    return null;
  }

  public String allReservations(List state, Integer first, Integer last, String before,
      String after, String id, String startLt, String startLte, String startGt, String startGte,
      String startRange, String endLt, String endLte, String endGt, String endGte,
      Boolean isFishing, Boolean isAccessibleOnly, Boolean isCurrentUserOnly, String orderBy) {
    return null;
  }
}
