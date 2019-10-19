package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

@Data
public class SharingGroupNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String name;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String description;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String url;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;
}
