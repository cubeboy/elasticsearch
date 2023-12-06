package com.jinnara.ecommerce.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {ElasticsearchConfig.class})
public class EcommerceRepositoryTests {
  Logger log = LoggerFactory.getLogger(EcommerceRepositoryTests.class);
//  @Autowired
//  private EcommerceRepository ecommerceRepository;
//
//  @Test
//  void testFindByCustomerFullName() {
//    String name = "mary";
//    List<Ecommerce> result = ecommerceRepository.findByCustomerFullName(name);
//
//    assertFalse("Result is Empty", result.isEmpty());
//    assertTrue("Customer Full Name not matched",
//        result.get(0).getCustomerFullName().toLowerCase().contains(name));
//
//    result.forEach(customer -> {
//      log.info("customer info ==> {}", customer);
//    });
//  }
//
//  @Test
//  void testFindByDayOfWeek() {
//    List<String> days = Arrays.asList("Monday", "Sunday");
//    List<Ecommerce> results = ecommerceRepository.findByDayOfWeek(days);
//    assertFalse("Result is empty", results.isEmpty());
//    results.forEach(customer -> {
//      log.info("customer info ==> {}", customer);
//    });
//  }
}
