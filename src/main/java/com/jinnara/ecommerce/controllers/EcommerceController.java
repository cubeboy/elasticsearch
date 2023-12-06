package com.jinnara.ecommerce.controllers;

import com.jinnara.ecommerce.repository.entity.Ecommerce;
import com.jinnara.ecommerce.services.EcommerceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EcommerceController {
  private final EcommerceService ecommerceService;

  @GetMapping("/find")
  public List<Ecommerce> findCustomerFullName(@RequestParam String name) {
    log.info("Request Param name ==>> {}", name);
    return ecommerceService.findByName(name);
  }

  @GetMapping("/findByDayOfWeeks")
  public List<Ecommerce> findByDayOfWeeks(@RequestParam List<String> days) {
    log.info("Request Param days ==>> {}", days);
    return ecommerceService.findByDayOfWeeks(days);
  }
}
