package tnut.searchengine.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
@ComponentScan(basePackages = "tnut.searchengine")
public class Config extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        ClientConfiguration config = ClientConfiguration
                .builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(config).rest();
    }
}
