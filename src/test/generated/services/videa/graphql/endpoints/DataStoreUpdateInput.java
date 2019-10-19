package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class DataStoreUpdateInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='JSONString'}}
   */
  private String jsonData;
}
