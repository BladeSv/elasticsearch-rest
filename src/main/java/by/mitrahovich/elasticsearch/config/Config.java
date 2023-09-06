package by.mitrahovich.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
public class Config {


    @Bean
    public RestClientBuilder getRestClient() {
        return RestClient.builder(new HttpHost("localhost", 9200, "http"));
    }

    @Bean
    public CommandLineRunner initIndexes(RestClientBuilder clientBuilder) {
        return (args -> {
            RestClient client = clientBuilder.build();
            Request request = new Request(
                    "HEAD",
                    "/employees");
            Response response = client.performRequest(request);
            if (response.getStatusLine().getStatusCode() == 404) {
                Request requestEmployees = getEmployeesRequest();
                Response responseEmployees = client.performRequest(requestEmployees);
                String responseText = EntityUtils.toString(responseEmployees.getEntity());
                System.out.println(responseText);
            }
            client.close();
        });
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
