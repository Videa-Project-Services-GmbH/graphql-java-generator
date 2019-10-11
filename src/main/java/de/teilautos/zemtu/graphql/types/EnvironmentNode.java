package de.teilautos.zemtu.graphql.types;

import java.lang.String;
import lombok.Data;

/**
 * 
 */
@Data
public class EnvironmentNode {
  private String siteConfig;

  private String currentUser;
}
