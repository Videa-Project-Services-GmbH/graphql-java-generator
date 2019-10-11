package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class MembershipCreateInput {
  @NotNull
  private String userId;

  @NotNull
  private String sharingGroupId;

  private String note;
}
