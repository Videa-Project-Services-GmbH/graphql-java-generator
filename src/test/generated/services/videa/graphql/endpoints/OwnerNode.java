package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

@Data
public class OwnerNode implements Node {
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
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String fullName;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String phoneNumber;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String phoneNumberMobile;
}
