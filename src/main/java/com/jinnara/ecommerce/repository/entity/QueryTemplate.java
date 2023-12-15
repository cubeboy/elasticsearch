package com.jinnara.ecommerce.repository.entity;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public abstract class QueryTemplate {
  public List<Query> getMatchQueryList() {
    List<Field> fieldList = List.of(this.getClass().getDeclaredFields());
    return fieldList.stream()
        .filter(field -> field.isAnnotationPresent(MatchQuery.class))
        .map(field -> {
          try {
            field.setAccessible(true);
            String value = (String) field.get(this);
            MatchQuery annotation = field.getAnnotation(MatchQuery.class);
            return QueryBuilders.match(m -> m
                .field(annotation.field())
                .query(value)
            );
          } catch (Exception e) {
            e.printStackTrace();
          }
          return null;
        })
        .filter(q -> q != null)
        .collect(Collectors.toList());
  }

  public List<Query> getTermsQueryList() {
    List<Field> fieldList = List.of(this.getClass().getDeclaredFields());
    return fieldList.stream()
        .filter(field -> field.isAnnotationPresent(TermsQuery.class))
        .map(field -> {
          try {
            field.setAccessible(true);
            List<String> values = (List<String>) field.get(this);
            List<FieldValue> fieldValues = values.stream()
                .map(value -> new FieldValue.Builder().stringValue(value).build())
                .collect(Collectors.toList());
            TermsQuery annotation = field.getAnnotation(TermsQuery.class);
            return QueryBuilders.terms(t -> t
                .field(annotation.field())
                .terms(ft -> ft.value(fieldValues))
            );
          } catch (Exception e) {
            e.printStackTrace();
          }
          return null;
        })
        .filter(q -> q != null)
        .collect(Collectors.toList());
  }
}
