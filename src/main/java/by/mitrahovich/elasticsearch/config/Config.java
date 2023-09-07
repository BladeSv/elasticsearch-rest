package by.mitrahovich.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public RestClientBuilder getRestClient() {
        return RestClient.builder(new HttpHost("elasticsearch", 9200, "http"));
    }
}
