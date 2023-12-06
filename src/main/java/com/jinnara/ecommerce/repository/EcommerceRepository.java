package com.jinnara.ecommerce.repository;

import com.jinnara.ecommerce.repository.entity.Ecommerce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EcommerceRepository extends ElasticsearchRepository<Ecommerce, String> {
  Page<Ecommerce> findByCustomerFullName(String customerFullName, PageRequest pageRequest);

  Page<Ecommerce> findByDayOfWeekIn(List<String> days, PageRequest pageRequest);

  @Query("{\"terms\": {\"day_of_week\": [\"?0\"]}}")
  List<Ecommerce> findByDayOfWeek(List<String> days);


}
