package services.videa.graphql.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.Class;
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
public class Mutation {
  private RestTemplate restTemplate;

  private DefaultUriBuilderFactory defaultUriBuilderFactory;

  private ObjectMapper objectMapper;

  public Mutation(String url, String token) {
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

  public ObtainJSONWebTokenPayload tokenAuth(ObtainJSONWebTokenInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { tokenAuth ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(ObtainJSONWebTokenPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("tokenAuth");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    ObtainJSONWebTokenPayload nodeObject = objectMapper.readValue(responseJson, ObtainJSONWebTokenPayload.class);
    return nodeObject;
    
  }

  public VerifyPayload verifyToken(VerifyInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { verifyToken ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(VerifyPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("verifyToken");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    VerifyPayload nodeObject = objectMapper.readValue(responseJson, VerifyPayload.class);
    return nodeObject;
    
  }

  public RefreshPayload refreshToken(RefreshInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { refreshToken ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(RefreshPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("refreshToken");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    RefreshPayload nodeObject = objectMapper.readValue(responseJson, RefreshPayload.class);
    return nodeObject;
    
  }

  public RevokePayload revokeToken(RevokeInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { revokeToken ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(RevokePayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("revokeToken");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    RevokePayload nodeObject = objectMapper.readValue(responseJson, RevokePayload.class);
    return nodeObject;
    
  }

  public CreateUserPayload createUser(CreateUserInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { createUser ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(CreateUserPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("createUser");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    CreateUserPayload nodeObject = objectMapper.readValue(responseJson, CreateUserPayload.class);
    return nodeObject;
    
  }

  public UpdateDataStorePayload updateDataStore(UpdateDataStoreInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { updateDataStore ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(UpdateDataStorePayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("updateDataStore");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    UpdateDataStorePayload nodeObject = objectMapper.readValue(responseJson, UpdateDataStorePayload.class);
    return nodeObject;
    
  }

  public CreateReservationPayload createReservation(CreateReservationInput input) throws
      IOException {
    Request request = new Request();
    String jsonBody = "mutation { createReservation ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(CreateReservationPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("createReservation");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    CreateReservationPayload nodeObject = objectMapper.readValue(responseJson, CreateReservationPayload.class);
    return nodeObject;
    
  }

  public UpdateReservationPayload updateReservation(UpdateReservationInput input) throws
      IOException {
    Request request = new Request();
    String jsonBody = "mutation { updateReservation ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(UpdateReservationPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("updateReservation");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    UpdateReservationPayload nodeObject = objectMapper.readValue(responseJson, UpdateReservationPayload.class);
    return nodeObject;
    
  }

  public DeleteReservationPayload deleteReservation(DeleteReservationInput input) throws
      IOException {
    Request request = new Request();
    String jsonBody = "mutation { deleteReservation ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(DeleteReservationPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("deleteReservation");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    DeleteReservationPayload nodeObject = objectMapper.readValue(responseJson, DeleteReservationPayload.class);
    return nodeObject;
    
  }

  public AccessReservationPayload accessReservation(AccessReservationInput input) throws
      IOException {
    Request request = new Request();
    String jsonBody = "mutation { accessReservation ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(AccessReservationPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("accessReservation");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    AccessReservationPayload nodeObject = objectMapper.readValue(responseJson, AccessReservationPayload.class);
    return nodeObject;
    
  }

  public FinishReservationPayload finishReservation(FinishReservationInput input) throws
      IOException {
    Request request = new Request();
    String jsonBody = "mutation { finishReservation ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(FinishReservationPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("finishReservation");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    FinishReservationPayload nodeObject = objectMapper.readValue(responseJson, FinishReservationPayload.class);
    return nodeObject;
    
  }

  public UnlockVehiclePayload unlockVehicle(UnlockVehicleInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { unlockVehicle ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(UnlockVehiclePayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("unlockVehicle");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    UnlockVehiclePayload nodeObject = objectMapper.readValue(responseJson, UnlockVehiclePayload.class);
    return nodeObject;
    
  }

  public LockVehiclePayload lockVehicle(LockVehicleInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { lockVehicle ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(LockVehiclePayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("lockVehicle");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    LockVehiclePayload nodeObject = objectMapper.readValue(responseJson, LockVehiclePayload.class);
    return nodeObject;
    
  }

  public CreateMembershipPayload createMembership(CreateMembershipInput input) throws IOException {
    Request request = new Request();
    String jsonBody = "mutation { createMembership ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(CreateMembershipPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("createMembership");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    CreateMembershipPayload nodeObject = objectMapper.readValue(responseJson, CreateMembershipPayload.class);
    return nodeObject;
    
  }

  public ApproveMembershipPayload approveMembership(ApproveMembershipInput input) throws
      IOException {
    Request request = new Request();
    String jsonBody = "mutation { approveMembership ";
    jsonBody += " ( ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderInputFields(input);
    jsonBody += " ) ";
    jsonBody += " { ";
    jsonBody += services.videa.graphql.java.rendering.GqlRenderer.renderReturnFields(ApproveMembershipPayload.class);
    jsonBody += " } ";
    jsonBody += " } ";
    request.setQuery(jsonBody);
    request.setVariables(java.util.Collections.emptyMap());
    Object response = restTemplate.postForObject("", request, Object.class);
    java.util.Map map = objectMapper.convertValue(response, java.util.Map.class);
    Map nodeMap = (Map)((Map)map.get("data")).get("approveMembership");
    String responseJson = objectMapper.writeValueAsString(nodeMap);
    ApproveMembershipPayload nodeObject = objectMapper.readValue(responseJson, ApproveMembershipPayload.class);
    return nodeObject;
    
  }

  @Getter
  @Setter
  private class Request {
    private String query;

    private Map variables;
  }
}
