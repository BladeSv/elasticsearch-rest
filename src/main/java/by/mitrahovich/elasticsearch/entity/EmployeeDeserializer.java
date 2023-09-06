package by.mitrahovich.elasticsearch.entity;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDeserializer extends StdDeserializer<Employee> {
    protected EmployeeDeserializer() {
        super(Employee.class);
    }

    @Override
    public Employee deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode employeeNode = p.getCodec().readTree(p);
        JsonNode sourceNode = employeeNode.path("_source");
        JsonNode addressNode = sourceNode.path("address");
        ArrayNode datasetArray = (ArrayNode) sourceNode.get("skills");
        List<String> skills = new ArrayList<>();
        datasetArray.forEach(node -> skills.add(node.textValue()));
        Employee employee = Employee.builder()._id(employeeNode.get("_id").textValue())
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

        return employee;
    }
}
