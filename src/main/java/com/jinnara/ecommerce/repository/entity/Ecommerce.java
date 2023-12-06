package com.jinnara.ecommerce.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "kibana_sample_data_ecommerce", type = "_doc", createIndex = false)
public class Ecommerce {
  @Id
  private String id;

  @Field(name = "customer_full_name", type = FieldType.Text)
  private String customerFullName;

  @Field(name = "category", type = FieldType.Text)
  private String [] category;

  @Field(name = "customer_id", type = FieldType.Keyword)
  private Long customerId;

  @Field(name = "day_of_week", type = FieldType.Text)
  private String dayOfWeek;
}
