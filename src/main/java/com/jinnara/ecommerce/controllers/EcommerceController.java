package com.jinnara.ecommerce.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
  private final ObjectMapper objectMapper = new ObjectMapper();

  @GetMapping("/find")
  public JsonNode findCustomerFullName(@RequestParam String name) {
    log.info("Request Param name ==>> {}", name);
    return objectMapper.convertValue(
        ecommerceService.findByName(name),
        JsonNode.class);
  }

  @GetMapping("/findByDayOfWeeks")
  public JsonNode findByDayOfWeeks(@RequestParam List<String> days) {
    log.info("Request Param days ==>> {}", days);
    return objectMapper.convertValue(
        ecommerceService.findByDayOfWeeks(days),
        JsonNode.class);
  }
}
