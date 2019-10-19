package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import lombok.Data;

@Data
public class DataStoreNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='OwnerNode'}}
   */
  private OwnerNode user;

  /**
   * GraphQL: TypeName{name='JSONString'}
   */
  private String jsonData;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;
}
