package com.jinnara.ecommerce.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jinnara.ecommerce.repository.entity.Ecommerce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EcommerceRepository {
  private final static String INDEX_NAME = "kibana_sample_data_ecommerce";
  private final ElasticsearchClient esClient;

  public Ecommerce findByCustomerFullName(String name) throws Exception {
    SearchResponse<Ecommerce> response = esClient.search(s -> s
        .index(INDEX_NAME)
        .query(q -> q
            .match(t -> t
                .field("customer_full_name")
                .query(name))),
        Ecommerce.class
    );
    log.info("SearchResponse<Product> ====>>>> {}", response);
    if(response.hits().hits().isEmpty()) {
      throw new Exception("Not Found Commerce data");
    }
    return response.hits().hits().stream()
        .findFirst()
        .orElseThrow()
        .source();
  }


  public List<Ecommerce> findByDayOfWeekIn(List<String> days) throws Exception {
    List<FieldValue> dayValueList = days.stream()
        .map(day -> new FieldValue.Builder()
            .stringValue(day)
            .build()
        )
        .collect(Collectors.toList());

    SearchRequest request = new SearchRequest.Builder()
        .index(INDEX_NAME)
        .from(0)
        .size(10)
        .query(q -> q
            .bool(b -> b
                .filter(f -> f
                    .terms(t -> t
                        .field("day_of_week")
                        .terms(v -> v.value(dayValueList))
                    )
                )
            )
        )
        .build();
    log.info("SearchRequest ===>>> {}", request);
    SearchResponse<Ecommerce> response = esClient.search(
        request,
        Ecommerce.class
    );
    assert response.hits().total() != null;
    log.info("SearchResponse<Product> total ====>>>> {}", response.hits().total().value());
    log.info("SearchResponse<Product> size ====>>>> {}", response.hits().hits().size());
    return response.hits().hits().stream()
        .map(Hit::source)
        .collect(Collectors.toList());
  }

  public List<Ecommerce> findByCustomerFullName(String name, String excludeDayOfWeek)
      throws Exception {
    SearchRequest request = new SearchRequest.Builder()
        .index(INDEX_NAME)
        .from(0)
        .size(10000)
        .query(q -> q
            .bool(b -> b
                .must(m -> m
                    .match(mt -> mt
                        .field("customer_full_name")
                        .query(name)
                    )
                )
                .mustNot(mn -> mn
                    .term(t -> t
                        .field("day_of_week")
                        .value(excludeDayOfWeek)
                    )
                )
            )
        )
        .build();
    log.info("SearchRequest ===>>> {}", request);
    SearchResponse<Ecommerce> response = esClient.search(
        request,
        Ecommerce.class
    );
    assert response.hits().total() != null;
    log.info("SearchResponse<Product> total ====>>>> {}", response.hits().total().value());
    log.info("SearchResponse<Product> size ====>>>> {}", response.hits().hits().size());
    log.info("SearchResponse<Product> ====>>>> {}", response);

    return response.hits().hits().stream()
        .map(Hit::source)
        .collect(Collectors.toList());
  }

  public List<Map<String, Object>> findByCustomerFullNameToMap(String name, String excludeDayOfWeek)
      throws Exception {
    TypeFactory factory = TypeFactory.defaultInstance();
    MapType type = factory.constructMapType(HashMap.class, String.class, Object.class);

    SearchRequest request = new SearchRequest.Builder()
        .index(INDEX_NAME)
        .from(0)
        .size(10000)
        .query(q -> q
            .bool(b -> b
                .must(m -> m
                    .match(mt -> mt
                        .field("customer_full_name")
                        .query(name)
                    )
                )
                .mustNot(mn -> mn
                    .term(t -> t
                        .field("day_of_week")
                        .value(excludeDayOfWeek)
                    )
                )
            )
        )
        .build();
    log.info("SearchRequest ===>>> {}", request);
    SearchResponse<Map<String, Object>> response = esClient.search(
        request,
        type
    );
    assert response.hits().total() != null;
    log.info("SearchResponse<Product> total ====>>>> {}", response.hits().total().value());
    log.info("SearchResponse<Product> size ====>>>> {}", response.hits().hits().size());
    log.info("SearchResponse<Product> ====>>>> {}", response);

    return response.hits().hits().stream()
        .map(Hit::source)
        .collect(Collectors.toList());
  }
}
