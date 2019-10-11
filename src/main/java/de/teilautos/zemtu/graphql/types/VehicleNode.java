package de.teilautos.zemtu.graphql.types;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class VehicleNode {
  @NotNull
  private String id;

  @NotNull
  private String carsharinggroup;

  @NotNull
  private String label;

  @NotNull
  private String brand;

  @NotNull
  private String model;

  @NotNull
  private String customText;

  @NotNull
  private String licensePlate;

  @NotNull
  private String zone;

  private String zoneObject;

  @NotNull
  private String zip;

  @NotNull
  private String city;

  @NotNull
  private String description;

  @NotNull
  private String condition;

  @NotNull
  private String homebase;

  @NotNull
  private String defaultPurpose;

  @NotNull
  private Integer pk;

  private String address;

  private List<String> captions;

  @NotNull
  private String timezone;

  private String reservations;

  private String images;

  @NotNull
  private String type;

  @NotNull
  private List<String> purposes;

  @NotNull
  private List<String> capabilities;

  @NotNull
  private String options;

  @NotNull
  private String operatingScheme;

  @NotNull
  private String data;

  private String distanceToPosition;

  private String availability;

  @NotNull
  private String instantAvailability;

  private Integer usage;
}
