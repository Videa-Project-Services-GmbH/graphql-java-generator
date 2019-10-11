package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import java.util.List;
import lombok.Data;

/**
 * 
 */
@Data
public class CreateUserPayload {
  private List<String> userErrors;

  private String user;

  private String clientMutationId;
}
