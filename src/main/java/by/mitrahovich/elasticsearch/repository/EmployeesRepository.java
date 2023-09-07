package by.mitrahovich.elasticsearch.repository;

import by.mitrahovich.elasticsearch.entity.Employee;
import by.mitrahovich.elasticsearch.repository.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Repository
public class EmployeesRepository {

    private RestClientBuilder clientBuilder;

    public List<Employee> getAllEmployees() throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "POST",
                "/employees/_search?pretty");
        Response response = client.performRequest(request);
        client.close();

        return EmployeeMapper.toEmployees(EntityUtils.toString(response.getEntity()));
    }

    public Employee getEmployeesById(String id) throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "POST",
                "/employees/_search?pretty");
        String query = String.format("{\"query\":{\"term\":{\"_id\":{\"value\":\"%s\"}}}}", id);
        request.setJsonEntity(query);
        Response response = client.performRequest(request);
        client.close();

        return EmployeeMapper.toEmployees(EntityUtils.toString(response.getEntity())).stream().findFirst().orElse(null);
    }

    public List<Employee> getEmployeesByField(String field, String value) throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "POST",
                "/employees/_search?pretty");
        String query = String.format("{\"query\":{\"match\":{\"%s\":{\"query\":\"%s\"}}}}", field, value);
        request.setJsonEntity(query);
        Response response = client.performRequest(request);
        client.close();

        return EmployeeMapper.toEmployees(EntityUtils.toString(response.getEntity()));
    }

    public String createEmployee(Employee employee) throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "POST",
                String.format("/employees/_create/%s", employee.get_id()));

        String employeeJson = EmployeeMapper.employeeToJson(employee);
        request.setJsonEntity(employeeJson);
        Response response = client.performRequest(request);
        client.close();

        return EntityUtils.toString(response.getEntity());
    }

    public String removeEmployee(String id) throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "DELETE",
                String.format("/employees/_doc/%s", id));

        Response response = client.performRequest(request);
        client.close();

        return EntityUtils.toString(response.getEntity());
    }

    public String getAggregation(String metricField, String metricType, String aggregationField) throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "POST",
                "/employees/_search?pretty");
        String aggregationJson = String.format("{\"size\":0,\"aggs\":{\"%s\":{\"%s\":{\"field\":\"%s\"}}}}", metricField, metricType, aggregationField);
        request.setJsonEntity(aggregationJson);
        Response response = client.performRequest(request);
        client.close();

        return EntityUtils.toString(response.getEntity());
    }
}
