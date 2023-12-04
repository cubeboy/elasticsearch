package com.jinnara.ecommerce.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
@RequiredArgsConstructor
public class ElasticsearchConfig {
  private final ElasticsearchClient elasticsearchClient;

  @Bean
  public ElasticsearchOperations elasticsearchOperations() {
    return new ElasticsearchTemplate(elasticsearchClient);
  }
}
