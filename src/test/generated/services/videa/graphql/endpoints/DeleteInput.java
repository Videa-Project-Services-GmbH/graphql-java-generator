package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class DeleteInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;
}
