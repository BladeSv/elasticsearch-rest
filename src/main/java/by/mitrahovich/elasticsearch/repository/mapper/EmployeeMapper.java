package by.mitrahovich.elasticsearch.repository.mapper;


import by.mitrahovich.elasticsearch.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;

public class EmployeeMapper {

    private static ObjectMapper mapper;

    static {
        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new BeanDeserializerModifier() {
            @Override
            public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
                if (beanDesc.getBeanClass() == Employee.class)
                    return new EmployeeDeserializer(deserializer);
                return deserializer;
            }
        });
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    public static List<Employee> toEmployees(String elasticsearchResponse) throws IOException {
        JsonNode jsonNode = mapper.readTree(elasticsearchResponse);
        JsonNode node = jsonNode.path("hits").path("hits");
        ObjectReader objectReader = mapper.readerForListOf(Employee.class);

        return objectReader.readValue(node);
    }

    public static String employeeToJson(Employee employee) throws JsonProcessingException {
        String updatedJson = mapper.writeValueAsString(employee);
        JsonNode jsonNode = mapper.readTree(updatedJson);
        ObjectNode object = (ObjectNode) jsonNode;
        object.remove("_id");
        return mapper.writeValueAsString(object);
    }
}

