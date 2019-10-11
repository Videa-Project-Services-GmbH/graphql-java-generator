package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Customized branding of the current site.
 */
@Data
public class SiteConfigNode {
  @NotNull
  private String name;

  @NotNull
  private String supportHotline;

  @NotNull
  private Integer pk;

  private String logo;

  private String colorA;

  private String generalTerms;

  private String userManual;

  @NotNull
  private String demoAccount;

  @NotNull
  private String userRegistration;

  @NotNull
  private String zone;
}
