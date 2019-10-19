package services.videa.graphql.endpoints;

import lombok.Data;

@Data
public class EnvironmentNode {
  /**
   * GraphQL: TypeName{name='SiteConfigNode'}
   */
  private SiteConfigNode siteConfig;

  /**
   * GraphQL: TypeName{name='CurrentUserNode'}
   */
  private CurrentUserNode currentUser;
}
