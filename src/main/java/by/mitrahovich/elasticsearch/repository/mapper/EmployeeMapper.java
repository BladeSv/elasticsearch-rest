package by.mitrahovich.elasticsearch.repository.mapper;


import by.mitrahovich.elasticsearch.entity.Employee;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

public class EmployeeMapper {
    public static List<Employee> toEmployees(String elasticsearchResponse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(elasticsearchResponse);
        JsonNode node = jsonNode.path("hits").path("hits");
        ObjectReader objectReader = mapper.readerForListOf(Employee.class);

        return objectReader.readValue(node);
    }
}
