package com.jinnara.ecommerce.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ecommerce {
  private String _id;
  private String customer_full_name;
  private String [] category;
  private Long customer_id;
  private String day_of_week;
  private List<Product> products;
}
