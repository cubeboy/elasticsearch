package com.jinnara.ecommerce.repository.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class Product {
  @Field(name = "_id", type = FieldType.Text)
  private String id;
  @Field(name = "category", type = FieldType.Text)
  private String category;
  @Field(name = "product_name", type = FieldType.Text)
  private String productName;
  @Field(name = "product_id", type = FieldType.Long)
  private Long productId;
}
