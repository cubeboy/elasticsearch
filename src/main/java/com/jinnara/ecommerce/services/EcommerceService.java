package com.jinnara.ecommerce.services;

import com.jinnara.ecommerce.repository.entity.Ecommerce;
import com.jinnara.ecommerce.repository.entity.EcommerceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EcommerceService {
  private final EcommerceRepository ecommerceRepository;

  public List<Ecommerce> findByName(String name) {
    return ecommerceRepository.findByCustomerFullName(name);
  }
}
