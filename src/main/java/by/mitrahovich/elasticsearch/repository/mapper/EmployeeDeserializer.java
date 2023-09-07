package by.mitrahovich.elasticsearch.repository.mapper;

import by.mitrahovich.elasticsearch.entity.Address;
import by.mitrahovich.elasticsearch.entity.Employee;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDeserializer extends StdDeserializer<Employee> {

    private JsonDeserializer<?> defaultDeserializer;

    public EmployeeDeserializer(JsonDeserializer<?> defaultDeserializer) {
        super(Employee.class);
        this.defaultDeserializer = defaultDeserializer;
    }


    @Override
    public Employee deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode employeeNode = p.getCodec().readTree(p);
        JsonNode sourceNode = employeeNode.path("_source");
        boolean empty = sourceNode.isEmpty();
        Employee employee;
        if (!empty) {
            JsonNode addressNode = sourceNode.path("address");
            ArrayNode datasetArray = (ArrayNode) sourceNode.get("skills");
            List<String> skills = new ArrayList<>();
            datasetArray.forEach(node -> skills.add(node.textValue()));
            employee = Employee.builder()._id(employeeNode.get("_id").textValue())
                    .name(sourceNode.get("name").textValue())
                    .dob(sourceNode.get("dob").textValue())
                    .address(new Address(addressNode.get("country").textValue(), addressNode.get("town").textValue()))
                    .email(sourceNode.get("email").textValue())
                    .skills(skills)
                    .experience(sourceNode.get("experience").intValue())
                    .rating(sourceNode.get("rating").doubleValue())
                    .description(sourceNode.get("description").textValue())
                    .verified((sourceNode.get("verified").booleanValue()))
                    .salary(sourceNode.get("salary").intValue())
                    .build();
        } else {
            employee = (Employee) defaultDeserializer.deserialize(p, ctxt);
        }

        return employee;
    }
}
