package com.jinnara.ecommerce.repository;

import com.jinnara.ecommerce.repository.entity.Ecommerce;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
@RequiredArgsConstructor
public class TemplateRepository {
  private final ElasticsearchRestTemplate restTemplate;

  public List<Ecommerce> findByCustomerFullName(String name) {
    NativeSearchQuery query = new NativeSearchQueryBuilder()
        .withQuery(matchQuery("customer_full_name", name))
        .build();
    return restTemplate.queryForList(query, Ecommerce.class);
  }
}
