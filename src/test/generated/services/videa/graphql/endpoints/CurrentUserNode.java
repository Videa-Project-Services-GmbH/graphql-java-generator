package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

/**
 * User data of the logged in user. */
@Data
public class CurrentUserNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String zip;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String city;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String username;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String firstName;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String lastName;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String externalUserId;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String title;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String address;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String fullname;

  /**
   * GraphQL: TypeName{name='URL'}
   */
  private String avatar;

  /**
   * GraphQL: TypeName{name='MultipleZonesNode'}
   */
  private MultipleZonesNode zones;
}
