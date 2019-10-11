package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class MembershipNode {
  @NotNull
  private String id;

  @NotNull
  private String user;

  @NotNull
  private String status;

  private String note;

  @NotNull
  private Integer pk;

  @NotNull
  private String sharingGroup;
}
