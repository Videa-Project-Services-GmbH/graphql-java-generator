package services.videa.graphql.endpoints;

import java.util.List;
import lombok.Data;

@Data
public class MembershipNodeConnection {
  /**
   * GraphQL: NonNullType{type=TypeName{name='PageInfo'}}
   */
  private PageInfo pageInfo;

  /**
   * GraphQL: NonNullType{type=ListType{type=TypeName{name='MembershipNodeEdge'}}}
   */
  private List<MembershipNodeEdge> edges;
}
