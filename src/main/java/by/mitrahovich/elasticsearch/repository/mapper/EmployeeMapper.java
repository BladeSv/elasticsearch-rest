package by.mitrahovich.elasticsearch.repository.mapper;


import by.mitrahovich.elasticsearch.entity.Employee;
import by.mitrahovich.elasticsearch.entity.EmployeeDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;

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
        return mapper.writeValueAsString(employee);
    }
}

