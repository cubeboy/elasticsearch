package com.jinnara.ecommerce.repository;

import com.jinnara.ecommerce.config.ElasticsearchConfig;
import com.jinnara.ecommerce.repository.entity.Ecommerce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest(classes = {EcommerceRepository.class})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ElasticsearchConfig.class})
public class EcommerceRepositoryTests {
  Logger log = LoggerFactory.getLogger(EcommerceRepositoryTests.class);

  @Autowired
  ApplicationContext context;

  @Autowired
  EcommerceRepository ecommerceRepository;

  @Test
  public void contextInfo() {
    List.of(context.getBeanDefinitionNames())
        .forEach(name -> log.info("bean name ==> {}", name));
    Assertions.assertNotNull(ecommerceRepository, "Require ecommerceRepository");
  }

  @Test
  public void testFindByCustomerFullName() throws Exception {
    Ecommerce ecommerce = ecommerceRepository.findByCustomerFullName("mary");
    assertNotNull("Find By customer_full_name", ecommerce);
    log.info("findByCustomerFullName result ==> {}", ecommerce);
  }

  @Test void testFindByDayOfWeekIn() throws Exception {
    List<String> days = List.of("Sunday", "Monday");
    List<Ecommerce> ecommerceList = ecommerceRepository.findByDayOfWeekIn(days);
    Assertions.assertFalse(ecommerceList.isEmpty(), "Find findByDaysOfWeek");
    log.info("findByDayOfWeekIn result ==> {}", ecommerceList.size());
    log.info("findByDayOfWeekIn result ==> {}", ecommerceList);
  }
}
