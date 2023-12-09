package com.jinnara.ecommerce.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ElasticsearchConfig {
  @Bean
  public RestClient restClient() {
    return RestClient
        .builder(org.apache.http.HttpHost.create("localhost:9200"))
        .build();
  }

  @Bean
  public RestClientTransport restClientTransport(RestClient restClient) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return new RestClientTransport(restClient, new JacksonJsonpMapper(mapper));
  }

  @Bean
  public ElasticsearchClient elasticsearchClient(RestClientTransport restClientTransport) {
    return new ElasticsearchClient(restClientTransport);
  }
}
