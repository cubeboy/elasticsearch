package com.jinnara.ecommerce.repository.entity;

import lombok.Data;
import java.util.List;

@Data
public class Ecommerce {
  private String id;
  private String customerFullName;
  private String [] category;
  private Long customerId;
  private String dayOfWeek;
  private List<Product> products;
}
