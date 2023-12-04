package com.jinnara.ecommerce.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "kibana_sample_data_ecommerce")
public class Ecommerce {
  @Id
  private String id;

  @Field(type = FieldType.Text, name = "customer_full_name")
  private String customerFullName;

  @Field(type = FieldType.Text, name = "category")
  private String category;

  @Field(type = FieldType.Keyword, name = "customer_id")
  private String customerId;

  @Field(type = FieldType.Keyword, name = "day_of_week")
  private String dayOfWeek;
}
