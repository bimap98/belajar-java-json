package programmerzamannow.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JsonObjectTest {

    @Test
    void createJson() throws JsonProcessingException {
        Map<String, Object> person = Map.of(
                "firstName", "Budi",
                "lastName", "Nugraha",
                "age", 30,
                "married", true,
                "address", Map.of(
                        "street", "Jalan belum jadi",
                        "city", "Jakarta",
                        "country", "Indonesia"
                )
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);
    }

    @Test
    void readJson() throws JsonProcessingException {
        String json = """
                {
                  "age": 30,
                  "married": true,
                  "lastName": "Nugraha",
                  "firstName": "Budi",
                  "address": {
                    "street": "Jalan belum jadi",
                    "country": "Indonesia",
                    "city": "Jakarta"
                  }
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> person = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });

        Assertions.assertEquals("Budi", person.get("firstName"));
        Assertions.assertEquals("Nugraha", person.get("lastName"));

    }
}
