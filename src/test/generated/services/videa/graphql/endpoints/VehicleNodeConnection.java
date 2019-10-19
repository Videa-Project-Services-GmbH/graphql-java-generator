package services.videa.graphql.endpoints;

import java.util.List;
import lombok.Data;

@Data
public class VehicleNodeConnection {
  /**
   * GraphQL: NonNullType{type=TypeName{name='PageInfo'}}
   */
  private PageInfo pageInfo;

  /**
   * GraphQL: NonNullType{type=ListType{type=TypeName{name='VehicleNodeEdge'}}}
   */
  private List<VehicleNodeEdge> edges;
}
