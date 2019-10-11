package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class OwnerNode {
  @NotNull
  private String id;

  @NotNull
  private String email;

  private String zip;

  private String city;

  @NotNull
  private Integer pk;

  private String fullName;

  private String phoneNumber;

  private String phoneNumberMobile;
}
