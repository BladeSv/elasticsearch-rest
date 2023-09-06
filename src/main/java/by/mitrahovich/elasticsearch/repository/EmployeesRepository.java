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

    public String getEmployeesById(String id) throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "POST",
                "/employees/_search?pretty");
        Response response = client.performRequest(request);
        client.close();

        return EntityUtils.toString(response.getEntity());
    }

}
