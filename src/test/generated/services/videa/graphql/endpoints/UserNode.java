package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

@Data
public class UserNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String email;

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
   * GraphQL: TypeName{name='String'}
   */
  private String url;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String company;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String tel;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String telMobile;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String avatar;

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
  private String fullName;
}
