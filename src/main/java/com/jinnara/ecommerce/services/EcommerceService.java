package com.jinnara.ecommerce.services;

import com.jinnara.ecommerce.repository.EcommerceRepository;
import com.jinnara.ecommerce.repository.TemplateRepository;
import com.jinnara.ecommerce.repository.entity.Ecommerce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EcommerceService {
//  private final EcommerceRepository ecommerceRepository;
  private final TemplateRepository ecommerceRepository;

  public List<Ecommerce> findByName(String name) {
//    return ecommerceRepository.findByCustomerFullName(name);
    Page<Ecommerce> result;
    result = ecommerceRepository.findByCustomerFullName(name, PageRequest.of(0, 10));
    log.info("Ecommerce result.TotalElements ==> {}", result.getTotalElements());
    log.info("Ecommerce result.TotalPages ==> {}", result.getTotalPages());
    log.info("Ecommerce result.Pageable ==> {}", result.getPageable());
    log.info("Ecommerce result.Number ==> {}", result.getNumber());
    log.info("Ecommerce result.Size ==> {}", result.getSize());
    return result.getContent();
  }

  public List<Ecommerce> findByDayOfWeeks(List<String> days) {
    return ecommerceRepository.findByDayOfWeekIn(days, PageRequest.of(0, 10))
        .getContent();
  }
}
