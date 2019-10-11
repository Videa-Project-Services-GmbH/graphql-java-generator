package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * User data of the logged in user.
 */
@Data
public class CurrentUserNode {
  @NotNull
  private String id;

  private String zip;

  private String city;

  @NotNull
  private String username;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  private String externalUserId;

  private String title;

  @NotNull
  private Integer pk;

  private String address;

  private String fullname;

  private String avatar;

  private String zones;
}
