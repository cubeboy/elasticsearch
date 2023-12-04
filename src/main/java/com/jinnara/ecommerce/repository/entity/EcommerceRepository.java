package com.jinnara.ecommerce.repository.entity;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EcommerceRepository extends ElasticsearchRepository<Ecommerce, String> {
  List<Ecommerce> findByCustomerFullName(String customerFullName);

  @Query("{\"terms\": {\"day_of_week\": [\"?0\"]}}")
  List<Ecommerce> findByDayOfWeek(List<String> days);
}
