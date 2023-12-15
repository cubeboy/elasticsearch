package com.jinnara.ecommerce.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jinnara.ecommerce.config.ElasticsearchConfig;
import com.jinnara.ecommerce.repository.entity.QueryParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ElasticsearchConfig.class})
public class ElasticQueryTest {
  private final Logger log = LoggerFactory.getLogger(ElasticQueryTest.class);
  private final MapType type = TypeFactory
      .defaultInstance()
      .constructMapType(HashMap.class, String.class, Object.class);
  @Autowired
  ElasticsearchClient esClient;

  @Test
  public void queryTest() throws IOException {
    List<String> days = List.of("Sunday", "Monday");
    QueryParam param = QueryParam.builder()
        .customerFullName("reyes")
        .dayOfWeeks(days)
        .build();

    SearchRequest request = new SearchRequest.Builder()
        .index("kibana_sample_data_ecommerce")
        .query(q -> q
            .bool(b -> b
                .must(param.getMatchQueryList())
                .mustNot(param.getTermsQueryList())
            )
        ).build();
    log.info("SearchRequest ==>> {}", request);
    SearchResponse response = esClient.search(request, type);
    Assertions.assertEquals(25, response.hits().total().value());
  }
}
