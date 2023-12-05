package com.jinnara.ecommerce.repository;

import com.jinnara.ecommerce.config.ElasticsearchConfig;
import com.jinnara.ecommerce.repository.entity.Ecommerce;
import com.jinnara.ecommerce.repository.entity.EcommerceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ElasticsearchConfig.class})
public class EcommerceRepositoryTests {
  Logger log = LoggerFactory.getLogger(EcommerceRepositoryTests.class);
  @Autowired
  private EcommerceRepository ecommerceRepository;

  @Test
  void testFindByCustomerFullName() {
    String name = "mary";
    List<Ecommerce> result = ecommerceRepository.findByCustomerFullName(name);

    assertFalse("Result is Empty", result.isEmpty());
    assertTrue("Customer Full Name not matched",
        result.get(0).getCustomerFullName().toLowerCase().contains(name));

    result.forEach(customer -> {
      log.info("customer info ==> {}", customer);
    });
  }

  @Test
  void testFindByDayOfWeek() {
    List<String> days = Arrays.asList("Monday", "Sunday");
    List<Ecommerce> results = ecommerceRepository.findByDayOfWeek(days);
    assertFalse("Result is empty", results.isEmpty());
    results.forEach(customer -> {
      log.info("customer info ==> {}", customer);
    });
  }
}
