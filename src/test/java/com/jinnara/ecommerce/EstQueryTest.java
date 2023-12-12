package com.jinnara.ecommerce;

import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.core.CreateRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinnara.ecommerce.config.ElasticsearchConfig;
import org.apache.catalina.connector.InputBuffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ElasticsearchConfig.class})
public class EstQueryTest {
  private final Logger log = LoggerFactory.getLogger(EstQueryTest.class);

  @Test
  public void createIndexTest() throws Exception {
    try(
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("product.json");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    ) {
//      String jsonMapping = reader.lines().collect(Collectors.joining("\n"));
//      ObjectMapper objectMapper = new ObjectMapper();
//      Map<String, Object> jsonMap = objectMapper.readValue(jsonMapping, Map.class);
//      log.info("jsonMap ===>>> {}", jsonMap);
//      Assertions.assertFalse(jsonMap.isEmpty());

      CreateIndexRequest createIndexRequest = CreateIndexRequest.of(r -> r
          .index("products")
          .mappings(TypeMapping.of(m -> m.withJson(inputStream)))
      );
      log.info("createIndexRequest ===> {}", createIndexRequest);
    }
  }
}
