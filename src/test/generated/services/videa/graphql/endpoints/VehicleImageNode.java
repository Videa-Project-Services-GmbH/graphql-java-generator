package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

@Data
public class VehicleImageNode implements Node {
  /**
   * GraphQL: TypeName{name='String'}
   */
  private String image;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: TypeName{name='URL'}
   */
  private String thumbnail;
}
