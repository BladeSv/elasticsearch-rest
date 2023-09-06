package by.mitrahovich.elasticsearch.tasks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Configuration;

@Slf4j
@AllArgsConstructor
@Configuration
public class Tasks {

    private RestClientBuilder clientBuilder;

//    @Order(1)
//    @Bean
//    public CommandLineRunner task1(RestClientBuilder clientBuilder) {
//        return args -> {
//            RestClient client = clientBuilder.build();
//            Request request = new Request(
//                    "POST",
//                    "/employees/_search");
//            Response response = client.performRequest(request);
//            String responseJson = EntityUtils.toString(response.getEntity());
//            log.info("Get all employees\n" + responseJson);
//        };
//    }
//
//    @Order(2)
//    @Bean
//    public CommandLineRunner task2(RestClientBuilder clientBuilder) {
//        return args -> {
//            RestClient client = clientBuilder.build();
//            Request request = new Request(
//                    "POST",
//                    "/employees/_search");
//            Response response = client.performRequest(request);
//            String responseJson = EntityUtils.toString(response.getEntity());
//            log.info("Get all employees\n" + responseJson);
//        };
//    }
}
