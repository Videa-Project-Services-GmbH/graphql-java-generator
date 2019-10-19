package services.videa.graphql.endpoints;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class VehicleNode implements Node {
  /**
   * GraphQL: NonNullType{type=TypeName{name='ID'}}
   */
  private String id;

  /**
   * GraphQL: NonNullType{type=TypeName{name='SharingGroupNode'}}
   */
  private SharingGroupNode carsharinggroup;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String label;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String brand;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String model;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String customText;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String licensePlate;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String zone;

  /**
   * GraphQL: TypeName{name='ZoneNode'}
   */
  private ZoneNode zoneObject;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String zip;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String city;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String description;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String condition;

  /**
   * GraphQL: NonNullType{type=TypeName{name='GeoPoint'}}
   */
  private GeoPoint homebase;

  /**
   * GraphQL: NonNullType{type=TypeName{name='ReservationPurposeEnum'}}
   */
  private ReservationPurposeEnum defaultPurpose;

  /**
   * GraphQL: NonNullType{type=TypeName{name='Int'}}
   */
  private Integer pk;

  /**
   * GraphQL: TypeName{name='String'}
   */
  private String address;

  /**
   * GraphQL: ListType{type=TypeName{name='String'}}
   */
  private List<String> captions;

  /**
   * GraphQL: NonNullType{type=TypeName{name='String'}}
   */
  private String timezone;

  /**
   * GraphQL: TypeName{name='ReservationNodeConnection'}
   */
  private ReservationNodeConnection reservations;

  /**
   * GraphQL: TypeName{name='VehicleImageNodeConnection'}
   */
  private VehicleImageNodeConnection images;

  /**
   * GraphQL: NonNullType{type=TypeName{name='VehicleTypeEnum'}}
   */
  private VehicleTypeEnum type;

  /**
   * GraphQL: NonNullType{type=ListType{type=TypeName{name='ReservationPurposeEnum'}}}
   */
  private List<ReservationPurposeEnum> purposes;

  /**
   * GraphQL: NonNullType{type=ListType{type=TypeName{name='VehicleCapabilitiesEnum'}}}
   */
  private List<VehicleCapabilitiesEnum> capabilities;

  /**
   * GraphQL: NonNullType{type=TypeName{name='VehicleOptions'}}
   */
  private VehicleOptions options;

  /**
   * GraphQL: NonNullType{type=TypeName{name='OperatingSchemeEnum'}}
   */
  private OperatingSchemeEnum operatingScheme;

  /**
   * GraphQL: NonNullType{type=TypeName{name='VehicleData'}}
   */
  private VehicleData data;

  /**
   * GraphQL: TypeName{name='Distance'}
   */
  private Distance distanceToPosition;

  /**
   * GraphQL: TypeName{name='Availability'}
   */
  private Availability availability;

  /**
   * GraphQL: NonNullType{type=TypeName{name='InstantAvailability'}}
   */
  private InstantAvailability instantAvailability;

  /**
   * GraphQL: TypeName{name='Int'}
   */
  private Integer usage;
}
