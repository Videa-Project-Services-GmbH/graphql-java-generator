package de.teilautos.zemtu.graphql.types;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * 
 */
@Data
public class Query {
  private RestTemplate restTemplate;

  private DefaultUriBuilderFactory defaultUriBuilderFactory;

  private ObjectMapper objectMapper;

  public Query(String url, String token) {
    this.defaultUriBuilderFactory = new DefaultUriBuilderFactory(url);
    this.restTemplate = init(token);
    this.objectMapper = new ObjectMapper();
  }

  private RestTemplate init(String token) {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setUriTemplateHandler(this.defaultUriBuilderFactory);
                restTemplate.getInterceptors().add((request, body, execution) -> {
                    request.getHeaders().set("Authorization", "Token " + token);
                    return execution.execute(request, body);
                });
                return restTemplate;
  }

  private String readFields(Class aClass) {
            StringBuffer buffer = new StringBuffer();
            java.lang.reflect.Field[] fields = aClass.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                buffer.append(field.getName()).append(" ");
                if (!field.getType().getName().startsWith("java.lang")) {
                    buffer.append(" { ").append(System.getProperty("line.separator"));
                    Class<?> myClass = null;
                    try {
                        if ("java.util.List".equals(field.getType().getName())) {
                            java.lang.reflect.ParameterizedType typeName 
                                    = (java.lang.reflect.ParameterizedType) field.getGenericType();
                            myClass = Class.forName(typeName.getActualTypeArguments()[0].getTypeName());
                        } else {
                            myClass = field.getType();
                        }
                    } catch(ClassNotFoundException e) {
                        e.printStackTrace(System.err);
                        System.err.println("Setting aClass to String.class");
                        myClass = String.class;
                    }
                    String fieldsStr = readFields(myClass);
                    buffer.append(fieldsStr).append(System.getProperty("line.separator"));
                    buffer.append("}").append(System.getProperty("line.separator"));
                }
            }
            return buffer.toString();
  }

  public String membership(String id) throws IOException {
    Request request = new Request();
    String jsonBody = "query { membership ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(MembershipNode.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("membership");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    MembershipNode nodeObject = objectMapper.readValue(responseJson, MembershipNode.class);
    return nodeObject;
    
  }

  public String allMemberships(Integer first, Integer last, String before, String after, String id,
      String user, String carsharinggroup, String orderBy) throws IOException {
    Request request = new Request();
    String jsonBody = "query { allMemberships ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(first != null)  { 
        jsonBody += java.text.MessageFormat.format("first:{0}, ", first);
    }
    if(last != null)  { 
        jsonBody += java.text.MessageFormat.format("last:{0}, ", last);
    }
    if(before != null)  { 
        jsonBody += java.text.MessageFormat.format("before:\"{0}\", ", before);
    }
    if(after != null)  { 
        jsonBody += java.text.MessageFormat.format("after:\"{0}\", ", after);
    }
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(user != null)  { 
        jsonBody += java.text.MessageFormat.format("user:\"{0}\", ", user);
    }
    if(carsharinggroup != null)  { 
        jsonBody += java.text.MessageFormat.format("carsharinggroup:\"{0}\", ", carsharinggroup);
    }
    if(orderBy != null)  { 
        jsonBody += java.text.MessageFormat.format("orderBy:\"{0}\", ", orderBy);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(MembershipNodeConnection.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("allMemberships");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    MembershipNodeConnection nodeObject = objectMapper.readValue(responseJson, MembershipNodeConnection.class);
    return nodeObject;
    
  }

  public String sharingGroup(String id) throws IOException {
    Request request = new Request();
    String jsonBody = "query { sharingGroup ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(SharingGroupNode.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("sharingGroup");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    SharingGroupNode nodeObject = objectMapper.readValue(responseJson, SharingGroupNode.class);
    return nodeObject;
    
  }

  public String allSharingGroups(Integer first, Integer last, String before, String after,
      String id, String name, String orderBy) throws IOException {
    Request request = new Request();
    String jsonBody = "query { allSharingGroups ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(first != null)  { 
        jsonBody += java.text.MessageFormat.format("first:{0}, ", first);
    }
    if(last != null)  { 
        jsonBody += java.text.MessageFormat.format("last:{0}, ", last);
    }
    if(before != null)  { 
        jsonBody += java.text.MessageFormat.format("before:\"{0}\", ", before);
    }
    if(after != null)  { 
        jsonBody += java.text.MessageFormat.format("after:\"{0}\", ", after);
    }
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(name != null)  { 
        jsonBody += java.text.MessageFormat.format("name:\"{0}\", ", name);
    }
    if(orderBy != null)  { 
        jsonBody += java.text.MessageFormat.format("orderBy:\"{0}\", ", orderBy);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(SharingGroupNodeConnection.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("allSharingGroups");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    SharingGroupNodeConnection nodeObject = objectMapper.readValue(responseJson, SharingGroupNodeConnection.class);
    return nodeObject;
    
  }

  public String vehicle(String id) throws IOException {
    Request request = new Request();
    String jsonBody = "query { vehicle ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(VehicleNode.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("vehicle");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    VehicleNode nodeObject = objectMapper.readValue(responseJson, VehicleNode.class);
    return nodeObject;
    
  }

  public String allVehicles(String position, String availability, Integer first, Integer last,
      String before, String after, String id, String zip, String city, String description,
      String orderBy) throws IOException {
    Request request = new Request();
    String jsonBody = "query { allVehicles ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(position != null)  { 
        jsonBody += java.text.MessageFormat.format("position:\"{0}\", ", position);
    }
    if(availability != null)  { 
        jsonBody += java.text.MessageFormat.format("availability:\"{0}\", ", availability);
    }
    if(first != null)  { 
        jsonBody += java.text.MessageFormat.format("first:{0}, ", first);
    }
    if(last != null)  { 
        jsonBody += java.text.MessageFormat.format("last:{0}, ", last);
    }
    if(before != null)  { 
        jsonBody += java.text.MessageFormat.format("before:\"{0}\", ", before);
    }
    if(after != null)  { 
        jsonBody += java.text.MessageFormat.format("after:\"{0}\", ", after);
    }
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(zip != null)  { 
        jsonBody += java.text.MessageFormat.format("zip:\"{0}\", ", zip);
    }
    if(city != null)  { 
        jsonBody += java.text.MessageFormat.format("city:\"{0}\", ", city);
    }
    if(description != null)  { 
        jsonBody += java.text.MessageFormat.format("description:\"{0}\", ", description);
    }
    if(orderBy != null)  { 
        jsonBody += java.text.MessageFormat.format("orderBy:\"{0}\", ", orderBy);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(VehicleNodeConnection.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("allVehicles");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    VehicleNodeConnection nodeObject = objectMapper.readValue(responseJson, VehicleNodeConnection.class);
    return nodeObject;
    
  }

  public String user(String id) throws IOException {
    Request request = new Request();
    String jsonBody = "query { user ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(UserNode.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("user");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    UserNode nodeObject = objectMapper.readValue(responseJson, UserNode.class);
    return nodeObject;
    
  }

  public String allUsers(Integer first, Integer last, String before, String after, String id,
      String username, String email, String firstName, String lastName, String orderBy) throws
      IOException {
    Request request = new Request();
    String jsonBody = "query { allUsers ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(first != null)  { 
        jsonBody += java.text.MessageFormat.format("first:{0}, ", first);
    }
    if(last != null)  { 
        jsonBody += java.text.MessageFormat.format("last:{0}, ", last);
    }
    if(before != null)  { 
        jsonBody += java.text.MessageFormat.format("before:\"{0}\", ", before);
    }
    if(after != null)  { 
        jsonBody += java.text.MessageFormat.format("after:\"{0}\", ", after);
    }
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(username != null)  { 
        jsonBody += java.text.MessageFormat.format("username:\"{0}\", ", username);
    }
    if(email != null)  { 
        jsonBody += java.text.MessageFormat.format("email:\"{0}\", ", email);
    }
    if(firstName != null)  { 
        jsonBody += java.text.MessageFormat.format("firstName:\"{0}\", ", firstName);
    }
    if(lastName != null)  { 
        jsonBody += java.text.MessageFormat.format("lastName:\"{0}\", ", lastName);
    }
    if(orderBy != null)  { 
        jsonBody += java.text.MessageFormat.format("orderBy:\"{0}\", ", orderBy);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(UserNodeConnection.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("allUsers");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    UserNodeConnection nodeObject = objectMapper.readValue(responseJson, UserNodeConnection.class);
    return nodeObject;
    
  }

  public String city(String id) throws IOException {
    Request request = new Request();
    String jsonBody = "query { city ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(CityNode.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("city");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    CityNode nodeObject = objectMapper.readValue(responseJson, CityNode.class);
    return nodeObject;
    
  }

  public String allCities(String before, String after, Integer first, Integer last) throws
      IOException {
    Request request = new Request();
    String jsonBody = "query { allCities ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(before != null)  { 
        jsonBody += java.text.MessageFormat.format("before:\"{0}\", ", before);
    }
    if(after != null)  { 
        jsonBody += java.text.MessageFormat.format("after:\"{0}\", ", after);
    }
    if(first != null)  { 
        jsonBody += java.text.MessageFormat.format("first:{0}, ", first);
    }
    if(last != null)  { 
        jsonBody += java.text.MessageFormat.format("last:{0}, ", last);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(CityConnection.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("allCities");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    CityConnection nodeObject = objectMapper.readValue(responseJson, CityConnection.class);
    return nodeObject;
    
  }

  public String dataStore() throws IOException {
    Request request = new Request();
    String jsonBody = "query { dataStore ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(DataStoreNode.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("dataStore");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    DataStoreNode nodeObject = objectMapper.readValue(responseJson, DataStoreNode.class);
    return nodeObject;
    
  }

  public String environment() throws IOException {
    Request request = new Request();
    String jsonBody = "query { environment ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(EnvironmentNode.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("environment");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    EnvironmentNode nodeObject = objectMapper.readValue(responseJson, EnvironmentNode.class);
    return nodeObject;
    
  }

  public String reservation(String id) throws IOException {
    Request request = new Request();
    String jsonBody = "query { reservation ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(ReservationNode.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("reservation");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    ReservationNode nodeObject = objectMapper.readValue(responseJson, ReservationNode.class);
    return nodeObject;
    
  }

  public String allReservations(String state, Integer first, Integer last, String before,
      String after, String id, String startLt, String startLte, String startGt, String startGte,
      String startRange, String endLt, String endLte, String endGt, String endGte,
      Boolean isFishing, Boolean isAccessibleOnly, Boolean isCurrentUserOnly, String orderBy) throws
      IOException {
    Request request = new Request();
    String jsonBody = "query { allReservations ";
    jsonBody += " ( ";
    int parameterStrSize = jsonBody.length();
    if(state != null)  { 
        jsonBody += java.text.MessageFormat.format("state:\"{0}\", ", state);
    }
    if(first != null)  { 
        jsonBody += java.text.MessageFormat.format("first:{0}, ", first);
    }
    if(last != null)  { 
        jsonBody += java.text.MessageFormat.format("last:{0}, ", last);
    }
    if(before != null)  { 
        jsonBody += java.text.MessageFormat.format("before:\"{0}\", ", before);
    }
    if(after != null)  { 
        jsonBody += java.text.MessageFormat.format("after:\"{0}\", ", after);
    }
    if(id != null)  { 
        jsonBody += java.text.MessageFormat.format("id:\"{0}\", ", id);
    }
    if(startLt != null)  { 
        jsonBody += java.text.MessageFormat.format("startLt:\"{0}\", ", startLt);
    }
    if(startLte != null)  { 
        jsonBody += java.text.MessageFormat.format("startLte:\"{0}\", ", startLte);
    }
    if(startGt != null)  { 
        jsonBody += java.text.MessageFormat.format("startGt:\"{0}\", ", startGt);
    }
    if(startGte != null)  { 
        jsonBody += java.text.MessageFormat.format("startGte:\"{0}\", ", startGte);
    }
    if(startRange != null)  { 
        jsonBody += java.text.MessageFormat.format("startRange:\"{0}\", ", startRange);
    }
    if(endLt != null)  { 
        jsonBody += java.text.MessageFormat.format("endLt:\"{0}\", ", endLt);
    }
    if(endLte != null)  { 
        jsonBody += java.text.MessageFormat.format("endLte:\"{0}\", ", endLte);
    }
    if(endGt != null)  { 
        jsonBody += java.text.MessageFormat.format("endGt:\"{0}\", ", endGt);
    }
    if(endGte != null)  { 
        jsonBody += java.text.MessageFormat.format("endGte:\"{0}\", ", endGte);
    }
    if(isFishing != null)  { 
        jsonBody += java.text.MessageFormat.format("isFishing:{0}, ", isFishing);
    }
    if(isAccessibleOnly != null)  { 
        jsonBody += java.text.MessageFormat.format("isAccessibleOnly:{0}, ", isAccessibleOnly);
    }
    if(isCurrentUserOnly != null)  { 
        jsonBody += java.text.MessageFormat.format("isCurrentUserOnly:{0}, ", isCurrentUserOnly);
    }
    if(orderBy != null)  { 
        jsonBody += java.text.MessageFormat.format("orderBy:\"{0}\", ", orderBy);
    }
    if(parameterStrSize < jsonBody.length()) {
        jsonBody = jsonBody.substring(0, jsonBody.length() - 2);
    }
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += readFields(ReservationNodeConnection.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("allReservations");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    ReservationNodeConnection nodeObject = objectMapper.readValue(responseJson, ReservationNodeConnection.class);
    return nodeObject;
    
  }

  @Getter
  @Setter
  private class Request {
    private String query;

    private Map variables;
  }
}
