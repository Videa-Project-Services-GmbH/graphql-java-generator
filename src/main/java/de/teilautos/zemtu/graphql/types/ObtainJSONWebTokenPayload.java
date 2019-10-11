package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * Obtain JSON Web Token mutation
 */
@Data
public class ObtainJSONWebTokenPayload {
  private String token;

  private String clientMutationId;

  private String refreshToken;
}
