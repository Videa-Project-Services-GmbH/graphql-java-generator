package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import java.util.List;
import lombok.Data;

/**
 * 
 */
@Data
public class CreateMembershipPayload {
  private List<String> userErrors;

  private String membership;

  private String clientMutationId;
}
