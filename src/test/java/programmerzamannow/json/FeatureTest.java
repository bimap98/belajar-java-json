package programmerzamannow.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FeatureTest {

    @Test
    void mapperTest() throws JsonProcessingException {
        String json = """
                {"ID": "1", "Name":"Eko"}
                """;

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        Person person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Eko", person.getName());

    }

    @Test
    void deserializationFeature() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        String json = """
                {"ID": "1", "name": "Eko", "age": 20, "hobbies": "Coding"}
                """;

        Person person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Eko", person.getName());
        Assertions.assertEquals(List.of("Coding"), person.getHobbies());

    }

    @Test
    void serializationFeature() throws JsonProcessingException {
        Person person = new Person();
        person.setId("1");
        person.setName("Eko");
        person.setHobbies(List.of("Coding", "Traveling"));

        Address address = new Address();
        address.setStreet("Jalan belum jadi");
        address.setCity("Jakarta");
        address.setCountry("Indonesia");
        person.setAddress(address);

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true);
        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);
    }

    @Test
    void serializationInclusion() throws JsonProcessingException {
        Person person = new Person();
        person.setId("1");
        person.setName("Eko");

        ObjectMapper objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);
    }
}
