package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class UserCreateInput {
  @NotNull
  private String username;

  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  private String company;

  private String address;

  @NotNull
  private String zip;

  @NotNull
  private String city;

  private String tel;

  private String telMobile;

  private String externalUserId;
}
