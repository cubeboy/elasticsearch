package com.jinnara.ecommerce.repository.entity;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Builder
@Data
public class QueryParam extends QueryTemplate {
  @MatchQuery(field="customer_full_name")
  private final String customerFullName;
  @TermsQuery(field="day_of_week")
  private final List<String> dayOfWeeks;


//  public Query getQueryCustomerFullName() {
//    return QueryBuilders.match(m -> m
//        .field("customer_full_name")
//        .query(this.customerFullName)
//    );
//  }
//
//  public Query getQueryDayOfWeeks() {
//    List<FieldValue> dayOfWeeks = new ArrayList<>();
//    dayOfWeeks.add(new FieldValue.Builder().stringValue("Sunday").build());
//    dayOfWeeks.add(new FieldValue.Builder().stringValue("Monday").build());
//
//    return QueryBuilders.terms(t -> t
//        .field("day_of_week")
//        .terms(vt -> vt.value(dayOfWeeks))
//    );
//  }
}
