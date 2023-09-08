package by.mitrahovich.elasticsearch.repository;

import by.mitrahovich.elasticsearch.entity.Employee;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Repository
public class EmployeesRepository {

    private ElasticsearchClient client;

    public List<Employee> getAllEmployees() throws IOException {
        SearchResponse<Employee> employeesResponse = client.search(s -> s
                        .index("employees")
                , Employee.class);
        List<Employee> employees = employeesResponse.hits().hits().stream()
                .map(hit -> {
                    Employee source = hit.source();
                    source.setId(hit.id());
                    return source;
                }).toList();

        return employees;
    }

    public Employee getEmployeesById(String id) throws IOException {
        GetRequest employeesReq = new GetRequest.Builder()
                .index("employees")
                .id(id)
                .build();
        GetResponse<Employee> employeeGetResponse = client.get(employeesReq, Employee.class);
        Employee source = employeeGetResponse.source();
        source.setId(employeeGetResponse.id());

        return source;
    }

    public List<Employee> getEmployeesByField(String field, String value) throws IOException {
        List<Employee> employees = client.search(s -> s
                                .index("employees")
                                .query(q -> q
                                        .match(m -> m
                                                .field(field)
                                                .query(value))),
                        Employee.class)
                .hits()
                .hits().stream()
                .map(hit -> {
                    Employee source = hit.source();
                    source.setId(hit.id());
                    return source;
                })
                .toList();

        return employees;
    }

    public String createEmployee(Employee employee) throws IOException {
        CreateRequest<Object> employees = new CreateRequest.Builder<>()
                .index("employees")
                .id(employee.getId())
                .document(employee)
                .build();
        CreateResponse createResponse = client.create(employees);
        String id = createResponse.id();

        return "id: " + id;
    }

    public String removeEmployee(String id) throws IOException {
        DeleteRequest employeesReq = new DeleteRequest.Builder()
                .index("employees")
                .id(id)
                .build();
        DeleteResponse delete = client.delete(employeesReq);
        return "id: " + delete.id();
    }

    public String getAvgAggregation(String metricField, String aggregationField) throws IOException {
        SearchResponse<Employee> search = client.search(s ->
                        s.index("employees")
                                .size(0)
                                .aggregations(metricField,
                                        a -> a.avg(avg -> avg
                                                .field(aggregationField)))
                , Employee.class);
        double value = search.aggregations().get(metricField).avg().value();

        return String.format("Avg of field %s: %s", aggregationField, value);
    }
}
