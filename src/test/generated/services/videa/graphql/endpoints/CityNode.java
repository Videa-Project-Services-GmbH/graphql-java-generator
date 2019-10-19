package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class CityNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String name;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String zip;
}
