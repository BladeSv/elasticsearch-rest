package by.mitrahovich.elasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientOptions;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public RestClientBuilder getRestClient() {
        return RestClient.builder(new HttpHost("localhost", 9200, "http"));
    }


    @Bean
    public ElasticsearchClient getElasticsearchClient(RestClientTransport restClientTransport) {
//        ElasticsearchTransport transport = new RestClientTransport(clientBuilder.build(), new JacksonJsonpMapper());
        ElasticsearchClient esClient = new ElasticsearchClient(restClientTransport);

        return esClient;
    }

    @Bean
    RestClientTransport restClientTransport(RestClientBuilder clientBuilder, ObjectProvider<RestClientOptions> restClientOptions) {
        return new RestClientTransport(clientBuilder.build(), new JacksonJsonpMapper(), restClientOptions.getIfAvailable());
    }
}
