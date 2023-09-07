package by.mitrahovich.elasticsearch.config;

import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@Component
public class FillerEmployeeIndex {

    private RestClientBuilder clientBuilder;

    private ApplicationContext ctx;

    public String fillDb() throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "HEAD",
                "/employees");
        String responseEmployeesStr = "Employee DB already have filled";
        Response response = client.performRequest(request);
        if (response.getStatusLine().getStatusCode() == 404) {
            Request requestEmployees = getEmployeesRequest();
            Response responseEmployees = client.performRequest(requestEmployees);
            responseEmployeesStr = EntityUtils.toString(responseEmployees.getEntity());
        }
        client.close();
        return responseEmployeesStr;
    }

    private Request getEmployeesRequest() throws IOException {
        Request requestEmployees = new Request(
                "POST",
                "/employees/_bulk");
        InputStream targetStream = new BufferedInputStream(new ClassPathResource("employees.json").getInputStream());
        InputStreamEntity inputStreamEntity = new InputStreamEntity(targetStream);
        inputStreamEntity.setContentType(ContentType.APPLICATION_JSON.toString());
        requestEmployees.setEntity(inputStreamEntity);
        return requestEmployees;
    }
}
