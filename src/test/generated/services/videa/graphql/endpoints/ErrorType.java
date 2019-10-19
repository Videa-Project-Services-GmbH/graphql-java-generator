package services.videa.graphql.endpoints;

import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class ErrorType {
  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String field;

  /**
   * GraphQL: NonNullType{type=ListType{type=NonNullType{type=TypeName{name='String'}}}}
   */
  private List<String> messages;
}
