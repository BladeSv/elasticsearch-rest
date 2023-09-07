package by.mitrahovich.elasticsearch.config;

import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.stereotype.Component;

import java.io.*;

@AllArgsConstructor
@Component
public class FillerEmployeeIndex {

    private RestClientBuilder clientBuilder;

    public void fillDb() throws IOException {
        RestClient client = clientBuilder.build();
        Request request = new Request(
                "HEAD",
                "/employees");
        Response response = client.performRequest(request);
        if (response.getStatusLine().getStatusCode() == 404) {
            Request requestEmployees = getEmployeesRequest();
            Response responseEmployees = client.performRequest(requestEmployees);
        }
        client.close();
    }

    private Request getEmployeesRequest() throws FileNotFoundException {
        Request requestEmployees = new Request(
                "POST",
                "/employees/_bulk");
        File initialFile = new File("./employees.json");
        InputStream targetStream = new BufferedInputStream(new FileInputStream(initialFile));
        InputStreamEntity inputStreamEntity = new InputStreamEntity(targetStream);
        inputStreamEntity.setContentType(ContentType.APPLICATION_JSON.toString());
        requestEmployees.setEntity(inputStreamEntity);
        return requestEmployees;
    }
}
