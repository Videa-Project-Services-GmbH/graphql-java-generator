package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class UserNode {
  @NotNull
  private String id;

  @NotNull
  private String email;

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

  private String url;

  private String company;

  private String tel;

  private String telMobile;

  private String avatar;

  @NotNull
  private Integer pk;

  private String address;

  private String fullName;
}
