package services.videa.graphql.endpoints;

import java.lang.String;
import lombok.Data;

@Data
public class UpdateDataStoreInput {
  /**
   * GraphQL: NonNullType{type=TypeName{name='DataStoreUpdateInput'}}
   */
  private DataStoreUpdateInput dataStore;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String clientMutationId;
}
