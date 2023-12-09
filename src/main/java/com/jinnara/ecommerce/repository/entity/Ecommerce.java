package com.jinnara.ecommerce.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ecommerce {
  private String id;
  private String customerFullName;
  private String [] category;
  private Long customerId;
  private String dayOfWeek;
  private List<Product> products;
}
