package com.jinnara.ecommerce.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
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
    return new RestClientTransport(restClient, new JacksonJsonpMapper());
  }

  @Bean
  public ElasticsearchClient elasticsearchClient(RestClientTransport restClientTransport) {
    return new ElasticsearchClient(restClientTransport);
  }
}
