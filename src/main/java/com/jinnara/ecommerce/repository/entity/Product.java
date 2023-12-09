package com.jinnara.ecommerce.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
  private String _id;
  private String category;
  private String product_name;
  private Long product_id;
}
