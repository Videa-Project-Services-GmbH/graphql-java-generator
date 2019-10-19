package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class UserCreateInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String username;

  /**
   * GraphQL: NonNullType{type=TypeName{name='EmailAddress'}}
   */
  private String email;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String password;

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
  private String company;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String address;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String zip;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String city;

  /**
   * GraphQL: TypeName{name='InternationalPhoneNumber'}
   */
  private String tel;

  /**
   * GraphQL: TypeName{name='InternationalPhoneNumber'}
   */
  private String telMobile;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String externalUserId;
}
