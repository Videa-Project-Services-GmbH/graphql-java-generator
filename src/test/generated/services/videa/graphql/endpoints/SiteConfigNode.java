package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

/**
 * Customized branding of the current site. */
@Data
public class SiteConfigNode {
  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String name;

  /**
   * GraphQL: NonNullType{type=TypeName{name='InternationalPhoneNumber'}}
   */
  private String supportHotline;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: TypeName{name='URL'}
   */
  private String logo;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String colorA;

  /**
   * GraphQL: TypeName{name='URL'}
   */
  private String generalTerms;

  /**
   * GraphQL: TypeName{name='URL'}
   */
  private String userManual;

  /**
   * GraphQL: NonNullType{type=TypeName{name='DemoAccountNode'}}
   */
  private DemoAccountNode demoAccount;

  /**
   * GraphQL: NonNullType{type=TypeName{name='UserRegistrationNode'}}
   */
  private UserRegistrationNode userRegistration;

  /**
   * GraphQL: NonNullType{type=TypeName{name='MultipleZonesNode'}}
   */
  private MultipleZonesNode zone;
}
