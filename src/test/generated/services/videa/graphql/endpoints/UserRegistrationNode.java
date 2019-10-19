package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class UserRegistrationNode {
  /**
   * GraphQL: NonNullType{type=TypeName{name='UserRegistrationTypeEnum'}}
   */
  private UserRegistrationTypeEnum type;

  /**
   * GraphQL: TypeName{name='JSONString'}
   */
  private String settings;
}
