package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class ApproveMembershipInput {
  @NotNull
  private String membershipId;

  private String clientMutationId;
}
