package com.jinnara.ecommerce.repository;

import com.jinnara.ecommerce.repository.entity.Ecommerce;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
@RequiredArgsConstructor
public class TemplateRepository {
  private final ElasticsearchRestTemplate restTemplate;

  public Page<Ecommerce> findByCustomerFullName(String name, PageRequest pageRequest) {
    NativeSearchQuery query = new NativeSearchQueryBuilder()
        .withQuery(matchQuery("customer_full_name", name))
        .withPageable(pageRequest)
        .build();
    return restTemplate.queryForPage(query, Ecommerce.class);
  }

  public Page<Ecommerce> findByDayOfWeekIn(List<String> days, PageRequest pageRequest){
    NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(QueryBuilders.termsQuery("day_of_week", days))
        .withPageable(pageRequest)
        .build();
    return restTemplate.queryForPage(searchQuery, Ecommerce.class);
  }
}
