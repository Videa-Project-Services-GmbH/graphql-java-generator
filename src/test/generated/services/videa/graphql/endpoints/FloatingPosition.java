package services.videa.graphql.endpoints;

import java.lang.Boolean;
import java.lang.String;
import lombok.Data;

@Data
public class FloatingPosition implements DataItem {
  /**
   * GraphQL: TypeName{name='DateTime'}
   */
  private String dateTime;

  /**
   * GraphQL: TypeName{name='Boolean'}
   */
  private Boolean displayRecommendation;

  /**
   * GraphQL: TypeName{name='GeoPoint'}
   */
  private GeoPoint point;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String address;
}
