package services.videa.graphql.endpoints;

import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class UpdateDataStorePayload {
  /**
   * GraphQL: ListType{type=TypeName{name='ErrorType'}}
   */
  private List<ErrorType> userErrors;

  /**
   * GraphQL: NonNullType{type=TypeName{name='DataStoreNode'}}
   */
  private DataStoreNode dataStore;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
