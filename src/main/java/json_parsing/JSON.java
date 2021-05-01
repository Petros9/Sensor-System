package json_parsing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
     private static ObjectMapper objectMapper = getDefaultObjectMapper();
     private static ObjectMapper getDefaultObjectMapper(){
          ObjectMapper objectMapper = new ObjectMapper();


          return objectMapper;
     }

     public static JsonNode parse(String string) throws JsonProcessingException {
          return objectMapper.readTree(string);
     }
}
