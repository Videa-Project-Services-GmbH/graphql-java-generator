package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

/**
 * Zone */
@Data
public class ZoneNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String city;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String label;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String description;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String content;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String color;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: TypeName{name='JSONString'}
   */
  private String jsonData;
}
