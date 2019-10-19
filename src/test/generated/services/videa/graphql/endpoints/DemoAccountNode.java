package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

@Data
public class DemoAccountNode {
  /**
   * GraphQL: NonNullType{type=TypeName{name='Boolean'}}
   */
  private Boolean enabled;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String username;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String password;
}
